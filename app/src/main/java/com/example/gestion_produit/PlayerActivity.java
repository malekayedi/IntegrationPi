package com.example.gestion_produit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.adapter.MatcheAdapter;
import com.example.gestion_produit.adapter.MatchplayerAdapter;
import com.example.gestion_produit.front.Constants;
import com.example.gestion_produit.front.GitHubService;
import com.example.gestion_produit.model.Matche;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerActivity  extends AppCompatActivity {
    RecyclerView Matcheplayercycler;
    MatchplayerAdapter matchplayerAdapter;
    TextView nameplayer;
    private List<Matche> matchList=new ArrayList<Matche>();
    ImageView imageplayer;
    private String url= Constants.BASE_URL;
    RecyclerView Matchrecycler;
    MatcheAdapter matcheAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerdetail);
        Matcheplayercycler=findViewById(R.id.MatchPlayerRecylerr);
        imageplayer=findViewById(R.id.imageplayer);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Matcheplayercycler.setLayoutManager(new LinearLayoutManager(this));


        GitHubService blogApi = retrofit.create(GitHubService.class);

        // Retrieve blogs from API and update RecyclerView
        Call<List<Matche>> call = blogApi.getAllMatch();

        call.enqueue(new Callback<List<Matche>>() {
            @Override
            public void onResponse(Call<List<Matche>> call, Response<List<Matche>> response) {
                if (response.isSuccessful()) {
                    matchList = response.body();

                    // Create and set adapter for RecyclerView
                    matcheAdapter = new MatcheAdapter(getApplicationContext(), matchList);
                    Matcheplayercycler.setAdapter(matcheAdapter);
                } else {
                    // Handle API error
                    Log.e("MainActivity", "Error retrieving Matches from API");
                }
            }

            @Override
            public void onFailure(Call<List<Matche>> call, Throwable t) {
                // Handle network failure
                Log.e("MainActivity", "Failed to retrieve Matches from API", t);
            }
        });
        imageplayer.setImageResource(R.drawable.malek);

        String name=getIntent().getStringExtra("name1");
        nameplayer=findViewById(R.id.nameplayer);
        nameplayer.setText(name);

    }
}
