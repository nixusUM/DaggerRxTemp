package com.example.nlbochas.daggerrxtemp.helper;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class ImageHandler extends SimpleTarget<GlideDrawable> {

    private ImageView imageIcon;

    public ImageHandler(ImageView imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
        imageIcon.setImageDrawable(resource);
    }
}
