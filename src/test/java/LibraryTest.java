import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.patrick.*;

public class LibraryTest {

    @Test
    @DisplayName("addItem(Item): iten is added to library list")
    public void addItemTest() {
        Library library = new Library();
        Book book = new Book("How to get 100% on my project", "1234567890", "Patrick", "Fantasy");
        library.addItem(book);
        int expected = 1;
        int actual = library.getItems().size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("borrowItem(User, Item): item becomes BORROWED")
    public void borrowItemTest() {
        Library library = new Library();
        Student student = new Student("The sentient potato");
        Book book = new Book("How to get 100% on my project", "12345", "Patrick", "Fantasy");
        library.addItem(book);
        library.addUser(student);
        library.borrowItem(student, book);

        Assertions.assertEquals(Item.Status.BORROWED, book.getStatus());
    }

    @Test
    @DisplayName("returnItem(User, Item): item becomes IN_STORE")
    public void returnItemTest() {
        Library library = new Library();
        Student student = new Student("The sentient potato");
        Book book = new Book("How to get 100% on my project", "12345", "Patrick", "Fantasy");
        library.addItem(book);
        library.addUser(student);
        library.borrowItem(student, book);
        library.returnItem(student, book);

        Assertions.assertEquals(Item.Status.IN_STORE, book.getStatus());
    }

    @Test
    @DisplayName("streamSearchByAuthor(String): finds books by author")
    public void streamSearchByAuthorTest() {
        Library library = new Library();
        Book book = new Book("How to get 100% on my project", "1234567890", "Patrick", "Fantasy");
        library.addItem(book);
        int actual = library.streamSearchByAuthor("Patrick").size();

        Assertions.assertEquals(1, actual);
    }

    @Test
    @DisplayName("recursiveSearchByAuthor(String): finds books by author")
    public void recursiveSearchByAuthorTest() {
        Library library = new Library();
        Book book = new Book("Ran out of Ideas", "12345", "Patrick", "Fantasy");
        library.addItem(book);
        int expected = 1;
        int actual = library.recursiveSearchByAuthor("Patrick").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("recursiveSearchByTitle(String): finds matching items")
    public void recursiveSearchByTitleTest() {
        Library library = new Library();
        Book book = new Book("How to not procrastinate", "12345", "Impossible", "Fantasy");
        library.addItem(book);
        int expected = 1;
        int actual = library.recursiveSearchByTitle("How to not procrastinate").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("streamSearchByTitle(String): finds matching items")
    public void streamSearchByTitleTest() {
        Library library = new Library();
        Book book = new Book("How to catch them all", "12345", "Pokemon fan", "Fantasy");
        library.addItem(book);
        int expected = 1;
        int actual = library.streamSearchByTitle("How to catch them all").size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("searchByCreator(String): finds items by creator field")
    public void searchByCreatorTest() {
        Library library = new Library();
        DVD dvd = new DVD("How I got 0% on my project", "Patrick",148);
        library.addItem(dvd);
        int actual = library.searchByCreator("Patrick").size();

        Assertions.assertEquals(1, actual);
    }

    @Test
    @DisplayName("borrowItem(User, Item): cannot borrow same item twice")
    public void borrowSameItemTwiceTest() {

        Library library = new Library();
        Student student = new Student("Patrick");
        Book book = new Book("I want to sleep", "12345", "Sleepy Turtle", "Action");
        library.addItem(book);
        library.addUser(student);
        library.borrowItem(student, book);

        try {
            library.borrowItem(student, book);
            Assertions.fail("Expected IllegalStateException was not thrown");
        } catch (IllegalStateException e) {
            Assertions.assertTrue(e.getMessage() != null);
        }
    }
}
