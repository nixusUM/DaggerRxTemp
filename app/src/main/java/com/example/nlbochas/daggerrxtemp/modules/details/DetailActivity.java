package com.example.nlbochas.daggerrxtemp.modules.details;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nlbochas.daggerrxtemp.R;
import com.example.nlbochas.daggerrxtemp.base.BaseActivity;
import com.example.nlbochas.daggerrxtemp.helper.ImageHandler;
import com.example.nlbochas.daggerrxtemp.mvp.model.MyCake;

import butterknife.Bind;

public class DetailActivity extends BaseActivity {

    public static final String CAKE = "cake";

    @Bind(R.id.cakeImage) protected ImageView mCakeImage;
    @Bind(R.id.cakeTitle) protected TextView mCakeTitle;
    @Bind(R.id.cakeDescription) protected TextView mCakeDescription;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCakeImage.setTransitionName("cakeImageAnimation");
        }

        showBackArrow();

        MyCake myCake = intent.getParcelableExtra(CAKE);
        setTitle("Cake Detail");

        mCakeTitle.setText(myCake.getTitle());
        mCakeDescription.setText(myCake.getDetailDescription());

        Glide.with(this).load(myCake.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(mCakeImage));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}