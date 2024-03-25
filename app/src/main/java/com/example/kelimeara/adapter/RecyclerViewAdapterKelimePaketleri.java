package com.example.kelimeara.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelimeara.activityler.CalismaActivity;
import com.example.kelimeara.activityler.DinlemeActivity;
import com.example.kelimeara.activityler.TestActivity;
import com.example.kelimeara.object.Kelime;
import com.example.kelimeara.R;

import java.util.ArrayList;

public class RecyclerViewAdapterKelimePaketleri extends RecyclerView.Adapter<RecyclerViewAdapterKelimePaketleri.PaketNesneleriniTutucu> {
    private Context mcontext;
    private ArrayList<Kelime> kelimeArrayList;

    public RecyclerViewAdapterKelimePaketleri(Context mcontext, ArrayList<Kelime> kelimeArrayList) {
        this.mcontext = mcontext;
        this.kelimeArrayList = kelimeArrayList;
    }

    public class PaketNesneleriniTutucu extends RecyclerView.ViewHolder{
        public TextView textViewNumara;
        public CardView cardViewKelimePaketi;
        public ImageButton imageButtonCalisma;
        public ImageButton imageButtonDinleme;
        public ImageButton imageButtonTest;

        public PaketNesneleriniTutucu( View itemView) {
            super(itemView);
            textViewNumara              = itemView.findViewById(R.id.textView_numara);
            cardViewKelimePaketi        = itemView.findViewById(R.id.cardview_kelime_paketi);
            imageButtonCalisma          = itemView.findViewById(R.id.imagebutton_calisma);
            imageButtonDinleme          = itemView.findViewById(R.id.imageButton_dinleme);
            imageButtonTest             = itemView.findViewById(R.id.imageButton_test);

        }
    }
    @NonNull
    @Override
    public PaketNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_kelime_paketleri_tasarim,parent,false);
        return new PaketNesneleriniTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaketNesneleriniTutucu holder, int position) {
        ArrayList<Kelime> onkelime = new ArrayList<>();
        holder.textViewNumara.setText(String.valueOf(position + 1) + ".");
        Kelime ekleneceKelime;
        int katSayi = kelimeArrayList.size() / 10;
        for (int i = 0; i < 10; i++) {
            ekleneceKelime = kelimeArrayList.get((position) + (katSayi * i));
            onkelime.add(ekleneceKelime);
        }
        holder.imageButtonCalisma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CalismaActivity.class);
                intent.putExtra("kelimelistesi",onkelime);
                mcontext.startActivity(intent);
            }
        });

        holder.imageButtonDinleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, DinlemeActivity.class);
                intent.putExtra("kelimelistesi",onkelime);
                mcontext.startActivity(intent);
            }
        });

        holder.imageButtonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, TestActivity.class);
                intent.putExtra("kelimelistesi",onkelime);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (kelimeArrayList.size() / 10) + 1;
    }


}
