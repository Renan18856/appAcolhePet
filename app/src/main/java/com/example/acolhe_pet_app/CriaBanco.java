package com.example.acolhe_pet_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {


    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 4;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE denunciante ("
                + "idDenunciante integer primary key autoincrement,"
                + "nome  text,"
                + "email text,"
                + "celular text,"
                + "senha text)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS denunciante");
        onCreate(db);
    }
}
