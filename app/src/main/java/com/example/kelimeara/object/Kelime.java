package com.example.kelimeara.object;

import java.io.Serializable;

public class Kelime implements Serializable {
    private  int id_kelime;
    private String ingilizce_kelime;
    private String turkce_anlam1;
    private String turkce_anlam2;
    private String turkce_anlam3;
    private String kelime_seviyesi;
    private int dogru_sayisi;
    private int yanlis_sayisi;
    public Kelime() {
    }

    public Kelime(int id_kelime, String ingilizce_kelime, String turkce_anlam1, String turkce_anlam2, String turkce_anlam3, String kelime_seviyesi, int dogru_sayisi, int yanlis_sayisi) {
        this.id_kelime = id_kelime;
        this.ingilizce_kelime = ingilizce_kelime;
        this.turkce_anlam1 = turkce_anlam1;
        this.turkce_anlam2 = turkce_anlam2;
        this.turkce_anlam3 = turkce_anlam3;
        this.kelime_seviyesi = kelime_seviyesi;
        this.dogru_sayisi = dogru_sayisi;
        this.yanlis_sayisi = yanlis_sayisi;
    }

    public int getId_kelime() {
        return id_kelime;
    }

    public void setId_kelime(int id_kelime) {
        this.id_kelime = id_kelime;
    }

    public String getIngilizce_kelime() {
        return ingilizce_kelime;
    }

    public void setIngilizce_kelime(String ingilizce_kelime) {
        this.ingilizce_kelime = ingilizce_kelime;
    }

    public String getTurkce_anlam1() {
        return turkce_anlam1;
    }

    public void setTurkce_anlam1(String turkce_anlam1) {
        this.turkce_anlam1 = turkce_anlam1;
    }

    public String getTurkce_anlam2() {
        return turkce_anlam2;
    }

    public void setTurkce_anlam2(String turkce_anlam2) {
        this.turkce_anlam2 = turkce_anlam2;
    }

    public String getTurkce_anlam3() {
        return turkce_anlam3;
    }

    public void setTurkce_anlam3(String turkce_anlam3) {
        this.turkce_anlam3 = turkce_anlam3;
    }

    public String getKelime_seviyesi() {
        return kelime_seviyesi;
    }

    public void setKelime_seviyesi(String kelime_seviyesi) {
        this.kelime_seviyesi = kelime_seviyesi;
    }

    public int getDogru_sayisi() {
        return dogru_sayisi;
    }

    public void setDogru_sayisi(int dogru_sayisi) {
        this.dogru_sayisi = dogru_sayisi;
    }

    public int getYanlis_sayisi() {
        return yanlis_sayisi;
    }

    public void setYanlis_sayisi(int yanlis_sayisi) {
        this.yanlis_sayisi = yanlis_sayisi;
    }

}
