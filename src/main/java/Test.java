import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Tushar Chokshi @ 1/15/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String str = "{\n  \"took\" : 2,\n  \"timed_out\" : false,\n  \"_shards\" : {\n    \"total\" : 1,\n    \"successful\" : 1,\n    \"failed\" : 0\n  },\n  \"hits\" : {\n    \"total\" : 3,\n    \"max_score\" : null,\n    \"hits\" : [ {\n      \"_index\" : \"cdk-incentives-v1\",\n      \"_type\" : \"incentives\",\n      \"_id\" : \"test-automation-family:en_US:37081640\",\n      \"_score\" : null,\n      \"_source\":{\"subTitle\":\"new offer created for test - 0\",\"locale\":\"en_US\",\"incentiveId\":37081640,\"webId\":\"test-automation-family\"},\n      \"sort\" : [ \"test-automation-family\", 1449849215000 ]\n    }, {\n      \"_index\" : \"cdk-incentives-v1\",\n      \"_type\" : \"incentives\",\n      \"_id\" : \"test-automation-family:en_US:37081630\",\n      \"_score\" : null,\n      \"_source\":{\"subTitle\":\"new offer created for test - 1\",\"locale\":\"en_US\",\"incentiveId\":37081630,\"webId\":\"test-automation-family\"},\n      \"sort\" : [ \"test-automation-family\", 1449849214000 ]\n    }, {\n      \"_index\" : \"cdk-incentives-v1\",\n      \"_type\" : \"incentives\",\n      \"_id\" : \"test-automation-family:en_US:37081620\",\n      \"_score\" : null,\n      \"_source\":{\"subTitle\":\"new offer created for test - 2\",\"locale\":\"en_US\",\"incentiveId\":37081620,\"webId\":\"test-automation-family\"},\n      \"sort\" : [ \"test-automation-family\", 1449849214000 ]\n    } ]\n  }\n}";
        System.out.println(str);
        JsonNode jsonNode = new ObjectMapper().readValue(str, JsonNode.class);
        System.out.println(jsonNode.get("took"));
        System.out.println(jsonNode.toString());

    }
}
