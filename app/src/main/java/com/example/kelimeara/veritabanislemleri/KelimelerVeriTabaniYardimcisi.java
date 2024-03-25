package com.example.kelimeara.veritabanislemleri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class KelimelerVeriTabaniYardimcisi extends SQLiteOpenHelper {
    public KelimelerVeriTabaniYardimcisi(@Nullable Context context) {
        super(context, "kelimeler.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS kelimeler " +
                "(id_kelime INTEGER , ingilizce_kelime TEXT, " +
                "turkce_anlam1 TEXT, " +
                "turkce_anlam2 TEXT," +
                " turkce_anlam3 TEXT, " +
                "kelime_seviyesi TEXT," +
                "dogru_sayisi INTEGER," +
                "yanlis_sayisi INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kelimeler");
        onCreate(sqLiteDatabase);
    }
}
