package com.example.kelimeara.veritabanislemleri;

import static java.sql.Types.NULL;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kelimeara.object.Kelime;

import java.util.ArrayList;

public class KelimelerDao {

    public ArrayList<Kelime> tumKelimelerGetir(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi) {
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }

        return kelimelerArrayList;
    }

    public ArrayList<Kelime> kelimeArama(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi, String arananKelime) {
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler WHERE ingilizce_kelime like'%" + arananKelime +"%'", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }

        return kelimelerArrayList;
    }
    public ArrayList<Kelime> kelimeSeviyeyeGoreAl(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi, String seviye) {
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler WHERE kelime_seviyesi like'%" + seviye + "%'", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }

        return kelimelerArrayList;
    }

    public ArrayList<Kelime> rastgeleYanlisSecenekAl(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi, Kelime dogruKelime){
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase     = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler WHERE id_kelime != "+ dogruKelime.getId_kelime() +" ORDER BY RANDOM() LIMIT 3",null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }
        kelimelerArrayList.add(dogruKelime);
        return kelimelerArrayList;
    }


    public void dogruSayisiYerlestir(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi,Kelime kelime) {
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        String sql = String.format("UPDATE kelimeler SET dogru_sayisi = '%d' WHERE kelime_seviyesi = '%s' AND id_kelime = '%s'", kelime.getDogru_sayisi() ,kelime.getKelime_seviyesi(),kelime.getId_kelime());
        sqLiteDatabase.execSQL(sql);

    }
    public void yanlisSayisiYerlestir(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi,Kelime kelime) {
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        String sql = String.format("UPDATE kelimeler SET yanlis_sayisi = '%d' WHERE kelime_seviyesi = '%s' AND id_kelime = '%s'", kelime.getYanlis_sayisi() ,kelime.getKelime_seviyesi(),kelime.getId_kelime());
        sqLiteDatabase.execSQL(sql);

    }

    public ArrayList<Kelime> bilinenKelimeler(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi, String seviye) {
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        seviye = "'" + seviye + "'";
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler WHERE dogru_sayisi - yanlis_sayisi > 3 AND kelime_seviyesi = " + seviye, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }
        if (kelimelerArrayList.size() == 0){
            Kelime kelime = new Kelime(0,"boş","","","","a1",0,0);
            kelimelerArrayList.add(kelime);
        }
        return kelimelerArrayList;
    }

    public ArrayList<Kelime> bilinmeyenKelimeler(KelimelerVeriTabaniYardimcisi kelimelerVeriTabaniYardimcisi, String seviye) {
        ArrayList<Kelime> kelimelerArrayList = new ArrayList<>();
        seviye = "'" + seviye + "'";
        SQLiteDatabase sqLiteDatabase = kelimelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM kelimeler WHERE dogru_sayisi - yanlis_sayisi < 4 AND kelime_seviyesi = " + seviye + "AND dogru_sayisi+yanlis_sayisi >= 1", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Kelime kelime = new Kelime(
                    cursor.getInt(cursor.getColumnIndex("id_kelime")),
                    cursor.getString(cursor.getColumnIndex("ingilizce_kelime")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam1")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam2")),
                    cursor.getString(cursor.getColumnIndex("turkce_anlam3")),
                    cursor.getString(cursor.getColumnIndex("kelime_seviyesi")),
                    cursor.getInt(cursor.getColumnIndex("dogru_sayisi")),
                    cursor.getInt(cursor.getColumnIndex("yanlis_sayisi"))
            );
            kelimelerArrayList.add(kelime);
        }
        if (kelimelerArrayList.size() == 0){
            Kelime kelime = new Kelime(0,"boş","","","","",0,0);
            kelimelerArrayList.add(kelime);
        }
        return kelimelerArrayList;
    }
}