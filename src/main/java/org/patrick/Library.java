package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class Library {
    private List<Item> items;
    private List<User> users;
    private Stack<Item> borrowedStack;
    private Queue<Item> returnQueue;
    private Set<Item> lostItems;
    private Map<String, Item> itemMap;

    public Library() {
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
        this.borrowedStack = new Stack<>();
        this.returnQueue = new LinkedList<>();
        this.lostItems = new HashSet<>();
        this.itemMap = new HashMap<>();
    }

    /**
     * Adds an item to the library.
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
        itemMap.put(item.getId(), item);
    }

    /**
     * Adds a user to the library.
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Allows a user to borrow an item.
     * @param user the user borrowing the item
     * @param item the item being borrowed
     */
    public void borrowItem(User user, Item item) {
        if (user == null || item == null) {
            throw new IllegalArgumentException("User or item cannot be null");
        }
        if (!users.contains(user)) {
            throw new IllegalArgumentException("User not registered");
        }
        if (item.getStatus() != Item.Status.IN_STORE) {
            throw new IllegalStateException("Item already borrowed");
        }
        if (!user.canBorrow(item)) {
            throw new IllegalStateException("Borrow limit exceeded");
        }
        if (item.getStatus() == Item.Status.LOST) {
            throw new IllegalStateException("Item is lost and cannot be borrowed right now");
        }

        user.borrowItem(item);
        item.setStatus(Item.Status.BORROWED);
        borrowedStack.push(item);
    }

    /**
     * Allows a user to return an item.
     * @param user the user returning the item
     * @param item the item being returned
     */
    public void returnItem(User user, Item item) {
        if (user == null || item == null) {
            throw new IllegalArgumentException("User or item cannot be null");
        }
        if (item.getStatus() != Item.Status.BORROWED) {
            throw new IllegalStateException("Item is not currently borrowed");
        }
        user.returnItem(item);
        item.setStatus(Item.Status.IN_STORE);
        returnQueue.add(item);
    }

    /**
     * Recursive search for items by title.
     * @param title search keyword
     * @return matching items
     */
    public List<Item> recursiveSearchByTitle(String title) {
        List<Item> results = new ArrayList<>();
        recursiveTitleHelper(title.toLowerCase(), 0, results);
        return results;
    }

    private void recursiveTitleHelper(String title, int index, List<Item> results) {
        if (index >= items.size()) return;
        Item item = items.get(index);

        if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
            results.add(item);
        }
        recursiveTitleHelper(title, index + 1, results);
    }

    /**
     * Recursive search for books by author.
     * @param author search keyword
     * @return matching books
     */
    public List<Book> recursiveSearchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        recursiveAuthorHelper(author.toLowerCase(), 0, results);
        return results;
    }

    private void recursiveAuthorHelper(String author, int index, List<Book> results) {
        if (index >= items.size()) return;
        Item item = items.get(index);

        if (item instanceof Book book) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }
        recursiveAuthorHelper(author, index + 1, results);
    }

    /**
     * Stream search by title.
     * @param title search keyword
     * @return matching items
     */
    public List<Item> streamSearchByTitle(String title) {
        return items.stream()
                .filter(i -> i.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toMap(Item::getId, i -> i, (a, b) -> a))
                .values()
                .stream()
                .toList();
    }

    /**
     * Searches all items by creator (author/director/publisher).
     * @param creator name of creator
     * @return matching items
     */
    public List<Item> searchByCreator(String creator) {
        return items.stream()
                .filter(i -> i.getCreator().toLowerCase().contains(creator.toLowerCase()))
                .collect(Collectors.toMap(Item::getId, i -> i, (a, b) -> a))
                .values()
                .stream()
                .toList();
    }

    /**
     * Stream search by author (books only).
     * @param author search keyword
     * @return matching books
     */
    public List<Book> streamSearchByAuthor(String author) {
        return items.stream()
                .filter(i -> i instanceof Book)
                .map(i -> (Book) i)
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toMap(Book::getId, b -> b, (a, b) -> a))
                .values()
                .stream()
                .toList();
    }

    /**
     * Loads users from a CSV file.
     * @param path the CSV file path
     */
    public void loadUsersCSV(String path) {
        String userPath = (path == null || path.isEmpty()) ? Constants.USERS_CSV_PATH : path + "/users.csv";
        File file = new File(userPath);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String type = data[2];
                User user = getUser(type, name);

                if (user != null) {
                    user.setId(id);
                    addUser(user);
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to load users CSV");
        }
    }

    /**
     * Creates the correct user object from CSV data.
     * @param type user type
     * @param name user name
     * @return created user
     */
    private static User getUser(String type, String name) {
        User user = null;
        switch (type) {
            case "Student" -> user = new Student(name);
            case "Teacher" -> user = new Teacher(name);
            case "Admin" -> user = new Admin(name);
        }
        return user;
    }

    /**
     * Loads items from a CSV file.
     * @param path the CSV file path
     */
    public void loadItemsCSV(String path) {
        String itemPath = (path == null || path.isEmpty()) ? Constants.ITEMS_CSV_PATH : path + "/items.csv";
        File file = new File(itemPath);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split(",");
                String type = data[0];
                String id = data[1];
                String title = data[2];
                Item.Status status = Item.Status.valueOf(data[3]);
                Item item = getItem(type, data, title);

                if (item != null) {
                    item.setId(id);
                    item.setStatus(status);
                    addItem(item);
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to load items CSV");
        }
    }

    /**
     * Creates the correct item object from CSV data.
     * @param type item type
     * @param data CSV row data
     * @param title item title
     * @return created item
     */
    private static Item getItem(String type, String[] data, String title) {
        Item item = null;

        switch (type) {
            case "Book" -> {
                String isbn = data[4];
                String author = data[5];
                String genre = data[6];
                item = new Book(title, isbn, author, genre);
            }
            case "DVD" -> {
                String director = data[4];
                int duration = Integer.parseInt(data[5]);
                item = new DVD(title, director, duration);
            }
            case "Magazine" -> {
                int issueNumber = Integer.parseInt(data[4]);
                String publisher = data[5];
                item = new Magazine(title, issueNumber, publisher);
            }
        }
        return item;
    }

    /**
     * Backs up user data to a CSV file
     * @param path the CSV path
     */
    public void backupUsersCSV(String path) {
        String userPath = (path == null || path.isEmpty()) ? Constants.USERS_CSV_PATH : path + "/users.csv";

        File file = new File(userPath);
        try (FileWriter fw = new FileWriter(file)) {
            for (User user : users) {
                fw.write(user.toCSV() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Failed to backup users CSV");
        }
    }

    /**
     * Backs up item data to a CSV file
     * @param path the CSV path
     */
    public void backupItemsCSV(String path) {
        String itemPath = (path == null || path.isEmpty()) ? Constants.ITEMS_CSV_PATH : path + "/items.csv";

        File file = new File(itemPath);
        try (FileWriter fw = new FileWriter(file)) {
            for (Item item : items) {
                fw.write(item.toCSV() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Failed to backup items CSV");
        }
    }

    /**
     * Marks an item as lost in the library.
     * The item status is updated, and it is stored in the lost items set.
     * @param item the item to mark as lost
     */
    public void markItemAsLost(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        item.setStatus(Item.Status.LOST);
        lostItems.add(item);
    }
}
