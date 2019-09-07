package jsonidentityinfoexample;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("offerSearchSpec")
public class OfferSearchSpec extends BasicSearchSpec {
    private String offerId;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
