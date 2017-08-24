package customserializerdeserializer;

/**
 * @author Tushar Chokshi @ 8/22/17.
 */
public abstract class Vehicle {
    private String make;
    private String model;

    protected Vehicle() {
    }

    protected Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
