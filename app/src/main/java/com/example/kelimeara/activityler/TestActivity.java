package com.example.kelimeara.activityler;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelimeara.R;
import com.example.kelimeara.object.Kelime;
import com.example.kelimeara.veritabanislemleri.KelimelerDao;
import com.example.kelimeara.veritabanislemleri.KelimelerVeriTabaniYardimcisi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {
    private KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi = new KelimelerVeriTabaniYardimcisi(this);
    private ArrayList<Kelime> kelimeler = new ArrayList<>();
    private TextView textViewTestIngilizceKelime, dogruCevap, textViewDogruSayisi, textViewYanlisSayisi;
    private Button buttonA, buttonB, buttonC, buttonD, buttonBitir;
    private ImageButton imageButtonTestSonraki, imageButtonTestDinleme;
    private TextToSpeech textToSpeech;
    private ArrayList<Kelime> secenekler;
    private int i = 0;
    private int dogruSayisi = 0;
    private int yanliSayisi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        kelimeler = (ArrayList<Kelime>) getIntent().getSerializableExtra("kelimelistesi");

        textViewTestIngilizceKelime = findViewById(R.id.textView_test_ingilizcekelime);
        buttonA                     = findViewById(R.id.buttonA);
        buttonB                     = findViewById(R.id.buttonB);
        buttonC                     = findViewById(R.id.buttonC);
        buttonD                     = findViewById(R.id.buttonD);
        buttonBitir                 = findViewById(R.id.button_bitir);
        dogruCevap                  = findViewById(R.id.textView_dogru_cevap);
        textViewDogruSayisi         = findViewById(R.id.textview_dogrusayisi);
        textViewYanlisSayisi        = findViewById(R.id.textview_yanlissaiyisi);
        imageButtonTestDinleme      = findViewById(R.id.imageButton_test_dinle);
        imageButtonTestSonraki      = findViewById(R.id.button_test_sonraki);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int a) {
                if(a!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        imageButtonTestDinleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(kelimeler.get(i).getIngilizce_kelime(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        dogruCevap.setVisibility(View.INVISIBLE);
        if(i < 10){
            secenekler = soruOlustur(kelimeler.get(i));
        }

        imageButtonTestSonraki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    i++;
                    if(i == 10){
                        i--;
                        Intent intent = new Intent(TestActivity.this, SonucEkrani.class);
                        intent.putExtra("dogrusayisi",dogruSayisi);
                        intent.putExtra("yanlissayisi",yanliSayisi);
                        startActivity(intent);
                        finish();
                    }
                    soruOlustur(kelimeler.get(i));
                    buttonC.setClickable(true);
                    buttonB.setClickable(true);
                    buttonA.setClickable(true);
                    buttonD.setClickable(true);
                    dogruCevap.setVisibility(View.INVISIBLE);
                    textToSpeech.speak(kelimeler.get(i).getIngilizce_kelime(),TextToSpeech.QUEUE_FLUSH,null);



            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(secenekler.get(0).getId_kelime() == kelimeler.get(i).getId_kelime()){
                    kelimeler.get(i).setDogru_sayisi(kelimeler.get(i).getDogru_sayisi()+1);
                    new KelimelerDao().dogruSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    dogruSayisi++;
                    Toast.makeText(TestActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                    sonrakiTikla();
                }else{
                    yanliSayisi++;
                    dogruCevap.setVisibility(View.VISIBLE);
                    dogruCevap.setText("Doğru yanıt: \n" + kelimeler.get(i).getTurkce_anlam1() +"\n" + kelimeler.get(i).getTurkce_anlam2()+"\n"+kelimeler.get(i).getTurkce_anlam3());
                    Toast.makeText(TestActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                    kelimeler.get(i).setYanlis_sayisi(kelimeler.get(i).getYanlis_sayisi() + 1);
                    new KelimelerDao().yanlisSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    buttonB.setClickable(false);
                    buttonC.setClickable(false);
                    buttonD.setClickable(false);
                    buttonA.setClickable(false);
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(secenekler.get(1).getId_kelime() == kelimeler.get(i).getId_kelime()){
                    kelimeler.get(i).setDogru_sayisi(kelimeler.get(i).getDogru_sayisi()+1);
                    new KelimelerDao().dogruSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    dogruSayisi++;
                    Toast.makeText(TestActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                    sonrakiTikla();
                }else{
                    yanliSayisi++;
                    dogruCevap.setVisibility(View.VISIBLE);
                    dogruCevap.setText("Doğru yanıt: \n" + kelimeler.get(i).getTurkce_anlam1() +"\n" + kelimeler.get(i).getTurkce_anlam2()+"\n"+kelimeler.get(i).getTurkce_anlam3());
                    Toast.makeText(TestActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                    kelimeler.get(i).setYanlis_sayisi(kelimeler.get(i).getYanlis_sayisi() + 1);
                    new KelimelerDao().yanlisSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    buttonA.setClickable(false);
                    buttonC.setClickable(false);
                    buttonD.setClickable(false);
                    buttonB.setClickable(false);
                }

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(secenekler.get(2).getId_kelime() == kelimeler.get(i).getId_kelime()){
                    kelimeler.get(i).setDogru_sayisi(kelimeler.get(i).getDogru_sayisi()+1);
                    new KelimelerDao().dogruSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    dogruSayisi++;
                    Toast.makeText(TestActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                    sonrakiTikla();
                }else{
                    yanliSayisi++;
                    dogruCevap.setVisibility(View.VISIBLE);
                    dogruCevap.setText("Doğru yanıt: \n" + kelimeler.get(i).getTurkce_anlam1() +"\n" + kelimeler.get(i).getTurkce_anlam2()+"\n"+kelimeler.get(i).getTurkce_anlam3());
                    Toast.makeText(TestActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                    kelimeler.get(i).setYanlis_sayisi(kelimeler.get(i).getYanlis_sayisi() + 1);
                    new KelimelerDao().yanlisSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    buttonC.setClickable(false);
                    buttonB.setClickable(false);
                    buttonA.setClickable(false);
                    buttonD.setClickable(false);
                }

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(secenekler.get(3).getId_kelime() == kelimeler.get(i).getId_kelime()){
                    dogruSayisi++;
                    kelimeler.get(i).setDogru_sayisi(kelimeler.get(i).getDogru_sayisi()+1);
                    new KelimelerDao().dogruSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    Toast.makeText(TestActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                    sonrakiTikla();
                }else{
                    yanliSayisi++;
                    dogruCevap.setVisibility(View.VISIBLE);
                    dogruCevap.setText("Doğru yanıt: \n" + kelimeler.get(i).getTurkce_anlam1() +"\n" + kelimeler.get(i).getTurkce_anlam2()+"\n"+kelimeler.get(i).getTurkce_anlam3());
                    Toast.makeText(TestActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                    kelimeler.get(i).setYanlis_sayisi(kelimeler.get(i).getYanlis_sayisi() + 1);
                    new KelimelerDao().yanlisSayisiYerlestir(kelimelerVeriTabaniYardimcisi,kelimeler.get(i));
                    buttonD.setClickable(false);
                    buttonB.setClickable(false);
                    buttonC.setClickable(false);
                    buttonA.setClickable(false);
                }
            }
        });

    }


    private ArrayList<Kelime> soruOlustur(Kelime kelime) {
        secenekler = new KelimelerDao().rastgeleYanlisSecenekAl(kelimelerVeriTabaniYardimcisi,kelime);
        HashSet<Kelime> secenekKaristir = new HashSet<>(secenekler);
        secenekler = new ArrayList<>(secenekKaristir);
        textViewTestIngilizceKelime.setText(kelime.getIngilizce_kelime());
        buttonA.setText(secenekler.get(0).getTurkce_anlam1() +"\n" + secenekler.get(0).getTurkce_anlam2()+"\n"+secenekler.get(0).getTurkce_anlam3());
        buttonB.setText(secenekler.get(1).getTurkce_anlam1() +"\n" + secenekler.get(1).getTurkce_anlam2()+"\n"+secenekler.get(1).getTurkce_anlam3());
        buttonC.setText(secenekler.get(2).getTurkce_anlam1() +"\n" + secenekler.get(2).getTurkce_anlam2()+"\n"+secenekler.get(2).getTurkce_anlam3());
        buttonD.setText(secenekler.get(3).getTurkce_anlam1() +"\n" + secenekler.get(3).getTurkce_anlam2()+"\n"+secenekler.get(3).getTurkce_anlam3());

        return secenekler;
    }

    private void sonrakiTikla(){
        imageButtonTestSonraki.callOnClick();
    }



}