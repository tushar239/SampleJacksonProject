import basicannotations.example.Address;
import basicannotations.example.Office;
import basicannotations.example.Person;
import basicannotations.example.ResidentialAddress;
import basicannotations.example.View;
import basicannotations.example.WorkAddress;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jsonfilter.example.SpecialPageOfferData;
import jsonfilter.example.SpecialsPageData;
import jsonserializer.example.Offer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Json Annotations
https://github.com/FasterXML/jackson-annotations
https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations


JSonNode example
http://www.mkyong.com/java/jackson-tree-model-example/

@JsonView example
http://www.baeldung.com/jackson-json-view-annotation

 */


public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("Full View -------");
        displayFullView();
        System.out.println("Summary View ------");
        displaySummaryView();
        System.out.println("Test Custom JsonSerializer -----");
        displayOfferUsingCustomSerializer();
        System.out.println("Test Custom JsonFilter -----");
        displayOfferUsingCustomFilter();
    }

    private static void displayOfferUsingCustomFilter() throws Exception {
        SpecialsPageData specialsPageData = new SpecialsPageData();
        specialsPageData.setId(1);
        specialsPageData.setType("Discount");
        SpecialPageOfferData offerData = new SpecialPageOfferData();
        offerData.setId(1);
        offerData.setType("Discount");
        specialsPageData.setSpecialPageOfferData(offerData);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SpecialsPageDataFilter", SimpleBeanPropertyFilter.filterOutAllExcept("specialPageOfferData"))
                .addFilter("SpecialsPageOfferDataFilter", SimpleBeanPropertyFilter.filterOutAllExcept("type"));


        ObjectWriter writer = mapper.writer(filters);
        System.out.println(writer.writeValueAsString(specialsPageData));
    }

    private static void displayOfferUsingCustomSerializer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(getOffer()));
    }

    private static Offer getOffer() {
        Offer offer = new Offer();
        offer.setId(1);
        offer.setType("Discount");
        return offer;
    }

    private static void displayFullView() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(getPerson()));
    }

    private static void displaySummaryView() throws IOException {
        // http://www.baeldung.com/jackson-json-view-annotation

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        System.out.println(mapper.writerWithView(View.Summary.class).writeValueAsString(getPerson()));
    }

    private static Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("Tushar");
        person.setLastName("Chokshi");
        person.setBirthDate(new Date());
        person.setIncome(new Double(300000.13453424));

        Office office = new Office();
        person.setOffice(office);

        Address address1 = new ResidentialAddress();
        address1.setCity("Redmond");
        address1.setState("WA");
        address1.setCounty("King");
        address1.setCountry("USA");

        Address address2 = new WorkAddress();
        address2.setCity("Sacramento");
        address2.setState("CA");
        address2.setCounty("Sacramento");
        address2.setCountry("USA");

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address1);
        addressList.add(address2);
        person.setAddresses(addressList);

        return person;
    }
}
