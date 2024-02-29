package com.example.gestion_produit.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gestion_produit.R;
import com.example.gestion_produit.RecycleViewOnItemClick;
import com.example.gestion_produit.model.Matche;

import java.util.List;

public class MatcheAdapter extends RecyclerView.Adapter<MatcheAdapter.MatchviewHolder> {
    Context context;
    List<Matche> MatchList;
    private RecycleViewOnItemClick recycleViewOnItemClick;

    public MatcheAdapter(Context context, List<Matche> matchList, RecycleViewOnItemClick recycleViewOnItemClick) {
        this.context = context;
        MatchList = matchList;
        this.recycleViewOnItemClick=recycleViewOnItemClick;
    }
    public MatcheAdapter(Context context, List<Matche> matchList) {
        this.context = context;
        MatchList = matchList;

    }


    @NonNull
    @Override
    public MatcheAdapter.MatchviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.itemmatche,parent,false);
        return new MatchviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MatcheAdapter.MatchviewHolder holder, @SuppressLint("RecyclerView") int position) {
        Matche item= MatchList.get(position);
        holder.nameplayer2.setText(item.getNameplayer2());
        holder.nameplayer1.setText(item.getNameplayer1());
        holder.Datematch.setText(item.getDateMatch());
        holder.scoreplayer1.setText(String.valueOf(item.getScoreplayer1()));
        holder.scoreplayer2.setText(String.valueOf(item.getScoreplayer2()));
        Glide.with(holder.itemView.getContext())
                .load(item.getNameplayer2())
                .placeholder(R.drawable.placeholder) // Image de remplacement en cas de chargement
                .error(R.drawable.error) // Image de remplacement en cas d'erreur de chargement
                .into(holder.imageplayer1);
        Glide.with(holder.itemView.getContext())
                .load(item.getImageplayer1())
                .placeholder(R.drawable.placeholder) // Image de remplacement en cas de chargement
                .error(R.drawable.error) // Image de remplacement en cas d'erreur de chargement
                .into(holder.imageplayer2);
        holder.notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return MatchList.size();
    }

    public class MatchviewHolder extends RecyclerView.ViewHolder {
ImageView imageplayer1,imageplayer2;
        ImageButton notif;
TextView scoreplayer1,scoreplayer2,nameplayer1,nameplayer2,Datematch;
        public MatchviewHolder(@NonNull View itemView) {
            super(itemView);
            imageplayer1=itemView.findViewById(R.id.imageplayer1);
            imageplayer2=itemView.findViewById(R.id.imageplayer2);
            scoreplayer1=itemView.findViewById(R.id.scoreplayer1);
            scoreplayer2=itemView.findViewById(R.id.scoreplayer2);
            nameplayer1=itemView.findViewById(R.id.nameplayer1);
            nameplayer2=itemView.findViewById(R.id.nameplayer2);
            notif=itemView.findViewById(R.id.notif);
            Datematch=itemView.findViewById(R.id.datematchedt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleViewOnItemClick.onClick(getAdapterPosition());
                }
            });
        }

        }

}
