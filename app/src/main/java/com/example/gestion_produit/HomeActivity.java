package com.example.gestion_produit;

import android.content.ClipData;
import android.content.Intent;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.adapter.MatchAdapter;
import com.example.gestion_produit.adapter.PlayerAdapter;
import com.example.gestion_produit.front.CartActivity;
import com.example.gestion_produit.model.Match;
import com.example.gestion_produit.model.Player;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // Now here we will add some dummy data in our model class
   DrawerLayout drawerLayout;
   NavigationView navigationView;
   ActionBarDrawerToggle actionBarDrawerToggle;

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
        setContentView(R.layout.activity_home);
  drawerLayout =findViewById(R.id.drawerlayout);
  navigationView=findViewById(R.id.nav_view);
  actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
  drawerLayout.addDrawerListener(actionBarDrawerToggle);
  actionBarDrawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

          int id = item.getItemId();

          if (id == R.id.Home) {
              Toast.makeText(HomeActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
           //   startActivity(new Intent(HomeActivity.this, CartActivity.class));
          } else if (id == R.id.Product) {
              startActivity(new Intent(HomeActivity.this, MainActivity.class));

            //  Toast.makeText(HomeActivity.this, "Product selected", Toast.LENGTH_SHORT).show();
          } else if (id == R.id.Matches) {
              startActivity(new Intent(HomeActivity.this, MatchActivity.class));

              // startActivity(new Intent(HomeActivity.this, com.example.gestion_produit.News.MainActivity.class));
             Toast.makeText(HomeActivity.this, "Matches selected", Toast.LENGTH_SHORT).show();
          } else if (id == R.id.News) {
              startActivity(new Intent(HomeActivity.this, com.example.gestion_produit.News.MainActivity.class));

            //  Toast.makeText(HomeActivity.this, "News selected", Toast.LENGTH_SHORT).show();
          } else if (id == R.id.about) {
              Toast.makeText(HomeActivity.this, "About selected", Toast.LENGTH_SHORT).show();
          } else if (id == R.id.logout) {
              Toast.makeText(HomeActivity.this, "Logout selected", Toast.LENGTH_SHORT).show();
          }

          drawerLayout.closeDrawer(GravityCompat.START);
          return true;
      }
  });
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
