package org.patrick;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Users
        Student student = new Student("Patrick");
        Teacher teacher = new Teacher("Potato");
        Admin admin = new Admin("Bidoof");

        library.addUser(student);
        library.addUser(teacher);
        library.addUser(admin);

        // Items
        Book book1 = new Book("How to pass my class", "1234567890123", "PatrickA", "Fantasy");
        DVD dvd1 = new DVD("How to sleep", "Sleepy Turtle", 123);
        Magazine magazine1 = new Magazine("Ran out of Ideas", 42, "PatrickPress");

        library.addItem(book1);
        library.addItem(book1);
        library.addItem(dvd1);
        library.addItem(dvd1);
        library.addItem(magazine1);

        // Borrow
        System.out.println("\nBorrowItemDemonstration");
        library.borrowItem(student, book1);
        System.out.println(book1);

        // Return
        System.out.println("\nReturnItemDemonstration");
        library.returnItem(student, book1);
        System.out.println(book1);

        // Stream Search
        System.out.println("\nStreamSearchDemonstration");
        System.out.println(library.streamSearchByAuthor("PatrickA"));
        System.out.println(library.streamSearchByTitle("How to pass my class"));
        System.out.println(library.searchByCreator("Sleepy Turtle"));

        // Recursive Search
        System.out.println("\nRecursiveSearchDemonstration");
        System.out.println(library.recursiveSearchByAuthor("PatrickA"));
        System.out.println(library.recursiveSearchByTitle("How to pass my class"));

        // Lost
        System.out.println("\nLostDemonstration");
        admin.markItemAsLost(library, magazine1);
        System.out.println(magazine1);

        // Generate Report
        System.out.println("\nGenerateReportDemonstration");
        System.out.println(admin.generateReport(library));

        // CSV backup
        admin.backupData(library);

        // CSV load
        System.out.println("\nCSV backup and load Demonstration");
        Library loadedLibrary = new Library();

        loadedLibrary.loadUsersCSV(null);
        loadedLibrary.loadItemsCSV(null);
        System.out.println(loadedLibrary.getUsers());
        System.out.println(loadedLibrary.getItems());
    }
}