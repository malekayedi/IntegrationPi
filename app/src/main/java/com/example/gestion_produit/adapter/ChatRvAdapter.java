package com.example.gestion_produit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestion_produit.R;
import com.example.gestion_produit.model.Chat;

import java.util.ArrayList;

public class ChatRvAdapter extends RecyclerView.Adapter {
    private ArrayList<Chat> chatArrayList;
    private Context context;

    public ChatRvAdapter(ArrayList<Chat> chatArrayList, Context context) {
        this.chatArrayList = chatArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType)
        {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item,parent,false);
                return new BotViewHolder(view);

        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     Chat chat=chatArrayList.get(position);
     switch (chat.getSender())
     {
         case "user":
             ((UserViewHolder)holder).userTv.setText(chat.getMessage());
             break;
         case "bot":
             ((BotViewHolder)holder).BotTv.setText(chat.getMessage());
             break;
     }
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTv;
        public UserViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userTv = itemView.findViewById(R.id.idTvuser);
        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView BotTv;
        public BotViewHolder(@NonNull View itemView)
        {
            super(itemView);
            BotTv = itemView.findViewById(R.id.idTvbot);
        }
    }
}
