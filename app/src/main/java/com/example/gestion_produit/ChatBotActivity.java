package com.example.gestion_produit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion_produit.adapter.ChatRvAdapter;
import com.example.gestion_produit.front.GitHubService;
import com.example.gestion_produit.model.Chat;
import com.example.gestion_produit.model.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotActivity extends AppCompatActivity {
    private RecyclerView chatsRv;
    private EditText userMsgEdit;
    private FloatingActionButton sendMsgFab;
    private final String USER_KEY="user";
    private final String BOT_KEY="bot";
  private   ArrayList<Chat> chatArrayList;
  private ChatRvAdapter chatRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        chatsRv=findViewById(R.id.idRVchats);
        userMsgEdit=findViewById(R.id.idEditMessage);
        sendMsgFab=findViewById(R.id.idFabSend);
        chatArrayList=new ArrayList<>();
        chatRvAdapter = new ChatRvAdapter(chatArrayList,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        chatsRv.setLayoutManager(manager);
        chatsRv.setAdapter(chatRvAdapter);
        sendMsgFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMsgEdit.getText().toString().isEmpty()){
                    Toast.makeText(ChatBotActivity.this,"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdit.getText().toString());
                userMsgEdit.setText("");
            }
        });
    }
    private void getResponse(String message)
    {
        chatArrayList.add(new Chat(message,USER_KEY));
        chatRvAdapter.notifyDataSetChanged();
        String url ="http://api.brainshop.ai/get?bid=180647&key=hlR6wrm0mzSU3kG9&uid=[uid]&msg="+message;
        String BASE_URL="http://api.brainshop.ai/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService gitHubService=retrofit.create(GitHubService.class);
        Call<Message> call=gitHubService.getMessage(url);
     call.enqueue(new Callback<Message>() {
         @Override
         public void onResponse(Call<Message> call, Response<Message> response) {
             if(response.isSuccessful())
             {
                 Message message1=response.body();
                 chatArrayList.add(new Chat(message1.getCnt(),BOT_KEY));
                 chatRvAdapter.notifyDataSetChanged();
             }
         }

         @Override
         public void onFailure(Call<Message> call, Throwable t) {
         chatArrayList.add(new Chat("Please revert your question",BOT_KEY));
         chatRvAdapter.notifyDataSetChanged();
         }
     });

    }
}