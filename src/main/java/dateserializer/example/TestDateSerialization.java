package dateserializer.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Tushar Chokshi @ 2/22/17.
 */

// http://www.baeldung.com/jackson-serialize-dates
public class TestDateSerialization {
    public static void main(String[] args) throws ParseException, IOException {

        serializeExample();
        
        deserializeExample();


    }

    private static void deserializeExample() throws IOException {
        String eventString = "{\"name\":\"party\",\"startDate\":\"20-12-2014 02:30:00\", \"endDate\":\"20-12-2017 02:30:00\"}";

        // Standard acceptable date formats in json string are "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"
        // if json has different format, you need to set that format as @JsonFormat on top of the property or in ObjectMapper as shown below

        //SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setDateFormat(df);

        Event event = mapper.readValue(eventString, Event.class);
        System.out.println("Start Date: "+ event.getStartDate()); // Sat Dec 20 02:30:00 PST 2014
        System.out.println("End Date: "+ event.getEndDate()); //  Tue Dec 19 18:30:00 PST 2017

    }

    protected static void serializeExample() throws ParseException, JsonProcessingException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("PST"));

        Date startDate = df.parse("01-01-1970 02:30");
        System.out.println(startDate);

        Date endDate = df.parse("01-01-1973 02:30");

        Event event = new Event();
        event.setName("party");
        event.setStartDate(startDate);
        event.setEndDate(endDate);

        {
            ObjectMapper mapper = new ObjectMapper(); // start date will be serialized as a long number, which is not readable. You need to use some Date format for jackson to serialize the date in readable text form. You can either set the date format on the property as @JsonFormat (like endDate) or in the ObjectMapper (as shown below).
            String result = mapper.writeValueAsString(event);
            System.out.println(result); // {"name":"party","startDate":37800000,"endDate":"01-01-1973 10:30:00"} when date number is generated, it is as per UTC timezone. When you try to convert it back to Date object, java converts this UTC number back to PST(your machine's timezone) and displays a date accordingly.

            Date date = new Date(37800000);
            System.out.println(date); // Thu Jan 01 02:30:00 PST 1970

        }
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // follows ISO-8061 format for date serialization. It serializes date as string instead of timestamp(number)
            String result = mapper.writeValueAsString(event); // date will be serialized as a string, which is readable
            System.out.println(result);// {"name":"party","startDate":"1970-01-01T02:30:00.000+0000"}
        }
    }
}
