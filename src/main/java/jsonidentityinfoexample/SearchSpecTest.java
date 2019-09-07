package jsonidentityinfoexample;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchSpecTest {
    public static void main(String[] args) throws IOException {

        {
            OwnerSearchSpec ownerSearchSpec = new OwnerSearchSpec();
            ownerSearchSpec.setWebId("test-webId");

            ObjectMapper om = new ObjectMapper();

            String ownerSearchSpecSerialized = om.writeValueAsString(ownerSearchSpec);
            System.out.println(ownerSearchSpecSerialized);


            OwnerSearchSpec ownerSearchSpecDeSerialized = om.readValue(ownerSearchSpecSerialized, OwnerSearchSpec.class);
            System.out.println(ownerSearchSpecDeSerialized);


        }

        {
            OwnerSearchSpec ownerSearchSpec = new OwnerSearchSpec();
            ownerSearchSpec.setWebId("test-webId");

            SearchSpec filterCapableSearchSpec = new FilterCapableSearchSpec(ownerSearchSpec);

            ObjectMapper om = new ObjectMapper();

            String filterCapableSearchSpecSerialized = om.writeValueAsString(filterCapableSearchSpec);
            System.out.println(filterCapableSearchSpecSerialized);


            FilterCapableSearchSpec filterCapableSearchSpecDeSerialized = om.readValue(filterCapableSearchSpecSerialized, FilterCapableSearchSpec.class);
            System.out.println(filterCapableSearchSpecDeSerialized);

        }

        {
            OfferSearchSpec offerSearchSpec = new OfferSearchSpec();
            offerSearchSpec.setOfferId("o1");

            SearchSpec filterCapableSearchSpec = new FilterCapableSearchSpec(offerSearchSpec);

            ObjectMapper om = new ObjectMapper();

            String filterCapableSearchSpecSerialized = om.writeValueAsString(filterCapableSearchSpec);
            System.out.println(filterCapableSearchSpecSerialized);

            FilterCapableSearchSpec filterCapableSearchSpecDeSerialized = om.readValue(filterCapableSearchSpecSerialized, FilterCapableSearchSpec.class);
            System.out.println(filterCapableSearchSpecDeSerialized);

        }

    }
}
