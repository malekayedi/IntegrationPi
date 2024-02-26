package com.example.gestion_produit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.HomeActivity;
import com.example.gestion_produit.R;
import com.example.gestion_produit.model.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    Context context;
    List<Player> playerList;

    public PlayerAdapter(Context context, List<Player> playerList) {
        this.context = context;
        this.playerList = playerList;
    }



    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.player_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        holder.Player_name.setText(playerList.get(position).getPlayer_name());
        holder.Nationality.setText(playerList.get(position).getNationality());
        holder.Pts.setText(playerList.get(position).getPts());
        holder.playerImage.setImageResource(playerList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static final class PlayerViewHolder extends RecyclerView.ViewHolder{

        ImageView playerImage;
        TextView Nationality, Pts,Player_name;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            playerImage = itemView.findViewById(R.id.player_image);
            Nationality = itemView.findViewById(R.id.player_nat);
            Player_name = itemView.findViewById(R.id.player_name);
            Pts = itemView.findViewById(R.id.pts);


        }
    }
}
