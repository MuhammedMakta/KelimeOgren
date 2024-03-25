package com.example.kelimeara.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelimeara.R;
import com.example.kelimeara.activityler.KelimeDetay;
import com.example.kelimeara.object.Kelime;

import java.util.ArrayList;

public class RecyclerViewAdapterBilinmeSayisi extends  RecyclerView.Adapter<RecyclerViewAdapterBilinmeSayisi.PaketNesneleriniTutucu>{
    private Context mcontext;
    private ArrayList<Kelime> kelimeArrayList;

    public RecyclerViewAdapterBilinmeSayisi(Context mcontext, ArrayList<Kelime> kelimeArrayList) {
        this.mcontext = mcontext;
        this.kelimeArrayList = kelimeArrayList;
    }



    public class PaketNesneleriniTutucu extends RecyclerView.ViewHolder{
        public TextView textViewKelime;
        public TextView textViewDogruSayisi;
        public TextView textViewYanlisSayisi;
        public ImageButton imageButtonFlashcard;


        public PaketNesneleriniTutucu( View itemView) {
            super(itemView);
            textViewKelime              = itemView.findViewById(R.id.textView_bilinme_kelime);
            textViewDogruSayisi         = itemView.findViewById(R.id.textView_bilinme_dogru);
            textViewYanlisSayisi        = itemView.findViewById(R.id.textView_bilinme_yanlis);
            imageButtonFlashcard        = itemView.findViewById(R.id.imageButtonFlashcard);


        }
    }

    @NonNull
    @Override
    public PaketNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bilinme_sayisi_tasarim,parent,false);
        return new RecyclerViewAdapterBilinmeSayisi.PaketNesneleriniTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaketNesneleriniTutucu holder, @SuppressLint("RecyclerView") int position) {
        holder.textViewKelime.setText(kelimeArrayList.get(position).getIngilizce_kelime());
        holder.textViewDogruSayisi.setText(String.valueOf(kelimeArrayList.get(position).getDogru_sayisi()));
        holder.textViewYanlisSayisi.setText(String.valueOf(kelimeArrayList.get(position).getYanlis_sayisi()));
        holder.imageButtonFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kelime kelime = kelimeArrayList.get(position);

                Intent intent = new Intent(mcontext, KelimeDetay.class);
                intent.putExtra("veri",kelime);
                mcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return kelimeArrayList.size();
    }
}
