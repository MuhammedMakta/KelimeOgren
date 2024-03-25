package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kelimeara.veritabanislemleri.KelimelerDao;
import com.example.kelimeara.veritabanislemleri.KelimelerVeriTabaniYardimcisi;
import com.example.kelimeara.R;
import com.example.kelimeara.adapter.RecyclerViewAdapterKelimePaketleri;
import com.example.kelimeara.object.Kelime;

import java.util.ArrayList;

public class KelimePaketleri extends AppCompatActivity {
    private KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi = new KelimelerVeriTabaniYardimcisi(this);
    private ArrayList<Kelime> kelimeler = new ArrayList<>();
    private RecyclerViewAdapterKelimePaketleri recyclerViewAdapterKelimePaketleri;
    private RecyclerView recyclerView;
    private ImageButton imageButtonBilinenler;
    private ImageButton imageButtonBilinmeyenler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelime_paketleri);
        imageButtonBilinenler    = findViewById(R.id.imageButtonBilinenler);
        imageButtonBilinmeyenler = findViewById(R.id.imageButtonBilinmeyenler);


        String seviye = getIntent().getStringExtra("seviye");
        imageButtonBilinenler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KelimePaketleri.this,Bilinenler.class);
                intent.putExtra("seviye",seviye);
                startActivity(intent);
            }
        });

        imageButtonBilinmeyenler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KelimePaketleri.this,Bilinmeyenler.class);
                intent.putExtra("seviye",seviye);
                startActivity(intent);
            }
        });
        kelimeler = new KelimelerDao().kelimeSeviyeyeGoreAl(kelimelerVeriTabaniYardimcisi,seviye);
        recyclerView = findViewById(R.id.recyclerview_kelimepaketleri);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapterKelimePaketleri = new RecyclerViewAdapterKelimePaketleri(this,kelimeler);
        recyclerView.setAdapter(recyclerViewAdapterKelimePaketleri);


    }
}