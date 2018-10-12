package com.example.android.armyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Module_Knowledge extends Module_Starter {

    // This is the only function needed to populate content in the Army Knowledge Module
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_module);

        Toolbar appBar = findViewById(R.id.knowledge_app_bar);
        appBar.setTitle(R.string.army_knowledge);
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.knowledge_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (int i = 0; i < 8; ++i) {

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
