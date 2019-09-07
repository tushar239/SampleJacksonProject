package jsonidentityinfoexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;

import java.io.IOException;

public class ExampleMain {

    public static void main(String[] args) throws IOException {
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setItemIds(List.of(10, 30));

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Peter");
        //customer.setOrder(order);
        order1.setCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Tushar");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("Jag");


        // circular reference : customer1 has customer2 and vice-a-versa
        customer1.setCoCustomer(customer2);

        customer2.setCoCustomer(customer1);


        System.out.println(customer1);

        ObjectMapper om = new ObjectMapper();

        System.out.println("-- serializing customer1 --");

        String customer1Serialized = om.writeValueAsString(customer1);
        System.out.println(customer1Serialized);

        System.out.println("-- serializing order1 --");

        String orderJson = om.writeValueAsString(order1);
        System.out.println(orderJson);


        System.out.println("-- deserializing customer1--");
        Customer customer1Deserialized = om.readValue(customer1Serialized, Customer.class);
        System.out.println(customer1Deserialized);
    }
}
