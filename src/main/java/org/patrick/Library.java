package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Map<String, Item> itemMap; //TODO: update in class diagram and fix the typo there while I am at it

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
        if (user.canBorrow(item) && item.getStatus() == Item.Status.IN_STORE) {
            user.borrowItem(item);
            item.setStatus(Item.Status.BORROWED);
            borrowedStack.push(item);
        }
    }

    /**
     * Allows a user to return an item.
     * @param user the user returning the item
     * @param item the item being returned
     */
    public void returnItem(User user, Item item) {
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
            if (book.getAuthor().toLowerCase().contains(author)) {
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
        //TODO: ADD to class diagram ;v; this was so much simpler than searching only by author...
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

    // TODO: CSV placeholders
    public void loadUsersCSV(String path) {}
    public void loadItemsCSV(String path) {}
    public void backupUsersCSV(String path) {}
    public void backupItemsCSV(String path) {}
}
