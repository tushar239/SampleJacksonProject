package customserializerdeserializer.collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tushar Chokshi @ 8/24/17.
 */
public class Test {
    static class MyPair {
        private String first;
        private String second;
        private List<SubPair> subPairs;

        public MyPair(String first, String second, List<SubPair> subPairs) {
            this.first = first;
            this.second = second;
            this.subPairs = subPairs;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }

        public List<SubPair> getSubPairs() {
            return subPairs;
        }

        /*@Override
        public String toString() {
            return "MyPair{" +
                    "first='" + first + '\'' +
                    ", second='" + second + '\'' +
                    '}';
        }*/
    }

    static class SubPair {
        private String rank;

        public SubPair(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }
    }

    static class MyPairSerializer extends JsonSerializer<MyPair> {

        @Override
        public void serialize(MyPair value,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();

            if (value.getFirst() != null)
                jsonGenerator.writeStringField("first", value.getFirst());

            if (value.getSecond() != null)
                jsonGenerator.writeStringField("second", value.getSecond());

            if (value.getSubPairs() != null) {
                jsonGenerator.writeObjectField("subPairs", value.getSubPairs());// SubPair's serializer is automatically found by Jackson

            }
/*            if (value.getSubPairs() != null) {
                JsonSerializer<Object> valueSerializer = serializers.findValueSerializer(SubPair.class);
                for (SubPair subPair : value.getSubPairs()) {
                    valueSerializer.serialize(subPair, jsonGenerator, serializers);
                }
            }*/

            jsonGenerator.writeEndObject();
        }
    }

    static class MySubPairSerializer extends JsonSerializer<SubPair> {


        public void serialize(SubPair value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();

            if (value.getRank() != null)
                jsonGenerator.writeStringField("rank123", value.getRank());

            jsonGenerator.writeEndObject();
        }

    }

    static class MyPairDeSerializer extends JsonDeserializer<MyPair> {

        @Override
        public MyPair deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
            ObjectCodec codec = jsonParser.getCodec();
            JsonNode root = codec.readTree(jsonParser);

            final JsonNode firstNode = root.get("first");
            final JsonNode secondNode = root.get("second");

            final JsonNode subPairsNode = root.get("subPairs");

            List<SubPair> subPairs = new ArrayList<SubPair>();
            if (subPairsNode.isArray()) {
                for (JsonNode node : subPairsNode) {
                    subPairs.add(codec.treeToValue(node, SubPair.class)); // SubPair's Deserializer is automatically found by Jackson
                }
            } else {
                subPairs.add(codec.treeToValue(subPairsNode, SubPair.class));
            }

            //codec.treeToValue(subPairsNode, List<SubPair>.getClass())
            //List<SubPair> subPairs = codec.readValue(subPairsNode, new TypeReference<List<SubPair>>(){});

            //codec.readValue(subPairsNode, List.class)


            /*List<SubPair> subPairs = new ArrayList<SubPair>();
            if (subPairsNode != null) {
                for (JsonNode subPair : subPairsNode) {
                    JavaType jacksonType = ctx.getTypeFactory().constructType(SubPair.class);
                    JsonDeserializer<?> deserializer = ctx.findRootValueDeserializer(jacksonType);
                    final JsonParser theJsonParser = subPair.traverse(ctx.getParser().getCodec());
                    theJsonParser.nextToken();
                    final Object deserialize = deserializer.deserialize(theJsonParser, ctx);
                    subPairs.add((SubPair) deserialize);
                }
            }*/

            final MyPair myPair = new MyPair(firstNode.asText(), secondNode.asText(), subPairs);

            return myPair;
        }

    }

    static class SubPairDeSerializer extends JsonDeserializer<SubPair> {

        @Override
        public SubPair deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
            ObjectCodec codec = jsonParser.getCodec();
            JsonNode root = codec.readTree(jsonParser);

            final JsonNode rankNode = root.get("rank123");

            final SubPair subPair = new SubPair(rankNode.asText());

            return subPair;
        }

    }


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        final SimpleModule module = new SimpleModule();

        module.addSerializer(MyPair.class, new MyPairSerializer());
        module.addSerializer(SubPair.class, new MySubPairSerializer());

        module.addDeserializer(MyPair.class, new MyPairDeSerializer());
        module.addDeserializer(SubPair.class, new SubPairDeSerializer());

        mapper.registerModule(module);

        {

            List<SubPair> subPairs = new ArrayList<SubPair>();
            subPairs.add(new SubPair("9"));

            MyPair myPair = new MyPair("Tushar", "Chokshi", subPairs);

            String jsonResult = mapper.writeValueAsString(myPair);
            System.out.println(jsonResult);

            MyPair myPairDeserialized = mapper.readValue(jsonResult, MyPair.class);
            System.out.println(myPairDeserialized.getFirst()+", "+myPairDeserialized.getSecond());
            for (SubPair subPair : myPairDeserialized.getSubPairs()) {
                System.out.println(subPair.getRank());
            }

/*
            Map<MyPair, String> map = new HashMap<MyPair, String>();
            map.put(key, "Comedy");


            String jsonResult = mapper.writeValueAsString(map);
            System.out.println(jsonResult);// {"hashmap.Test$MyPair@483bf400" : "Comedy"}
*/
        }
    }
}
