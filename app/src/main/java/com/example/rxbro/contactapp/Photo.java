package com.example.rxbro.contactapp;

import android.net.Uri;

/**
 * Created by rxbro on 5/31/2017.
 */

public class Photo {
    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(Uri storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    private String title;
    private Uri storageLocation;
    private String tag1;
    private String tag2;
    private String tag3;
}
