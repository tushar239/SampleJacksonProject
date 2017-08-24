package customserializerdeserializer;

/**
 * @author Tushar Chokshi @ 8/22/17.
 */
public class Offer {
    private Long id;

    public Offer() {
    }

    public Offer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                '}';
    }
}
