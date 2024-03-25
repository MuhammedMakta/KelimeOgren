package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.kelimeara.veritabanislemleri.KelimelerDao;
import com.example.kelimeara.veritabanislemleri.KelimelerVeriTabaniYardimcisi;
import com.example.kelimeara.R;
import com.example.kelimeara.adapter.RecyclerViewAdapterSozluk;
import com.example.kelimeara.object.Kelime;

import java.util.ArrayList;

public class Sozluk extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Kelime> kelimeler = new ArrayList<>();
    private RecyclerViewAdapterSozluk recyclerViewAdapterSozluk;
    private SearchView searchView;
    private KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi = new KelimelerVeriTabaniYardimcisi(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sozluk);
        kelimeler = new KelimelerDao().tumKelimelerGetir(kelimelerVeriTabaniYardimcisi);
        recyclerView = findViewById(R.id.recyclerView_sozluk);
        searchView   = findViewById(R.id.searchView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapterSozluk = new RecyclerViewAdapterSozluk(this,kelimeler);
        recyclerView.setAdapter(recyclerViewAdapterSozluk);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                aramaYap(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                aramaYap(s);
                return false;
            }
        });
    }

    public void aramaYap(String s){
        kelimeler = new KelimelerDao().kelimeArama(kelimelerVeriTabaniYardimcisi,s);
        recyclerViewAdapterSozluk = new RecyclerViewAdapterSozluk(this,kelimeler);
        recyclerView.setAdapter(recyclerViewAdapterSozluk);

    }


}