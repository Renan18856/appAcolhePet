package com.example.acolhe_pet_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {


    private static final String NOME_BANCO = "banco_exemplo.db";
    private static final int VERSAO = 18;
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //tabela denunciante
        String sql = "CREATE TABLE denunciante ("
                + "idDenunciante integer primary key autoincrement,"
                + "nome  text,"
                + "email text,"
                + "celular text,"
                + "senha text)";
        db.execSQL(sql);

        //tabela abrigo
        sql = "CREATE TABLE abrigo ("
                + "idAbrigo integer primary key autoincrement,"
                + "nomeAbri  text,"
                + "emailAbri text,"
                + "cidadeAbri text,"
                + "telefoneAbri text,"
                + "cnpjAbri text,"
                + "senhaAbri text)";
        db.execSQL(sql);

        //Tive que tirar "telefoneAbri" e "nomeAbri" por enquanto
        //Tabela dados da Denuncia
        sql = "CREATE TABLE dadosDenuncia ("
                + "idDenuncia integer primary key autoincrement,"
                + "email text,"
                + "data text,"
                + "numero text,"
                + "rua text,"
                + "bairro text,"
                + "cidade text,"
                + "descricaoProblema text,"
                + "urgencia text,"
                + "nomeDenun text,"
                + "celularDenun text)";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS denunciante");
        db.execSQL("DROP TABLE IF EXISTS abrigo");
        db.execSQL("DROP TABLE IF EXISTS dadosDenuncia");

        onCreate(db);
    }
}
