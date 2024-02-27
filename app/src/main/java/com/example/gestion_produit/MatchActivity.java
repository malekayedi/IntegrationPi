package com.example.gestion_produit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gestion_produit.adapter.MatcheAdapter;
import com.example.gestion_produit.model.Matche;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity implements RecycleViewOnItemClick {

    RecyclerView Matchrecycler;
    MatcheAdapter matcheAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private List<Matche> matchList=new ArrayList<Matche>();
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
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
        drawerLayout =findViewById(R.id.drawerlayoutmatch);
        navigationView=findViewById(R.id.nav_view_match);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.Home) {
                    Toast.makeText(MatchActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                    //   startActivity(new Intent(HomeActivity.this, CartActivity.class));
                } else if (id == R.id.Product) {
                    startActivity(new Intent(MatchActivity.this, MainActivity.class));

                    //  Toast.makeText(HomeActivity.this, "Product selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.Matches) {
                    startActivity(new Intent(MatchActivity.this, MatchActivity.class));

                    // startActivity(new Intent(HomeActivity.this, com.example.gestion_produit.News.MainActivity.class));
                    Toast.makeText(MatchActivity.this, "Matches selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.News) {
                    startActivity(new Intent(MatchActivity.this, com.example.gestion_produit.News.MainActivity.class));

                    //  Toast.makeText(HomeActivity.this, "News selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.about) {
                    Toast.makeText(MatchActivity.this, "About selected", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.logout) {
                    Toast.makeText(MatchActivity.this, "Logout selected", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
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
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }

}