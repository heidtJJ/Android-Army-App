package com.example.android.armyapp.Modules.Army_Knowledge_Mod;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.android.armyapp.ModuleAdapter;
import com.example.android.armyapp.Quiz.QuizActivity;
import com.example.android.armyapp.R;

public class Knowledge_Adapter extends ModuleAdapter {

    private static  final String module = "ak";

    public Knowledge_Adapter(Context context, int NUM_LESSONS_IN_MODULE) {
        super(context, NUM_LESSONS_IN_MODULE);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (position) {
            case 0:
                holder.videoImage.setImageResource(R.drawable.man);
                holder.itemTextView.setText("The Army Song");
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
                holder.itemTextView.setText("Army Values");
                break;
            case 2:
                holder.videoImage.setImageResource(R.mipmap.ic_launcher);
                holder.itemTextView.setText("Soldier's Creed");
                break;
            case 3:
                holder.videoImage.setImageResource(R.mipmap.ic_launcher);
                holder.itemTextView.setText("Army Ranks");
                holder.takeQuizButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), QuizActivity.class);
                        String module = "ak";
                        String topic = "ar";
                        intent.putExtra("module", module);
                        intent.putExtra("topic", topic);
                        v.getContext().startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.videoImage.setImageResource(R.mipmap.icon_army_white);
                holder.itemTextView.setText("Phonetic Alphabet");
                break;
            case 5:
                holder.videoImage.setImageResource(R.mipmap.icon_army_white);
                holder.itemTextView.setText("Warrior Ethos");
                break;
            case 6:
                holder.videoImage.setImageResource(R.mipmap.icon_army_white);
                holder.itemTextView.setText("Important Army Dates");
                break;
            case 7:
                holder.videoImage.setImageResource(R.mipmap.icon_army_white);
                holder.itemTextView.setText("Phonetic Alphabet");
                break;
            default:
                holder.videoImage.setImageResource(R.mipmap.icon_resilient);
                break;
        }
    }
}