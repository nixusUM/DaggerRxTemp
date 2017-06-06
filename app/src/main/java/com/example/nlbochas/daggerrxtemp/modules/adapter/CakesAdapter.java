package com.example.nlbochas.daggerrxtemp.modules.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nlbochas.daggerrxtemp.R;
import com.example.nlbochas.daggerrxtemp.mvp.model.MyCake;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CakesAdapter extends RecyclerView.Adapter<CakesAdapter.CakesHolder> {

    private LayoutInflater layoutInflater;
    private List<MyCake> myCakeList = new ArrayList<>();

    public CakesAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public CakesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CakesHolder(layoutInflater.inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(CakesHolder holder, int position) {
        holder.bind(myCakeList.get(position));
    }

    @Override
    public int getItemCount() {
        return myCakeList.size();
    }

    public void setMyCakeList(List<MyCake> myCakeList) {
        this.myCakeList = myCakeList;
        notifyDataSetChanged();
    }

    public void clearCakeList() {
        this.myCakeList.clear();
        notifyDataSetChanged();
    }

    protected class CakesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.cake_icon) protected ImageView cakeIcon;
        @Bind(R.id.txt_title) protected TextView cakeTitle;
        @Bind(R.id.txt_preview_description) protected TextView cakePreviewDescription;

        private Context context;
        private MyCake myCake;

        protected CakesHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        protected void bind(MyCake cake) {
            myCake = cake;
            cakeTitle.setText(myCake.getTitle());
            cakePreviewDescription.setText(myCake.getPreviewDescription());

            Glide.with(context).load(myCake.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(cakeIcon);
        }

        @Override
        public void onClick(View v) {
            if (mCakeClickListener != null) {
                mCakeClickListener.onClick(cakeIcon, myCake, getAdapterPosition());
            }
        }
    }

    public void setCakeClickListener(OnCakeClickListener listener) {
        mCakeClickListener = listener;
    }

    private OnCakeClickListener mCakeClickListener;
    public interface OnCakeClickListener {
        void onClick(View v, MyCake myCake, int position);
    }
}
