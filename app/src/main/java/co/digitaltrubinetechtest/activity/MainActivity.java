package co.digitaltrubinetechtest.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import co.digitaltrubinetechtest.Network.APIClient;
import co.digitaltrubinetechtest.Network.APIService;
import co.digitaltrubinetechtest.R;
import co.digitaltrubinetechtest.adapter.ProductAdapter;
import co.digitaltrubinetechtest.bus.MessageEvent;
import co.digitaltrubinetechtest.model.Ad;
import co.digitaltrubinetechtest.model.Ads;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dharma Kshetri on 2/7/16.
 */
public class MainActivity extends BaseActivity {
    private static final int LAYOUT = R.layout.activity_main;
    public static final String LAST_NAME="kshetri";
    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Ad> adsList= new ArrayList<>();
    private ProductAdapter productAdapter;

    private TextView mNoResultsIndicator;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        creatingLayouts();
    }


    private void creatingLayouts() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        setTitle(getResources().getString(R.string.app_name));

        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.product_list);
        mNoResultsIndicator = (TextView) findViewById(R.id.no_results_indicator);
        //Use this setting to improve performance if you know that changes in
        //the content do not change the layout size of the RecyclerView
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }
//        //using a linear layout manager
        //intializing an arraylist called songlist
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initializing adapter
        productAdapter = new ProductAdapter(this);

        //specifying an adapter to access data, create views and replace the content
        mRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        productAdapter.setOnItemClickListener(onItemClickListener);


        showProgressDialog();
        loadProducts();
        //loadTest();
        loadFinalProducts(LAST_NAME);
        hideProgressDialog();
    }

    // without rx java
    private void loadTest() {
        APIService ipService= APIClient.getTestClient().create(APIService.class);

        Call<Ads> call = ipService.getAdsDetails();
        showProgressDialog();
        call.enqueue(new Callback<Ads>() {
            @Override
            public void onResponse(Call<Ads> call, Response<Ads> response) {
                Log.e(TAG, " response " +response.body().getAdList().size());
                List<Ad> ads = response.body().getAdList();
                adsList=ads;
                handleShowResults(ads);
            }

            @Override
            public void onFailure(Call<Ads> call, Throwable t) {
                Log.e(TAG, " onFailure" );
                t.printStackTrace();
            }
        });
    }


    // with RxJava
    private void loadProducts() {

       Observable<Ads> call = APIClient.getRetrofitAPIClient().getAds();
        subscription = call
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Ads>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG," onError ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Ads ads) {
                        handleShowResults(ads.getAdList());
                        adsList=ads.getAdList();
                    }
                });
    }


    //RxJava with lname paramter
    // with RxJava
    private void loadFinalProducts(String lname) {

        Observable<Ads> call = APIClient.getRetrofitAPIClient().getFinalAds(lname);
        subscription = call
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Ads>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG," onError ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Ads ads) {
                        handleShowResults(ads.getAdList());
                        adsList=ads.getAdList();
                    }
                });
    }

    private void handleShowResults(List<Ad> ads) {
        if (ads.isEmpty()) {
            showNoResults();
        } else {
            showResults(ads);
        }
    }

    private void showNoResults() {
        mNoResultsIndicator.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void showResults(List<Ad> ads) {
        mNoResultsIndicator.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        productAdapter.setAd(ads);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }


    ProductAdapter.OnItemClickListener onItemClickListener = new ProductAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent transitionIntent = new Intent(getApplicationContext(), ProductDetailActivity.class);
            EventBus.getDefault().postSticky(new MessageEvent(adsList.get(position)));
            startActivity(transitionIntent);
        }
    };

}
