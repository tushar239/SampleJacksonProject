package dateformat;

import java.util.Date;

/**
 * @author Tushar Chokshi @ 8/24/17.
 */
public class Event {
    private String party;
    private Date eventDate;

    public Event(String party, Date eventDate) {
        this.party = party;
        this.eventDate = eventDate;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (party != null ? !party.equals(event.party) : event.party != null) return false;
        return eventDate != null ? eventDate.equals(event.eventDate) : event.eventDate == null;
    }

    @Override
    public int hashCode() {
        int result = party != null ? party.hashCode() : 0;
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "party='" + party + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
