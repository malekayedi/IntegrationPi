package com.example.gestion_produit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion_produit.adapter.ProductAdapter;
import com.example.gestion_produit.adapter.ProductCatAdapter;
import com.example.gestion_produit.front.CartActivity;
import com.example.gestion_produit.front.Constants;
import com.example.gestion_produit.front.GitHubService;
import com.example.gestion_produit.model.ProductCategory;
import com.example.gestion_produit.model.Products;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ProductCatAdapter productCatAdapter;
RecyclerView productCatRecycler,prodItemrecycler;
ProductAdapter productAdapter;
private List<Products> productsList=new ArrayList<Products>();
private String url= Constants.BASE_URL;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

TextView buttoncart;
    private static final String TAG = "MainActivity";

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
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService blogApi = retrofit.create(GitHubService.class);

        // Retrieve blogs from API and update RecyclerView
        Call<List<Products>> call = blogApi.getAll();

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful()) {
                    productsList = response.body();
                    for (Products product : productsList) {
                        String imageUrl = product.getImageurl(); // Remplacez getImageUrl() par la méthode appropriée pour obtenir l'URL de l'image du produit
                        // Charger l'image à partir de l'URL et l'afficher dans votre RecyclerView

                        Target target = new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                // Mettre à jour l'image du produit dans votre modèle de produit
                                product.setBitmap(bitmap);
                                productAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                // Gérer les erreurs de chargement de l'image
                                Log.e("MainActivity", "Failed to load image: " + e.getMessage());
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {
                                // Optionnel : Vous pouvez afficher un placeholder pendant le chargement de l'image
                            }
                        };

                        Picasso.get().load(imageUrl).into(target);
                    }
                    // Create and set adapter for RecyclerView
                    productAdapter = new ProductAdapter(getApplicationContext(), productsList);
                    prodItemrecycler.setAdapter(productAdapter);
                } else {
                    // Handle API error
                    Log.e("MainActivity", "Error retrieving products from API");
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                // Handle network failure
                Log.e("MainActivity", "Failed to retrieve blogs from API", t);
            }
        });
         setProdItemrecycler(productsList);
         bottomnavigation();
        // Initialisation du drawerLayout et de la navigationView
        drawerLayout = findViewById(R.id.drawerlayoutproduct);
        navigationView = findViewById(R.id.nav_view_product);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        // Configuration du listener de la navigationView


    }

    // Configuration du bouton retour en arrière
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void bottomnavigation()
    {
        ImageView cartbtn=findViewById(R.id.cartbtn);
        cartbtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        buttoncart.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });

    }
    private void setProductRecycler(List<ProductCategory> productCategoryList)
    {
       // productCatRecycler=findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCatAdapter = new ProductCatAdapter(this,productCategoryList);
        productCatRecycler.setAdapter(productCatAdapter);
    }
    private void setProdItemrecycler(List<Products> productList)
    {
        prodItemrecycler=findViewById(R.id.productRecyler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        prodItemrecycler.setLayoutManager(layoutManager);
       productAdapter = new ProductAdapter(this,productList);
        prodItemrecycler.setAdapter(productAdapter);
        buttoncart=findViewById(R.id.buttoncart);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "Selected item ID: " + id);

        if (id == R.id.Home) {
            Log.i(TAG, "Home selected");
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else if (id == R.id.Product) {
            Log.i(TAG, "Product selected");
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.Matches) {
            Log.i(TAG, "Matches selected");
            startActivity(new Intent(MainActivity.this, MatchActivity.class));
        } else if (id == R.id.News) {
            Log.i(TAG, "News selected");
            startActivity(new Intent(MainActivity.this, com.example.gestion_produit.News.MainActivity.class));
        } else if (id == R.id.about) {
            Log.i(TAG, "About selected");
            Toast.makeText(MainActivity.this, "About selected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Log.i(TAG, "Logout selected");
            Toast.makeText(MainActivity.this, "Logout selected", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Unknown item selected");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}