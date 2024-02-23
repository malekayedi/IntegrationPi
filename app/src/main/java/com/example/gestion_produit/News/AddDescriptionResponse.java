package com.example.gestion_produit.News;

import com.google.gson.annotations.SerializedName;

public class AddDescriptionResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
