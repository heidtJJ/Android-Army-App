package com.example.android.armyapp.Modules.Resiliency_Mod;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.armyapp.ModuleStarter;
import com.example.android.armyapp.R;

public class Module_Resiliency extends ModuleStarter {
    private final static int NUM_LESSONS_IN_MODULE = 5;

    // This is the only method needed to populate content in the Army Resiliency Module
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        // Set up the app bar
        Toolbar appBar = findViewById(R.id.module_app_bar);
        appBar.setTitle(R.string.resiliency);
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set up UI to match Army Knowledge module.
        ImageView headerImage = findViewById(R.id.Module_Image);// Module Image.
        headerImage.setImageResource(R.drawable.knowledge_photo);
        TextView moduleTitle = findViewById(R.id.Module_Title);// Module Title.
        moduleTitle.setText(getText(R.string.Resiliency_Mod_Title));
        TextView moduleDesc = findViewById(R.id.Module_Desc);// Module Description.
        moduleDesc.setText(getText(R.string.Resiliency_Mod_Desc));

        super.recyclerView = findViewById(R.id.module_recycler_view);
        super.recyclerView.setHasFixedSize(true);
        super.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*for (int i = 0; i < NUM_LESSONS_IN_MODULE; ++i) {

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher_round);

            // download the first thumbnail from youtube video and display it on imageView
            String youtubeVideoURL = "https://www.youtube.com/watch?v=fhWaJi1Hsfo";
            String youtubeThumbnailUrl = YoutubeImgHelper.getImgUrlFromVideo(youtubeVideoURL);
            new DownloadImageTask(imageView)
                    .execute(youtubeThumbnailUrl);
        }*/
        super.adapter = new Resiliency_Adapter(this, NUM_LESSONS_IN_MODULE);
        super.recyclerView.setAdapter(super.adapter);
    }
}
