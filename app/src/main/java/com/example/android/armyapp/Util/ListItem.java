package com.example.android.armyapp.Util;

import android.widget.ImageView;

public class ListItem {

    private ImageView image;

    ListItem(ImageView image){
        this.image = image;
    }

    public ImageView getImage(){
        return this.image;
    }
}
