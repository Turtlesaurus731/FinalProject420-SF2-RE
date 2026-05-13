package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public abstract class User {
    protected String id;
    protected String name;
    protected List<Item> borrowedItems;
    private static int nextId = 1;

    public User(String name) {
        this.id = String.format("%04d", nextId++);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Converts the user into CSV format.
     * @return a CSV representation of the user
     */
    public abstract String toCSV();

    /**
     * Returns the user's borrowing limit.
     * @return the borrowing limit
     */
    public abstract int getBorrowLimit();

    /**
     * Adds an item to the user's borrowed items list.
     * @param item the item to borrow
     */
    public void borrowItem(Item item) {
        borrowedItems.add(item);
    }

    /**
     * Removes an item from the user's borrowed items list.
     * @param item the item to return
     */
    public void returnItem(Item item) {
        borrowedItems.remove(item);
    }

    /**
     * Checks if the user can borrow the given item.
     * @param item the item to check
     * @return true if the user can borrow the item, false otherwise
     */
    public abstract boolean canBorrow(Item item);
}
