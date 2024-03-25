package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kelimeara.veritabanislemleri.CumlelerDao;
import com.example.kelimeara.veritabanislemleri.CumlelerVeriTabaniYardimcisi;
import com.example.kelimeara.veritabanislemleri.DatabaseCopyHelperCumle;
import com.example.kelimeara.veritabanislemleri.DatabaseCopyHelperKelimeler;
import com.example.kelimeara.R;
import com.example.kelimeara.object.Cumle;
import com.example.kelimeara.object.Kelime;

import java.util.ArrayList;
import java.util.Locale;

public class KelimeDetay extends AppCompatActivity {
    private TextView textViewKelime;
    private TextView textViewCumle;
    private ImageButton imageButtonSes;
    private Kelime kelime = null;
    private ArrayList<Cumle> cumleArrayList = new ArrayList<>();
    private TextToSpeech textToSpeech;
    private CumlelerVeriTabaniYardimcisi cumlelerVeriTabaniYardimcisi = new CumlelerVeriTabaniYardimcisi(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);
        kopyala();
        kelime = (Kelime) getIntent().getSerializableExtra("veri");

        textViewKelime = findViewById(R.id.textView_kelime);
        textViewCumle  = findViewById(R.id.textView_cumle);
        imageButtonSes = findViewById(R.id.imageButton_ses);

        textViewKelime.setText(kelime.getIngilizce_kelime()+"\n\n" + kelime.getTurkce_anlam1() + "\n"+
                kelime.getTurkce_anlam2()+"\n" + kelime.getTurkce_anlam3());

        cumleArrayList = new CumlelerDao().cumleIcındeKelimeArama(cumlelerVeriTabaniYardimcisi,kelime.getIngilizce_kelime());
        String cumleler = "";
        for (int i = 0; i < cumleArrayList.size(); i++) {
                cumleler = cumleler + cumleArrayList.get(i).getCumle_ingilizce() + "\n\n";

        }
        textViewCumle.setText(cumleler);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        imageButtonSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(kelime.getIngilizce_kelime().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });


    }


    private void kopyala() {
        DatabaseCopyHelperKelimeler helperKelimeler = new DatabaseCopyHelperKelimeler(this);
        DatabaseCopyHelperCumle helperCumle         = new DatabaseCopyHelperCumle(this);

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