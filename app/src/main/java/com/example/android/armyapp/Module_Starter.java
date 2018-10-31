package com.example.android.armyapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

public abstract class Module_Starter extends AppCompatActivity {
    protected int NUM_LESSONS_IN_MODULE = -1;

    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_right);
    }

}