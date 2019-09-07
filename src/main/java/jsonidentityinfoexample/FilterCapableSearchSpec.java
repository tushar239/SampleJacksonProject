package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("filterCapableSearchSpec")
public class FilterCapableSearchSpec extends SearchSpec {
    @JsonProperty("basicSearchSpec")
    private BasicSearchSpec basicSearchSpec;

    @JsonCreator
    public FilterCapableSearchSpec(@JsonProperty("basicSearchSpec") BasicSearchSpec basicSearchSpec) {
        this.basicSearchSpec = basicSearchSpec;
    }

    @Override
    public BasicSearchSpec getBasicSearchSpec() {
        return basicSearchSpec;
    }

}
