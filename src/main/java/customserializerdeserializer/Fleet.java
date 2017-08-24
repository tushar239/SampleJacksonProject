package customserializerdeserializer;

import java.util.List;

/**
 * @author Tushar Chokshi @ 8/22/17.
 */
public class Fleet {
    private List<Vehicle> vehicles;
    private Offer offer;

    public Fleet() {
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "vehicles=" + vehicles +
                ", offer=" + offer +
                '}';
    }
}
