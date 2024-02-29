package com.example.gestion_produit;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    TextView ruletext1,ruletext2,ruletext3,ruletext4,ruletext5;
    LinearLayout layout1,layout2,layout3,layout4,layout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        ruletext1 = findViewById(R.id.rule1);
        layout1 =findViewById(R.id.layout1);
        layout1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ruletext2 = findViewById(R.id.rule2);
        layout2 =findViewById(R.id.layout2);
        layout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ruletext3 = findViewById(R.id.rule3);
        layout3 =findViewById(R.id.layout3);
        layout3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ruletext4 = findViewById(R.id.rule4);
        layout4 =findViewById(R.id.layout4);
        layout4.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        ruletext5 = findViewById(R.id.rule5);
        layout5 =findViewById(R.id.layout5);
        layout5.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
    }

    public void expand(View view) {
        int v = (ruletext1.getVisibility() == View.GONE)?View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout1, new AutoTransition());
        ruletext1.setVisibility(v);
    }

    public void expand2(View view) {
        int v = (ruletext2.getVisibility() == View.GONE)?View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout2, new AutoTransition());
        ruletext2.setVisibility(v);
    }

    public void expand3(View view) {
        int v = (ruletext3.getVisibility() == View.GONE)?View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout3, new AutoTransition());
        ruletext3.setVisibility(v);
    }

    public void expand4(View view) {
        int v = (ruletext4.getVisibility() == View.GONE)?View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout4, new AutoTransition());
        ruletext4.setVisibility(v);
    }

    public void expand5(View view) {
        int v = (ruletext5.getVisibility() == View.GONE)?View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout5, new AutoTransition());
        ruletext5.setVisibility(v);
    }
}