package dateformat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Tushar Chokshi @ 8/24/17.
 */
// http://www.baeldung.com/jackson-serialize-dates
public class Test {
    public static void main(String[] args) throws JsonProcessingException, ParseException {
        whenSerializingDateToISO8601_thenSerializedToText();
    }

    public static void whenSerializingDateToISO8601_thenSerializedToText()
            throws JsonProcessingException, ParseException {

        // Testing ObjectMapper's WRITE_DATES_AS_TIMESTAMPS configuration
        {
            Date date = new Date();
            Event event = new Event("party", date);

            ObjectMapper mapper = new ObjectMapper();
            // This looks like ISO-8061 format. Instead of serializing date as long number, it is serialized as string in ISO-8061 format.
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // {"party":"party","eventDate":"2017-08-24T18:35:29.191+0000"}
            mapper.setDateFormat(new ISO8601DateFormat()); // {"party":"party","eventDate":"2017-08-24T20:35:20Z"}

            String result = mapper.writeValueAsString(event);
            System.out.println(result);
        }

        // ISO format with UTC time zone. Trying to do same as what 'WRITE_DATES_AS_TIMESTAMPS' is doing in ObjectMapper.
        {
            String ISO_8601_COMPATIBLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
            SimpleDateFormat standardDateFormat = new SimpleDateFormat(ISO_8601_COMPATIBLE_DATE_FORMAT);
            standardDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            String parsedDate = standardDateFormat.format(new Date());

            System.out.println(parsedDate);// 2017-08-24T18:52:10.715+0000
        }

        // ISO format with your machine's time zone (PDT)
        {
            String ISO_8601_COMPATIBLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
            SimpleDateFormat standardDateFormat = new SimpleDateFormat(ISO_8601_COMPATIBLE_DATE_FORMAT);

            String parsedDate = standardDateFormat.format(new Date());

            System.out.println(parsedDate);// 2017-08-24T11:53:25.724-0700
        }

    }
}
