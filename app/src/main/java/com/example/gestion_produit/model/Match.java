package com.example.gestion_produit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Match {
    @PrimaryKey(autoGenerate = true)
    Integer MatchId;
    @ColumnInfo
    @SerializedName("Match_title")
    String Match_title;
    @ColumnInfo
    @SerializedName("Match_date")
    String Match_date;
    @ColumnInfo
    @SerializedName("Match_place")
    String Match_place;
    @ColumnInfo
    @SerializedName("imageUrl")
    Integer imageUrl;

    public Match(String match_title, String match_date, String match_place, Integer imageUrl) {
        Match_title = match_title;
        Match_date = match_date;
        Match_place = match_place;
        this.imageUrl = imageUrl;
    }

    public String getMatch_title() {
        return Match_title;
    }

    public void setMatch_title(String match_title) {
        Match_title = match_title;
    }

    public String getMatch_date() {
        return Match_date;
    }

    public void setMatch_date(String match_date) {
        Match_date = match_date;
    }

    public String getMatch_place() {
        return Match_place;
    }

    public void setMatch_place(String match_place) {
        Match_place = match_place;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
