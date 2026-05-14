package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    public Magazine(String title, int issueNumber, String publisher) {
        super(title);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    @Override
    public String toCSV() {
        return "Magazine," +
                id + "," +
                title + "," +
                status + "," +
                issueNumber + "," +
                publisher;
    }

    @Override
    public String getCreator() {
        return publisher;
    }
}
