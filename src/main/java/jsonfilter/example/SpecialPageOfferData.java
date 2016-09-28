package jsonfilter.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * @author Tushar Chokshi @ 4/26/15.
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
@JsonFilter("SpecialsPageOfferDataFilter")
public class SpecialPageOfferData {
    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
