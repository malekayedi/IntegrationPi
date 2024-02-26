package com.example.gestion_produit.model;

public class Player {

    String Player_name;
    String Nationality;
    String Pts;
    Integer imageUrl;

    public Player(String player_name, String nationality, String pts, Integer imageUrl) {
        Player_name = player_name;
        Nationality = nationality;
        Pts = pts;
        this.imageUrl = imageUrl;
    }

    public String getPlayer_name() {
        return Player_name;
    }

    public void setPlayer_name(String player_name) {
        Player_name = player_name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getPts() {
        return Pts;
    }

    public void setPts(String pts) {
        Pts = pts;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
