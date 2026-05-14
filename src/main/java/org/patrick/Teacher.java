package org.patrick;

public class Teacher extends User {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public String toCSV() {
        return id + "," + name + ",Teacher";
    }

    @Override
    public int getBorrowLimit() {
        return Constants.MAX_ITEMS_TEACHER;
    }

    @Override
    public boolean canBorrow(Item item) {
        return borrowedItems.size() < getBorrowLimit();
    }
}