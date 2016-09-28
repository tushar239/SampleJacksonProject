package jsonserializer.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Tushar Chokshi @ 4/26/15.
 */
public class OfferJsonSerializer extends JsonSerializer<Offer> {

    @Override
    public void serialize(Offer offer, JsonGenerator jgen, SerializerProvider serializers) throws IOException, JsonProcessingException {


        /*

        JsonFactory jfactory = new JsonFactory();
        //  An OutputStreamWriter is a bridge from character streams to byte streams
        Writer writer = new OutputStreamWriter(System.out);
        jgen = jfactory.createGenerator(writer);
        */


        /*
        ObjectMapper mapper = (ObjectMapper)jgen.getCodec();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        mapper.writerWithView(View.Summary.class);

        //mapper.writeValue(jgen, offer);
        //jgen.setCodec(mapper);
*/


        jgen.writeStartObject();
        jgen.writeNumberField("offer_id", offer.getId());
        jgen.writeStringField("offer_type", offer.getType());
        jgen.writeEndObject();

    }
}
