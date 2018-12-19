package com.example.android.armyapp.Modules.Physical_Training_Mod;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.armyapp.ModuleStarter;
import com.example.android.armyapp.R;

public class Module_PT extends ModuleStarter {
    private final static int NUM_LESSONS_IN_MODULE = 4;

    // This is the only method needed to populate content in the Army PT Module
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        // Set up the app bar
        Toolbar appBar = findViewById(R.id.module_app_bar);
        appBar.setTitle(R.string.physical_training);
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set up UI to match PT module.
        ImageView headerImage = findViewById(R.id.Module_Image);// Module Image.
        headerImage.setImageResource(R.drawable.pt_photo);

        TextView moduleTitle = findViewById(R.id.Module_Title);// Module Title.
        moduleTitle.setText(getText(R.string.PT_Mod_Title));

        TextView moduleDesc = findViewById(R.id.Module_Desc);// Module Description.
        moduleDesc.setText(getText(R.string.PT_Mod_Desc));


        super.recyclerView = findViewById(R.id.module_recycler_view);
        super.recyclerView.setHasFixedSize(true);
        super.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        super.adapter = new PT_Adapter(this,NUM_LESSONS_IN_MODULE);
        super.recyclerView.setAdapter(super.adapter);
    }

}
