package com.example.android.armyapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;


public class Module_Knowledge extends Module_Starter {

    // This is the only function needed to populate content in the Army Knowledge Module
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        // Set up the app bar
        Toolbar appBar = findViewById(R.id.knowledge_app_bar);
        appBar.setTitle(R.string.army_knowledge);
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.knowledge_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NUM_LESSONS_IN_MODULE = 8;
        for (int i = 0; i < NUM_LESSONS_IN_MODULE; ++i) {

            String youtubeVideoURL = "https://www.youtube.com/watch?v=fhWaJi1Hsfo";
            String youtubeThumbnailUrl = YoutubeImgHelper.getImgUrlFromVideo(youtubeVideoURL);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher_round);

            /*// download the first thumbnail from youtube video and display it on imageView
            new DownloadImageTask(imageView)
                    .execute(youtubeThumbnailUrl);
            */
        }
        adapter = new KnowledgeAdapter(this);
        recyclerView.setAdapter(adapter);
    }

}
