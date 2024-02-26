package com.example.gestion_produit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gestion_produit.MatchDetailActivity;
import com.example.gestion_produit.R;
import com.example.gestion_produit.model.Match;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{

    Context context;
    List<Match> MatchList;

    public MatchAdapter(Context context, List<Match> MatchList) {
        this.context = context;
        this.MatchList = MatchList;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.match_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {

        holder.match_title.setText(MatchList.get(position).getMatch_title());
        holder.match_date.setText((CharSequence) MatchList.get(position).getMatch_date());
        holder.match_place.setText(MatchList.get(position).getMatch_place());
        holder.match_image.setImageResource(MatchList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, MatchDetailActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MatchList.size();
    }

    public static final class MatchViewHolder extends RecyclerView.ViewHolder{

        ImageView match_image;
        TextView match_title, match_place, match_date;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);

            match_image = itemView.findViewById(R.id.match_image);
            match_title = itemView.findViewById(R.id.match_title);
            match_place = itemView.findViewById(R.id.match_place);
            match_date = itemView.findViewById(R.id.match_date);

        }
    }

}
