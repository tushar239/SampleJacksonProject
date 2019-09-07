package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

// To avoid jackson's exception due to circular reference, use this annotation
// https://www.logicbig.com/tutorials/misc/jackson/json-identity-info-annotation.html
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
    private int id;
    private String name;
    private Order order;
    private Customer coCustomer; // if customer1 has customer2 and customer2 has customer1, then it is a circular reference.
                                 // if customer1 has customer2 and customer2 has customer3, then it is not a circular reference.


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCoCustomer() {
        return coCustomer;
    }

    public void setCoCustomer(Customer coCustomer) {
        this.coCustomer = coCustomer;
    }
    /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(order, customer.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, order);
    }*/

    /*@Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }*/
}