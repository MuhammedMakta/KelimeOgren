package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kelimeara.R;

public class SonucEkrani extends AppCompatActivity {
    private TextView textViewDogruSayisi, textViewYanlisSayisi;
    private Button buttonBitir;
    private int dogruSayisi = 0;
    private int yanliSayisi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_ekrani);
        dogruSayisi = getIntent().getIntExtra("dogrusayisi",0);
        yanliSayisi = getIntent().getIntExtra("yanlissayisi",0);

        textViewDogruSayisi         = findViewById(R.id.textview_dogrusayisi);
        textViewYanlisSayisi        = findViewById(R.id.textview_yanlissaiyisi);
        buttonBitir                 = findViewById(R.id.button_bitir);

        textViewDogruSayisi.setText("Dogru sayisi: " + String.valueOf(dogruSayisi));
        textViewYanlisSayisi.setText("Yanlis sayisi: " + String.valueOf(yanliSayisi));

        buttonBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });



    }
}