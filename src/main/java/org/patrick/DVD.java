package org.patrick;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DVD extends Item {
    private String director;
    private int duration;

    public DVD(String title, String director, int duration) {
        super(title);
        this.director = director;
        this.duration = duration;
    }

    @Override
    public String toCSV() {
        return "DVD," +
                id + "," +
                title + "," +
                status + "," +
                director + "," +
                duration;
    }

    @Override
    public String getCreator() {
        return director;
    }
}
