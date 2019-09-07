package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ownerSearchSpec")
public class OwnerSearchSpec extends BasicSearchSpec {
    private String webId;

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

}
