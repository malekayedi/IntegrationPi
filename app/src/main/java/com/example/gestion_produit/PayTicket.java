package com.example.gestion_produit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PayTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ticket);
        // Récupérer le numéro passé depuis MainActivity
        int num = getIntent().getIntExtra("num", 0); // 0 est la valeur par défaut si le numéro n'est pas trouvé
        int price = getIntent().getIntExtra("price", 0);
        // Afficher le numéro dans un TextView ou un autre composant de votre mise en page
        //TextView numTextView = findViewById(R.id.ticketnum);
        TextView numTextView = findViewById(R.id.ticketnum);
        TextView priceTextView = findViewById(R.id.ticketPrice);
        numTextView.setText(String.valueOf(num));
        priceTextView.setText(String.valueOf(price));
    }
}