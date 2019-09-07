package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwnerSearchSpec.class, name = "ownerSearchSpec"),
        @JsonSubTypes.Type(value = OfferSearchSpec.class, name = "offerSearchSpec"),
})
@JsonTypeName("basicSearchSpec")
public abstract class BasicSearchSpec extends SearchSpec {

    @Override
    @JsonIgnore
    public BasicSearchSpec getBasicSearchSpec() {
        return this;
    }

}
