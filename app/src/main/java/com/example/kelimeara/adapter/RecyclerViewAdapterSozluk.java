package com.example.kelimeara.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelimeara.activityler.KelimeDetay;
import com.example.kelimeara.object.Kelime;
import com.example.kelimeara.R;

import java.util.ArrayList;

public class RecyclerViewAdapterSozluk extends RecyclerView.Adapter<RecyclerViewAdapterSozluk.PaketNesneleriniTutucu>{
    private Context mcontext;
    private ArrayList<Kelime> kelimeler;

    public RecyclerViewAdapterSozluk(Context mcontext, ArrayList<Kelime> kelimeler) {
        this.mcontext = mcontext;
        this.kelimeler = kelimeler;
    }
    public class PaketNesneleriniTutucu extends RecyclerView.ViewHolder{
        public TextView textViewSozluk;
        public CardView cardviewSatir;

        public PaketNesneleriniTutucu( View itemView) {
            super(itemView);
            textViewSozluk       = itemView.findViewById(R.id.textView_sozluk);
            cardviewSatir        = itemView.findViewById(R.id.cardview_satir);
        }
    }
    @NonNull
    @Override
    public PaketNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sozluk_tasarim,parent,false);
        return new PaketNesneleriniTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaketNesneleriniTutucu holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewSozluk.setText(kelimeler.get(position).getIngilizce_kelime()+
                "\n\n" + kelimeler.get(position).getTurkce_anlam1()+
                "\n" + kelimeler.get(position).getTurkce_anlam2()+
                "\n" + kelimeler.get(position).getTurkce_anlam3());
        holder.cardviewSatir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kelime kelime = kelimeler.get(position);

                Intent intent = new Intent(mcontext, KelimeDetay.class);
                intent.putExtra("veri",kelime);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimeler.size();
    }


}
