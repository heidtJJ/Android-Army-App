package com.example.android.armyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    protected Context context;

    public ModuleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        Log.i("viewType", String.valueOf(viewType));

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button watchVideoButton;
        public Button takeQuizButton;
        public ImageView videoImage;
        public TextView itemTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            watchVideoButton = (Button) itemView.findViewById(R.id.watchVideoButton);
            takeQuizButton = (Button) itemView.findViewById(R.id.takeQuizButton);
            videoImage = (ImageView) itemView.findViewById(R.id.imageView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }
    }
}