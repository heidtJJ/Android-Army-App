package com.example.android.armyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private RelativeLayout armyKnowledgeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);

        view.findViewById(R.id.ArmyKnowledge_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Module_Knowledge.class));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.slide_left,R.anim.stay);
            }
        });

        view.findViewById(R.id.PT_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Module_PT.class));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.slide_left,R.anim.stay);
            }
        });

        view.findViewById(R.id.RM_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Module_RM.class));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.slide_left,R.anim.stay);
            }
        });

        view.findViewById(R.id.resiliency_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Module_Resiliency.class));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.slide_left,R.anim.stay);
            }
        });

        view.findViewById(R.id.returnFromBasic_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Module_ReturnFromBasic.class));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.slide_left,R.anim.stay);
            }
        });


        return view;
    }

}
