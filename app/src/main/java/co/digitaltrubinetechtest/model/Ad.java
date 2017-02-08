package co.digitaltrubinetechtest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Dharma kshetri on 2/7/17.
 */

@Root
public class Ad {
    @Element
    public String appId;

    @Element
    public String averageRatingImageURL;



    @Element
    public double bidRate;

    @Element
    public String callToAction;

    @Element
    public int campaignDisplayOrder;

    @Element
    public  long campaignId;

    @Element
    public  int campaignTypeId;

    @Element
    public String categoryName;

    @Element
    public String clickProxyURL;

    @Element
    public long creativeId;

    @Element
    public boolean homeScreen;

    @Element
    public String impressionTrackingURL;

    @Element
    public boolean isRandomPick;

    @Element(required = false)
    public String minOSVersion;

    @Element
    public String numberOfRatings;

    @Element
    public  String productDescription;

    @Element
    public long productId;

    @Element
    public String productName;

    @Element
    public String productThumbnail;

    @Element
    public double rating;

    public Ad() {}


    public String getAppId() {
        return appId;
    }

    public String getAverageRatingImageURL() {
        return averageRatingImageURL;
    }

    public double getBidRate() {
        return bidRate;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public int getCampaignDisplayOrder() {
        return campaignDisplayOrder;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public int getCampaignTypeId() {
        return campaignTypeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getClickProxyURL() {
        return clickProxyURL;
    }

    public long getCreativeId() {
        return creativeId;
    }

    public boolean isHomeScreen() {
        return homeScreen;
    }

    public String getImpressionTrackingURL() {
        return impressionTrackingURL;
    }

    public boolean isRandomPick() {
        return isRandomPick;
    }

    public String getMinOSVersion() {
        return minOSVersion;
    }

    public String getNumberOfRatings() {
        return numberOfRatings;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public double getRating() {
        return rating;
    }

}