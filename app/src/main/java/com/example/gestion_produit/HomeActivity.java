package com.example.gestion_produit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.adapter.MatchAdapter;
import com.example.gestion_produit.adapter.PlayerAdapter;
import com.example.gestion_produit.model.Match;
import com.example.gestion_produit.model.Player;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // Now here we will add some dummy data in our model class
    RecyclerView match_recycler, TopPlayerRecycler;
    MatchAdapter matchAdapter;
    PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    List<Match> matchList = new ArrayList<>();
        matchList.add(new Match("derbi el 3asma", "12/12/2002", "GAZACOURT", R.drawable.matchten));
        matchList.add(new Match("FINAL", "09 jan", "From GAZACOURT", R.drawable.kaysoun));
        matchList.add(new Match("SEMI FINAL", "15 mars", "GAZACOURT", R.drawable.badir));
        matchList.add(new Match("Nilgiri Hills", "19 mars", "GAZACOURT", R.drawable.matchten));

    setRecentRecycler(matchList);

    List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("9ays SAID", "India", "128", R.drawable.kaysoun));
        playerList.add(new Player("BADIIR", "TUN", "128", R.drawable.badir));
        playerList.add(new Player("LWESS", "TURKEY", "112", R.drawable.matchten));
        playerList.add(new Player("MALIK", "USA", "111", R.drawable.matchten));
        playerList.add(new Player("YASSINOS", "CHINA", "110", R.drawable.matchten));

    setTopPlacesRecycler(playerList);
}

    private void setRecentRecycler(List<Match> matchList) {

        match_recycler = findViewById(R.id.match_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        match_recycler.setLayoutManager(layoutManager);
        matchAdapter = new MatchAdapter(this, matchList);
        match_recycler.setAdapter(matchAdapter);

    }

    private void setTopPlacesRecycler(List<Player> playerList) {

        TopPlayerRecycler = findViewById(R.id.TopPlayerRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        TopPlayerRecycler.setLayoutManager(layoutManager);
        playerAdapter = new PlayerAdapter(this, playerList);
        TopPlayerRecycler.setAdapter(playerAdapter);
    }
}
