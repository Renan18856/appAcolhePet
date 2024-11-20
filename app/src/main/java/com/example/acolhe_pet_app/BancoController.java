package com.example.acolhe_pet_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    // rotina para incluir um novo denunciante (tela de Cadastre_se)
    public String gravaDenun (String _Nome, String _Email, String _Celular, String _Senha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", _Nome);
        valores.put("email", _Email);
        valores.put("celular", _Celular);
        valores.put("senha", _Senha);

        resultado = db.insert("denunciante", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao tentar efetuar o Cadastre_se";
        else
            return "Cadastro efetuado com sucesso";
    }

    // funcao para verificar se denunciante e senha existem (tela de Login)
    // select idUsuario, nome, email, celular, senha from denunciante where email = 'aaa@aaa.com.br' and senha = '123@123'
    public Cursor ProcuraDadosLogin(String _email, String _senha) {
        Cursor cursor;
        String[] campos = { "idDenunciante", "nome", "email", "celular", "senha" };
        String where = "email = '" + _email + "' and senha = '" + _senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("denunciante", campos , where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
