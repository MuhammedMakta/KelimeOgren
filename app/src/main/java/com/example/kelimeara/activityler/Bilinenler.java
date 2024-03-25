package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.kelimeara.R;
import com.example.kelimeara.adapter.RecyclerViewAdapterBilinmeSayisi;
import com.example.kelimeara.adapter.RecyclerViewAdapterSozluk;
import com.example.kelimeara.object.Kelime;
import com.example.kelimeara.veritabanislemleri.KelimelerDao;
import com.example.kelimeara.veritabanislemleri.KelimelerVeriTabaniYardimcisi;

import java.util.ArrayList;

public class Bilinenler extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapterBilinmeSayisi recyclerViewAdapterBilinmeSayisi;
    ArrayList<Kelime> kelimeArrayList = new ArrayList<>();
    KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi = new KelimelerVeriTabaniYardimcisi(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilinenler);
        String seviye = getIntent().getStringExtra("seviye");
        recyclerView  = findViewById(R.id.recyclerview_bilinme_sayilari);
        kelimeArrayList = new KelimelerDao().bilinenKelimeler(kelimelerVeriTabaniYardimcisi,seviye);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapterBilinmeSayisi = new RecyclerViewAdapterBilinmeSayisi(this,kelimeArrayList);
        recyclerView.setAdapter(recyclerViewAdapterBilinmeSayisi);

    }
}