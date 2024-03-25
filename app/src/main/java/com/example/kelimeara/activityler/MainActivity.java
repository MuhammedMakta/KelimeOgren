package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kelimeara.veritabanislemleri.DatabaseCopyHelperCumle;
import com.example.kelimeara.veritabanislemleri.DatabaseCopyHelperKelimeler;
import com.example.kelimeara.R;
import com.example.kelimeara.adapter.RecyclerViewAdapterMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> kelimeSeviyeNumarasi = new ArrayList<>();
    private RecyclerViewAdapterMainActivity recyclerViewAdapterCalisma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kopyala();
        for (int i = 0; i < 6; i++){
            kelimeSeviyeNumarasi.add(i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewAdapterCalisma = new RecyclerViewAdapterMainActivity(getApplicationContext(), kelimeSeviyeNumarasi);
        recyclerView.setAdapter(recyclerViewAdapterCalisma);
    }

    private void kopyala() {
        DatabaseCopyHelperKelimeler helperKelimeler = new DatabaseCopyHelperKelimeler(this);
        DatabaseCopyHelperCumle     helperCumle     = new DatabaseCopyHelperCumle(this);
        try {
            helperKelimeler.createDataBase();
            helperCumle.createDataBase();

        }catch (Exception e){
            e.printStackTrace();
        }
        helperKelimeler.openDataBase();
        helperCumle.openDataBase();
    }
}