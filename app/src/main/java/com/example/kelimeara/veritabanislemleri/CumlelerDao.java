package com.example.kelimeara.veritabanislemleri;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.kelimeara.object.Cumle;

import java.util.ArrayList;

public class CumlelerDao {
    public ArrayList<Cumle> tumCumleler(CumlelerVeriTabaniYardimcisi cumlelerVeriTabaniYardimcisi) {
        ArrayList<Cumle> cumlelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = cumlelerVeriTabaniYardimcisi.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cumle", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Cumle cumle = new Cumle(
                    cursor.getInt(cursor.getColumnIndex("cumle_id")),
                    cursor.getString(cursor.getColumnIndex("cumle_ingilizce"))
            );
            cumlelerArrayList.add(cumle);
        }

        return cumlelerArrayList;
    }

    public ArrayList<Cumle> cumleIcındeKelimeArama(CumlelerVeriTabaniYardimcisi cumlelerVeriTabaniYardimcisi,String gelenKelime) {
        ArrayList<Cumle> cumlelerArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = cumlelerVeriTabaniYardimcisi.getWritableDatabase();
        gelenKelime = " " + gelenKelime + " ";
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cumle WHERE cumle_ingilizce like '%" +gelenKelime +"%'", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Cumle cumle = new Cumle(
                    cursor.getInt(cursor.getColumnIndex("cumle_id")),
                    cursor.getString(cursor.getColumnIndex("cumle_ingilizce"))
            );
            cumlelerArrayList.add(cumle);
        }
        Cumle cumle = new Cumle(0,"Maalesef Ornek Cumle Yok");
        if(cumlelerArrayList.size() == 0){
            cumlelerArrayList.add(0,cumle);
        }
        return cumlelerArrayList;
    }
}
