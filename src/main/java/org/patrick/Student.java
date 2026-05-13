package org.patrick;

public class Student extends User {

    public Student(String name) {
        super(name);
    }

    @Override
    public String toCSV() {
        return id + "," + name + ",Student";
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public boolean canBorrow(Item item) {
        return borrowedItems.size() < getBorrowLimit() && item instanceof Book;
    }
}
