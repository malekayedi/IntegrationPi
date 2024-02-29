package com.example.gestion_produit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity
public class Matche implements Serializable {
    @PrimaryKey(autoGenerate = true)
    Integer MatchId;
    @ColumnInfo
    @SerializedName("nameplayer1")
    String nameplayer1;
    @ColumnInfo
    @SerializedName("nameplayer2")
    String nameplayer2;
    @ColumnInfo
    @SerializedName("imageplayer1")
    int imageplayer1;
    @ColumnInfo
    @SerializedName("imageplayer2")
    int imageplayer2;
    @ColumnInfo
    @SerializedName("scoreplayer1")
    Integer scoreplayer1;
    @ColumnInfo
    @SerializedName("scoreplayer2")
    Integer scoreplayer2;
    @ColumnInfo
    @SerializedName("DateMatch")
    String DateMatch;


    public Matche(String nameplayer1, String nameplayer2, int imageplayer1, int imageplayer2, Integer scoreplayer1, Integer scoreplayer2, String dateMatch) {
        this.nameplayer1 = nameplayer1;
        this.nameplayer2 = nameplayer2;
        this.imageplayer1 = imageplayer1;
        this.imageplayer2 = imageplayer2;
        this.scoreplayer1 = scoreplayer1;
        this.scoreplayer2 = scoreplayer2;
        DateMatch = dateMatch;
    }

    public String getDateMatch() {
        return DateMatch;
    }

    public void setDateMatch(String dateMatch) {
        DateMatch = dateMatch;
    }

    public Integer getScoreplayer2() {
        return scoreplayer2;
    }

    public void setScoreplayer2(Integer scoreplayer2) {
        this.scoreplayer2 = scoreplayer2;
    }

    public Integer getMatchId() {
        return MatchId;
    }

    public void setMatchId(Integer matchId) {
        MatchId = matchId;
    }

    public String getNameplayer1() {
        return nameplayer1;
    }

    public void setNameplayer1(String nameplayer1) {
        this.nameplayer1 = nameplayer1;
    }

    public String getNameplayer2() {
        return nameplayer2;
    }

    public void setNameplayer2(String nameplayer2) {
        this.nameplayer2 = nameplayer2;
    }

    public int getImageplayer1() {
        return imageplayer1;
    }

    public void setImageplayer1(int imageplayer1) {
        this.imageplayer1 = imageplayer1;
    }

    public int getImageplayer2() {
        return imageplayer2;
    }

    public void setImageplayer2(int imageplayer2) {
        this.imageplayer2 = imageplayer2;
    }

    public Integer getScoreplayer1() {
        return scoreplayer1;
    }

    public void setScoreplayer1(Integer scoreplayer1) {
        this.scoreplayer1 = scoreplayer1;
    }
}
