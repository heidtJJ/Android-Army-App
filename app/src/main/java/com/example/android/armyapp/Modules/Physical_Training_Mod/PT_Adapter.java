package com.example.android.armyapp.Modules.Physical_Training_Mod;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.android.armyapp.ModuleAdapter;
import com.example.android.armyapp.Quiz.QuizActivity;
import com.example.android.armyapp.R;

public class PT_Adapter extends ModuleAdapter {

    private static final String module = "ak";

    public PT_Adapter(Context context, int NUM_LESSONS_IN_MODULE) {
        super(context, NUM_LESSONS_IN_MODULE);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (position) {
            case 0:
                holder.videoImage.setImageResource(R.drawable.man);
                holder.itemTextView.setText(R.string.pushups);
                holder.takeQuizButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), QuizActivity.class);
                        String topic = "tas";
                        intent.putExtra("module", module);
                        intent.putExtra("topic", topic);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.videoImage.setImageResource(R.mipmap.icon_books);
                holder.itemTextView.setText("Situps");
                break;
            case 2:
                holder.videoImage.setImageResource(R.mipmap.ic_launcher);
                holder.itemTextView.setText("Run");
                break;
            case 3:
                holder.videoImage.setImageResource(R.mipmap.ic_launcher);
                holder.itemTextView.setText("Take a PT Test");
                break;
            default:
                holder.videoImage.setImageResource(R.mipmap.icon_resilient);
                break;
        }
    }
}