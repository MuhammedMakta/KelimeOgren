package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelimeara.R;
import com.example.kelimeara.object.Cumle;
import com.example.kelimeara.object.Kelime;
import com.example.kelimeara.veritabanislemleri.CumlelerDao;
import com.example.kelimeara.veritabanislemleri.CumlelerVeriTabaniYardimcisi;
import com.example.kelimeara.veritabanislemleri.DatabaseCopyHelperCumle;
import com.example.kelimeara.veritabanislemleri.KelimelerDao;
import com.example.kelimeara.veritabanislemleri.KelimelerVeriTabaniYardimcisi;

import java.util.ArrayList;
import java.util.Locale;

public class CalismaActivity extends AppCompatActivity {
    private ArrayList<Cumle> cumles = new ArrayList<>();
    private KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi = new KelimelerVeriTabaniYardimcisi(this);
    private CumlelerVeriTabaniYardimcisi cumlelerVeriTabaniYardimcisi = new CumlelerVeriTabaniYardimcisi(this);
    private ArrayList<Kelime> kelimeler = new ArrayList<>();
    private ImageButton imageButton_dinle, button_sonraki, imageButtonBiliyorum;
    private TextView textView_ingilizce, textView_turkce, textView_cumle;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calisma);
        kelimeler = (ArrayList<Kelime>) getIntent().getSerializableExtra("kelimelistesi");
        imageButton_dinle    = findViewById(R.id.imageButton_test_dinle);
        imageButtonBiliyorum = findViewById(R.id.imageButton_biliyorum);
        textView_ingilizce   = findViewById(R.id.textView_calisma_ingilizce_kelime);
        textView_turkce      = findViewById(R.id.textView_calisma_turkce_anlam);
        textView_cumle       = findViewById(R.id.textView_calisma_cumle);
        button_sonraki       = findViewById(R.id.button_test_sonraki);


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int a) {
                if(a!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        CumlelerDao cumlelerDao = new CumlelerDao();
        for (int k = 0; k < kelimeler.size(); k++) {
            cumles.add(cumlelerDao.cumleIcındeKelimeArama(cumlelerVeriTabaniYardimcisi,kelimeler.get(k).getIngilizce_kelime()).get(0));
        }


        final int[] i = {0};
        textView_ingilizce.setText(kelimeler.get(i[0]).getIngilizce_kelime());
        textView_turkce.setText(kelimeler.get(i[0]).getTurkce_anlam1() + "\n" + kelimeler.get(i[0]).getTurkce_anlam2() + "\n" +kelimeler.get(i[0]).getTurkce_anlam3());

        imageButtonBiliyorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kelimeler.get(i[0]).setDogru_sayisi(kelimeler.get(i[0]).getDogru_sayisi() + 4);
                new KelimelerDao().dogruSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i[0]));
                imageButtonBiliyorum.setClickable(false);
                Toast.makeText(CalismaActivity.this, "Bilinen kelimelere eklendi", Toast.LENGTH_SHORT).show();
            }
        });

        if (cumles.size() > 0){
            textView_cumle.setText(cumles.get(i[0]).getCumle_ingilizce());
        }else{
            textView_cumle.setText("Maalesef örnek cümle yok");
        }

        imageButton_dinle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(kelimeler.get(i[0]).getIngilizce_kelime(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });


            button_sonraki.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    i[0]++;
                    if(i[0] == 10){
                        for (int j = 0; j < kelimeler.size(); j++) {
                        }
                        textView_turkce.setTextSize(30);
                        textView_ingilizce.setVisibility(View.INVISIBLE);
                        textView_cumle.setVisibility(View.INVISIBLE);
                        textView_turkce.setText("Bitti");

                        finish();
                    }else{
                        textView_ingilizce.setText(kelimeler.get(i[0]).getIngilizce_kelime());
                        textView_turkce.setText(kelimeler.get(i[0]).getTurkce_anlam1() + "\n" + kelimeler.get(i[0]).getTurkce_anlam2() + "\n" +kelimeler.get(i[0]).getTurkce_anlam3());
                        textToSpeech.speak(kelimeler.get(i[0]).getIngilizce_kelime(),TextToSpeech.QUEUE_FLUSH,null);
                        if (cumles.size() > 0){
                            textView_cumle.setText(cumles.get(i[0]).getCumle_ingilizce());
                        }else{
                            textView_cumle.setText("Maalesef örnek cümle yok");
                        }
                    }
                    imageButtonBiliyorum.setClickable(true);
                }
            });





    }

    private void kopyala() {
        DatabaseCopyHelperCumle helperCumle = new DatabaseCopyHelperCumle(this);

        try {
            helperCumle.createDataBase();

        }catch (Exception e){
            e.printStackTrace();
        }
        helperCumle.openDataBase();
    }
}