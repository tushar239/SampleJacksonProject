package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilterCapableSearchSpec.class, name = "filterCapableSearchSpec"),
        @JsonSubTypes.Type(value = BasicSearchSpec.class, name = "basicSearchSpec"),
        //@JsonSubTypes.Type(value = OfferSearchSpec.class, name = "offerSearchSpec"),
})
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class SearchSpec {

    public abstract BasicSearchSpec getBasicSearchSpec();

}
