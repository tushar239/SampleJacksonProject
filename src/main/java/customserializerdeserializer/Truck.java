package customserializerdeserializer;

/**
 * @author Tushar Chokshi @ 8/22/17.
 */
public class Truck extends Vehicle {
    private double payloadCapacity;

    public Truck() {
    }

    public Truck(String make, String model, double payloadCapacity) {
        super(make, model);
        this.payloadCapacity = payloadCapacity;
    }

    public double getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(double payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "payloadCapacity=" + payloadCapacity +
                '}';
    }
}
