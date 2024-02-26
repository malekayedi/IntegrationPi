package com.example.gestion_produit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.adapter.MatchplayerAdapter;
import com.example.gestion_produit.model.Matche;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity  extends AppCompatActivity {
    RecyclerView Matcheplayercycler;
    MatchplayerAdapter matchplayerAdapter;
    private List<Matche> matchList=new ArrayList<Matche>();
    ImageView imageplayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdetail);
        Matcheplayercycler=findViewById(R.id.MatchPlayerRecylerr);
        imageplayer=findViewById(R.id.imageplayer);
        Matcheplayercycler.setLayoutManager(new LinearLayoutManager(this));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,6,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,4,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,2,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,3,0,"12/12/2001"));
        matchplayerAdapter = new MatchplayerAdapter(getApplicationContext(), matchList);
        Matcheplayercycler.setAdapter(matchplayerAdapter);
        imageplayer.setImageResource(R.drawable.malek);
    }
}
