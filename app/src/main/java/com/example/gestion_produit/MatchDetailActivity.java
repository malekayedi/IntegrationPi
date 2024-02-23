package com.example.gestion_produit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchDetailActivity extends AppCompatActivity {
ImageView img1,img2;
TextView name1,name2,score1,score2,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        img1=findViewById(R.id.imgplayerdt1);
        img2=findViewById(R.id.imgplayerdt2);
        name1=findViewById(R.id.nameplayer1);
        name2=findViewById(R.id.nameplayer2);
        score1=findViewById(R.id.scoreplay1);
        score2=findViewById(R.id.scoreplay2);
        date=findViewById(R.id.date);
        Intent intent=getIntent();
        String itemname1=intent.getStringExtra("name1");
        String itemname2=intent.getStringExtra("name2");
        int itemimg1=intent.getIntExtra("image1",0);
        int itemimg2=intent.getIntExtra("image2",0);
        int itemscore1=intent.getIntExtra("score1",0);
        int itemscore2=intent.getIntExtra("score2",0);
        String itemdate=intent.getStringExtra("date");
        name1.setText(itemname1);
        name2.setText(itemname2);
        score1.setText(String.valueOf(itemscore1));
        score2.setText(String.valueOf(itemscore2));
        date.setText(itemdate);
        img1.setImageResource(itemimg1);
        img2.setImageResource(itemimg2);






    }
}