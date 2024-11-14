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




    // rotina para incluir um novo usuário (tela de Cadastre_se)
    public String gravaUsuario(String _Nome, String _Email, String _Cpf, String _Senha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();




        valores = new ContentValues();
        valores.put("nome", _Nome);
        valores.put("email", _Email);
        valores.put("cpf", _Cpf);
        valores.put("senha", _Senha);




        resultado = db.insert("usuarios", null, valores);
        db.close();




        if (resultado == -1)
            return "Erro ao tentar efetuar o Cadastre_se";
        else
            return "Cadastro efetuado com sucesso";
    }


    public String insereDados(String txtNome, String txtEmail) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();




        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("email", txtEmail);




        resultado = db.insert("contatos", null, valores);
        db.close();




        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    // funcao para verificar se usuário e senha existem (tela de Login)
    // select idUsuario, nome, email, cpf, senha from usuarios where email = 'aaa@aaa.com.br' and senha = '123@123'
    public Cursor ProcuraDadosLogin(String _email, String _senha) {
        Cursor cursor;
        String[] campos = { "idUsuario", "nome", "email", "cpf", "senha" };
        String where = "email = '" + _email + "' and senha = '" + _senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadosPeloCodigo(int id) {
        Cursor cursor;
        String[] campos = { "codigo", "nome", "email" };
        String where = "codigo=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("contatos", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }




        db.close();
        return cursor;
    }




    public String alteraDados(int id, String nome, String email){




        String msg = "Dados alterados com sucesso!!!" ;




        db = banco.getReadableDatabase();




        ContentValues valores = new ContentValues() ;
        valores.put("nome" , nome ) ;
        valores.put("email", email) ;




        String condicao = "codigo = " + id;




        int linha ;
        linha = db.update("contatos", valores, condicao, null) ;




        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }




        db.close();
        return msg;
    }




    public String excluirDados(int id){
        String msg = "Registro Excluído" ;




        db = banco.getReadableDatabase();




        String condicao = "codigo = " + id ;




        int linhas ;
        linhas = db.delete("contatos", condicao, null) ;




        if ( linhas < 1) {
            msg = "Erro ao Excluir" ;
        }




        db.close();
        return msg;
    }




}
