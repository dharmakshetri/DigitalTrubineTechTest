package co.digitaltrubinetechtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.digitaltrubinetechtest.R;
import co.digitaltrubinetechtest.model.Ad;

/**
 * Created by Dharma Kshetri on 2/7/17.
 */


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Ad> adsList= new ArrayList<>();
    private Context mContext;
    static OnItemClickListener mItemClickListener;
    //Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.mainHolder)
        public RelativeLayout placeHolder;

        @BindView(R.id.tv_ad_name)
        public TextView tvAdName;

        @BindView(R.id.tv_no_of_rating)
        public TextView tvNoOfRating;

        @BindView(R.id.tv_minOs)
        public TextView tvMinOs;

        @BindView(R.id.iv_product_thumbnail)
        public ImageView ivPrductThumbnail;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            placeHolder.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    //Provide a suitable constructor
    public ProductAdapter(Context context){
        mContext = context;
    }
    public void setAd(List<Ad> aList) {
        adsList.clear();
        adsList.addAll(aList);
        notifyDataSetChanged();
    }
    //Create new views (invoked by the layout manager)
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Creating a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_items,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace the contents of a view (invoked by the layout manager
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {

        // - replace the contents of the view with that element

        Ad ad = adsList.get(position);
        holder.tvAdName.setText(ad.getProductName());
        holder.tvNoOfRating.setText(ad.getNumberOfRatings());
        holder.tvMinOs.setText(ad.getMinOSVersion());

        Glide.with(mContext)
                .load(ad.getProductThumbnail())
                .into(holder.ivPrductThumbnail);
    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }
}

