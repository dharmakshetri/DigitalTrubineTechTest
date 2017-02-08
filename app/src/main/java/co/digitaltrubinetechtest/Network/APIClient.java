package co.digitaltrubinetechtest.Network;


import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Dharma Kshetri on 2/7/17.
 */

public class APIClient {

    public static final String API_BASE_URL="http://ads.appia.com";
    public static final String API_PARAMETER="getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10";

    private static APIService apiService;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static Retrofit retrofit=null;
    // get retrofit api services
    public static  APIService getRetrofitAPIClient(){
        if(apiService == null){
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
            apiService = retrofit.create(APIService.class);
        }
        return  apiService;
    }

    public static  Retrofit getTestClient(){
        if(retrofit == null){
            retrofit=new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }

}
