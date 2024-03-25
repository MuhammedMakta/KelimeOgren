package com.example.kelimeara.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.kelimeara.activityler.KelimePaketleri;
import com.example.kelimeara.R;
import com.example.kelimeara.object.Renkler;
import com.example.kelimeara.activityler.Sozluk;

import java.util.List;

public class RecyclerViewAdapterMainActivity extends RecyclerView.Adapter<RecyclerViewAdapterMainActivity.PaketNesneleriniTutucu> {
    private Context mcontext;
    private List<Integer> kelimePaketNumarasi;

    public RecyclerViewAdapterMainActivity(Context mcontext, List<Integer> kelimePaketNumarasi) {
        this.mcontext = mcontext;
        this.kelimePaketNumarasi = kelimePaketNumarasi;
    }

    public class PaketNesneleriniTutucu extends RecyclerView.ViewHolder{
        public TextView textViewKelimePaketi;
        public CardView cardviewSatir;

        public PaketNesneleriniTutucu( View itemView) {
            super(itemView);
            textViewKelimePaketi = itemView.findViewById(R.id.textView_kelime_paketi);
            cardviewSatir        = itemView.findViewById(R.id.cardview_satir);
        }
    }


    @NonNull
    @Override
    public PaketNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_calisma_tasarim,parent,false);
        return new PaketNesneleriniTutucu(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull PaketNesneleriniTutucu holder, int position) {
        int kelimePaketNumarasiGelen = kelimePaketNumarasi.get(position);
        holder.textViewKelimePaketi.setTextColor(Color.WHITE);
        Renkler renkler = new Renkler();

        switch (kelimePaketNumarasiGelen){
            case 0:
                holder.cardviewSatir.setCardBackgroundColor(renkler.cyanmavi);
                holder.textViewKelimePaketi.setText("A1 Seviye Kelimeler");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext, KelimePaketleri.class);
                        intent.putExtra("seviye","a1");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.cardviewSatir.setCardBackgroundColor(renkler.yesil);
                holder.textViewKelimePaketi.setText("A2 Seviye Kelimeler");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext,KelimePaketleri.class);
                        intent.putExtra("seviye","a2");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.cardviewSatir.setCardBackgroundColor(renkler.turuncu);
                holder.textViewKelimePaketi.setText("B1 Seviye Kelimeler");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext,KelimePaketleri.class);
                        intent.putExtra("seviye","b1");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.cardviewSatir.setCardBackgroundColor(renkler.magenta);
                holder.textViewKelimePaketi.setText("B2 Seviye Kelimeler");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext,KelimePaketleri.class);
                        intent.putExtra("seviye","b2");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.cardviewSatir.setCardBackgroundColor(renkler.koyucyan);
                holder.textViewKelimePaketi.setText("C1 Seviye Kelimeler");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mcontext,KelimePaketleri.class);
                        intent.putExtra("seviye","c1");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(intent);
                    }
                });
                break;
            case 5:
                holder.cardviewSatir.setCardBackgroundColor(renkler.black);
                holder.textViewKelimePaketi.setText("Sözlük");
                holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Intent intent = new Intent(mcontext, Sozluk.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      mcontext.startActivity(intent);
                    }
                });
                break;
        }
    }
    @Override
    public int getItemCount() {
        return kelimePaketNumarasi.size();
    }

}
