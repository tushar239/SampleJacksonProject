package basicannotations.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;
import java.util.List;

/*

https://github.com/FasterXML/jackson-annotations
https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations

The default Jackson property detection rules will find:

All ''public'' fields
All ''public'' getters ('getXxx()' methods)
All setters ('setXxx(value)' methods), ''regardless of visibility'')


@JsonIgnoreProperties: per-class annotation to list properties to ignore, or to indicate that any unknown properties are to be ignored.
On serialization,
@JsonIgnoreProperties({"prop1", "prop2"}) ignores listed properties
On deserialization,
@JsonIgnoreProperties(ignoreUnknown=true) ignores properties that don't have getter/setters without throwing any warning or exception
*/

//@JsonIgnoreProperties({"unknown"}) // Only for serialization (object to json), ignores properties
@JsonIgnoreProperties(ignoreUnknown=true) // Only for deserialization (json to object), ignores properties that don't have getter/setters without throwing any warning or exception
@JsonPropertyOrder(value = {"firstName", "lastName", "addresses", "office", "result", "unknown"})
// @JsonTypeInfo will add "@class" : "basicannotations.example.Person" to json
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Person {
    @JsonIgnore
    private int id;

    @JsonProperty("_firstName")
    @JsonView(View.Summary.class)
    private String firstName;

    @JsonProperty("_lastName")
    @JsonView(View.Summary.class)
    private String lastName;

    @JsonFormat(pattern = "MM/DD/YYYY", timezone = "en_US")
    @JsonView(View.Summary.class)
    private Date birthDate;

    /*
        Below @JsonTypeInfo will generate
        "addresses" : [
            {
                "residentialAddress" : {
                    ...
                }
            }, {
                "workAddress" : {
                    ...
                }
            }
        ]

        @JsonTypeInfo is required with @JsonSubTypes

        Alternatively, these annotations can be placed on Address class. It will give the same effect.
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    //@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="objectType")
    @JsonSubTypes(
            {
                    @JsonSubTypes.Type(name="residentialAddress", value=ResidentialAddress.class),
                    @JsonSubTypes.Type(name="workAddress", value=WorkAddress.class)
            }
    )
    private List<Address> addresses;

    private Office office;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT) // ???????
    private Double income;

    private String result;

    public String unknown="ok";

    public Person() {

    }
    // http://www.cowtowncoder.com/blog/archives/2011/07/entry_457.html
    // While de-serializing (object to json) Person object, use this constructor for firstName, lastName and birthDate. Use setter methods for other properties.
    @JsonCreator
    public Person(@JsonProperty("_firstName") String firstName, @JsonProperty("_lastName") String lastName, @JsonProperty("birthDate") Date birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

/*
    public String getUnknown() {
        return unknown;
    }


    public void setUnknown(String unknown) {
        this.unknown = unknown;
    }
*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    // method doesn't start with get***(), so normally it is not considered during serialization. To make it a getter method use @JsonGetter
    @JsonGetter("result")
    public String giveMeResult() {
        return "success";
    }

    // method doesn't start with set***(), so normally it is not considered during de-serialization. To make it a setter method use @JsonSetter
    @JsonSetter("result")
    public void assignResult(String result) {
        this.result = result;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}
