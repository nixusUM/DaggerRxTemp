package com.example.nlbochas.daggerrxtemp.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyCake implements Parcelable {

    private int id;
    private String title;
    private String previewDescription;
    private String detailDescription;
    private String imageUrl;

    public MyCake() {
    }

    protected MyCake(Parcel in) {
        id = in.readInt();
        title = in.readString();
        previewDescription = in.readString();
        detailDescription = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<MyCake> CREATOR = new Creator<MyCake>() {
        @Override
        public MyCake createFromParcel(Parcel in) {
            return new MyCake(in);
        }

        @Override
        public MyCake[] newArray(int size) {
            return new MyCake[size];
        }
    };

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPreviewDescription() {
            return previewDescription;
        }

        public void setPreviewDescription(String previewDescription) {
            this.previewDescription = previewDescription;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(previewDescription);
        dest.writeString(detailDescription);
        dest.writeString(imageUrl);
    }
}

