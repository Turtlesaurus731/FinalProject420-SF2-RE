package org.patrick;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addUser(new Student("Patrick"));
        library.addUser(new Teacher("Potato"));
        Admin admin = new Admin("Bidoof");
        library.addUser(admin);
        library.addItem(new Book("How to pass my class", "1234567890123", "PatrickA", "Fantasy"));
        library.addItem(new Book("How to pass my class", "1234567890123", "PatrickA", "Fantasy"));
        library.addItem(new DVD("How to sleep", "Sleepy Turtle", 123));
        library.addItem(new DVD("How to sleep", "Sleepy Turtle", 123));
        library.addItem(new Magazine("Ran out of Ideas", 42, "PatrickPress"));

        library.backupUsersCSV(null);
        library.backupItemsCSV(null);
        admin.backupData(library);
        Library loadedLibrary = new Library();
        loadedLibrary.loadItemsCSV(null);
        loadedLibrary.loadUsersCSV(null);
        System.out.println(loadedLibrary.getItems());
        System.out.println(loadedLibrary.getUsers());

        String report = admin.generateReport(library);
        System.out.println(report);

        System.out.println(library.streamSearchByAuthor("PatrickA"));
        System.out.println(library.searchByCreator("Sleepy Turtle"));
        System.out.println(library.streamSearchByTitle("How to pass my class"));
        System.out.println(library.recursiveSearchByAuthor("PatrickA"));
        System.out.println(library.recursiveSearchByTitle("How to pass my class"));
    }
}