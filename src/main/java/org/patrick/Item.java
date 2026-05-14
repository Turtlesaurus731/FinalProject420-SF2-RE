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

    /**
     * Gets the Creator of an Item
     * @return the creator of an Item
     */
    public abstract String getCreator();

    /**
     * Returns a logical search key used to group identical items
     * in search results (to avoid duplicate copies appearing in a search).
     * @return the search key for this item
     */
    public String getSearchKey() {
        return id;
    }
}

