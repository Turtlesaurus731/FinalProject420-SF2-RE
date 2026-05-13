package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Item {
    protected String id;
    protected String title;
    protected Status status;
    private static int nextId = 1;

    public enum Status {
        IN_STORE,
        BORROWED,
        LOST
    }

    public Item(String title) {
        this.id = String.format("%04d", nextId++);
        this.title = title;
        this.status = Status.IN_STORE;
    }

    /**
     * Converts the item into CSV format.
     * @return a CSV representation of the item
     */
    public abstract String toCSV();
}

