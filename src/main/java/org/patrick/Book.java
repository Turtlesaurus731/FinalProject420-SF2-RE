package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Book extends Item{
    private String isbn;
    private String author;
    private String genre;

    public Book(String title, String isbn, String author, String genre) {
        super(title);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    /**
     * Checks if the ISBN is valid (A valid ISBN must contain exactly 13 digits).
     * @return true if the ISBN is valid, false otherwise
     */
    public boolean isValidISBN() {
        if (isbn.length() != 13) {
            return false;
        }
        for (int i = 0; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toCSV() {
        return "Book," +
                id + "," +
                title + "," +
                status + "," +
                isbn + "," +
                author + "," +
                genre;
    }

    @Override
    public String getCreator() {
        return author;
    }

    @Override
    public String getSearchKey() {
        return isbn;
    }
}
