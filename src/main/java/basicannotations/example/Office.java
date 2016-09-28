package basicannotations.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.ArrayList;
import java.util.List;

/*
The default Jackson property detection rules will find:

All ''public'' fields
All ''public'' getters ('getXxx()' methods)
All setters ('setXxx(value)' methods), ''regardless of visibility'')

But if this does not work, you can change visibility levels by using annotation @JsonAutoDetect.
If you wanted, for example, to auto-detect ALL fields  you could do it by setting Visibility to ANY.
 */

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
// similarly, you can also define creatorVisibility, isGetterVisibility, getterVisibility and setterVisibility
// different types of Visibility is NON_PRIVATE, PROTECTED_AND_PUBLIC, PUBLIC_ONLY, NONE, DEFAULT (inherited)
public class Office {
    private String name = "CDK";

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY) // NON_EMPTY means not null and not empty
    private String emptyProperty;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private Integer nullProperty;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonPropertyDescription("empty list") // Currently used to populate the description field in generated JSON
    private List<String> emptyList = new ArrayList<String>(); // it is empty, but not null, so it will be a part of json

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<String> nullList;


    /*
    @JsonAnySetter, @JsonAnyGetter :
    These annotations works as a Catch-All and are applied on Getters/Setter working with a Map.
    If there is any JSON value which is not mapped to a property in POJO, then that value can be caught by @JsonAnySetter, and stored (deserialized) into Map.
    Similarly the values which are stored into Map can be serialized back to JSON using @JsonAnyGetter.

    private Map<String, Object> otherProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> any() {
        return otherProperties;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        otherProperties.put(name, value);
    }
     */

}
