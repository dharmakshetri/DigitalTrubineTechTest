package co.digitaltrubinetechtest.Network;

import co.digitaltrubinetechtest.model.Ads;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dharma Kshetri on 2/7/17.
 */

public interface APIService {

    // get the ads
    @GET("/getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10")
    rx.Observable<Ads> getAds();

    @GET("/getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10")
    Call<Ads> getAdsDetails();


    @GET("/getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10")
    rx.Observable<Ads> getFinalAds(@Query("lname") String lname);
}
