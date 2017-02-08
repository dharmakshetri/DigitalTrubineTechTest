package co.digitaltrubinetechtest.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.digitaltrubinetechtest.R;
import co.digitaltrubinetechtest.bus.MessageEvent;
import co.digitaltrubinetechtest.model.Ad;


public class ProductDetailActivity extends BaseActivity {

    Ad ad;
    private Toolbar mToolbar;

    @BindView(R.id.imageProductThumbnail)
    ImageView imageProductThumbnail;

    @BindView(R.id.ivAverageRatingImageURL)
    ImageView ivAverageRatingImageURL;

    @BindView(R.id.tvProductDescription)
    TextView tvProductDescription;

    @BindView(R.id.tvNumberOfRatings)
    TextView tvNumberOfRatings;

    @BindView(R.id.tvRating)
    TextView tvRating;

    @BindView(R.id.tvMinOSVersion)
    TextView tvMinOSVersion;

    @BindView(R.id.tvCategoryName)
    TextView tvCategoryName;

    @BindView(R.id.fabClickProxyURL)
    FloatingActionButton fabClickProxyURL;

    @BindView(R.id.relMinOs)
    RelativeLayout relMinOs;

    @BindView(R.id.divMinOs)
    View divMinOs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        ButterKnife.bind(this);

        creatingLayouts();

    }

    private void creatingLayouts() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    // UI updates must run on MainThread
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
          ad= event.ad;
         setTitle(ad.getProductName());
         loadProductDetails(ad);
    }

    private void loadProductDetails(Ad ad) {
        Glide.with(this)
                .load(ad.getProductThumbnail())
                .into(imageProductThumbnail);

        Glide.with(this)
                .load(ad.getAverageRatingImageURL())
                .into(ivAverageRatingImageURL);

        tvProductDescription.setText(ad.getProductDescription());

        tvNumberOfRatings.setText("Downloads : "+ad.getNumberOfRatings());

        tvRating.setText("Rating : "+String.valueOf(ad.getRating()));

        if(ad.getMinOSVersion()!=null || !ad.getMinOSVersion().isEmpty()|| !ad.getMinOSVersion().equalsIgnoreCase("null")){
            relMinOs.setVisibility(View.VISIBLE);
            divMinOs.setVisibility(View.VISIBLE);
            tvMinOSVersion.setText("Avaiable: "+ad.getMinOSVersion());
        }else{
            relMinOs.setVisibility(View.GONE);
            divMinOs.setVisibility(View.GONE);
        }


        tvCategoryName.setText("Category: "+ ad.getCategoryName());

    }

    @OnClick(R.id.fabClickProxyURL)
    public void onClick(View view){
        Uri uri = Uri.parse(ad.getClickProxyURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
        @Override
        public void onStop() {
            super.onStop();
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }


}
