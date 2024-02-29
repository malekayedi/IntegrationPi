package com.example.gestion_produit.front;

import com.example.gestion_produit.News.AddDescriptionResponse;
import com.example.gestion_produit.model.Blog;
import com.example.gestion_produit.model.Matche;
import com.example.gestion_produit.model.Message;
import com.example.gestion_produit.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GitHubService {
    @GET("/getProduit")
    Call<List<Products>> getAll();
    @GET("/getMatche")
    Call<List<Matche>> getAllMatch();
    @POST("/incrementLike/{productId}")
    Call<Void> incrementLike(@Path("productId") int productId);

    @POST("/decrementLike/{productId}")
    Call<Void> decrementLike(@Path("productId") int productId);
    @GET("/getBlog")
    Call<List<Blog>> getAllblog();

    @POST("/addDescription")
    Call<AddDescriptionResponse> addDescription(@Body Blog request);

    @POST("/incrementLike/{blogId}")
    Call<Void> incrementLikeblog(@Path("blogId") int blogId);

    @POST("/decrementLike/{blogId}")
    Call<Void> decrementLikeblog(@Path("blogId") int blogId);

    @POST("/incrementDislike/{blogId}")
    Call<Void> incrementDislike(@Path("blogId") int blogId);

    @POST("/decrementDislike/{blogId}")
    Call<Void> decrementDislike(@Path("blogId") int blogId);

    @GET
    Call<Message> getMessage(@Url String url);
}
