package com.example.android.armyapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class KnowledgeAdapter extends ModuleAdapter {

    public KnowledgeAdapter(Context context) {
        super(context);
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
                        String module = "ak";
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