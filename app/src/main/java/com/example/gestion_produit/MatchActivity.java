package com.example.gestion_produit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gestion_produit.adapter.MatcheAdapter;
import com.example.gestion_produit.model.Matche;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity implements RecycleViewOnItemClick {

    RecyclerView Matchrecycler;
    MatcheAdapter matcheAdapter;
    private List<Matche> matchList=new ArrayList<Matche>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Matchrecycler=findViewById(R.id.MatchRecyler);
        Matchrecycler.setLayoutManager(new LinearLayoutManager(this));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,6,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,4,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,2,0,"12/12/2001"));
        matchList.add(new Matche("malek","badri",R.drawable.test1,R.drawable.test2,3,0,"12/12/2001"));
        matcheAdapter = new MatcheAdapter(getApplicationContext(), matchList,MatchActivity.this);
        Matchrecycler.setAdapter(matcheAdapter);

    }

    @Override
    public void onClick(int position) {

            // Récupérer le blog sélectionné à partir de la position
            Matche match = matchList.get(position);

            // Ouvrir l'activité de détail avec les informations du blog
            Intent intent = new Intent(MatchActivity.this, MatchDetailActivity.class);
            intent.putExtra("name1",match.getNameplayer1());
            intent.putExtra("name2",match.getNameplayer2());
            intent.putExtra("image1",match.getImageplayer1());
            intent.putExtra("image2",match.getImageplayer2());
            intent.putExtra("score1",match.getScoreplayer1());
            intent.putExtra("score2",match.getScoreplayer2());
            intent.putExtra("date",match.getDateMatch());



            startActivity(intent);
        }


}