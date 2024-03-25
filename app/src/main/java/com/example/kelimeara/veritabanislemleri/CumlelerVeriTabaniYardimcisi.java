package com.example.kelimeara.veritabanislemleri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CumlelerVeriTabaniYardimcisi extends SQLiteOpenHelper {

    public CumlelerVeriTabaniYardimcisi(@Nullable Context context) {
        super(context, "cumle.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"cumle\" (\n" +
                "\t\"cumle_id\"\tINTEGER NOT NULL,\n" +
                "\t\"cumle_ingilizce\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"cumle_id\")\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cumle");
        onCreate(sqLiteDatabase);
    }
}
