package customserializerdeserializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tushar Chokshi @ 8/22/17.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = getObjectMapper();

        Car car = new Car("Mercedes-Benz", "S500", 5, 250.0);
        Truck truck = new Truck("Isuzu", "NQR", 7500.0);

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(car);
        vehicles.add(truck);

        Offer offer = new Offer(1L);

        Fleet fleet = new Fleet();
        fleet.setVehicles(vehicles);
        fleet.setOffer(offer);

        String jsonDataString = objectMapper.writeValueAsString(fleet);
        System.out.println(jsonDataString); // {"vehicles":["java.util.ArrayList",[["customserializer.Car",{"make":"Mercedes-Benz","model":"S500","seatingCapacity":5,"topSpeed":250.0}],["customserializer.Truck",{"make":"Isuzu","model":"NQR","payloadCapacity":7500.0}]]],"offer":{"id":1}}

        Fleet deserializedFleet = objectMapper.readValue(jsonDataString, Fleet.class);
        System.out.println(deserializedFleet); // Fleet{vehicles=[Car{seatingCapacity=5, topSpeed=250.0}, Truck{payloadCapacity=7500.0}], offer=Offer{id=1}}

    }

    protected static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // This will serialize the object with class name in it, so that during deserialization, it knows what class to deserialize it.
        // If you don't have this, you need to add custom deserializers
        objectMapper.enableDefaultTyping();

        return objectMapper;
    }


}
