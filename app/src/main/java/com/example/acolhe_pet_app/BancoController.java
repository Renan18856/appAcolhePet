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

        //Alterações forma feitas aqui (Adição dos colchetes {})
        if (resultado == -1) {
            return "Erro ao tentar efetuar o Cadastre_se";
        }else{
            return "Cadastro efetuado com sucesso";
        }
    }

    // rotina para incluir um novo abrigo (tela de Cadastre_se)
    public String gravaAbrigo (String _NomeAbri, String _EmailAbri, String _CidadeAbri, String _TelefoneAbri,
    String _CnpjAbri, String _SenhaAbri) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        //alteração no nome das variáveis:
        valores = new ContentValues();
        valores.put("nomeAbri", _NomeAbri);
        valores.put("emailAbri", _EmailAbri);
        valores.put("cidadeAbri", _CidadeAbri);
        valores.put("telefoneAbri", _TelefoneAbri);
        valores.put("cnpjAbri", _CnpjAbri);
        valores.put("senhaAbri", _SenhaAbri);

        resultado = db.insert("abrigo", null, valores);
        db.close();

        //Alterações forma feitas aqui (Adição dos colchetes {})
        if (resultado == -1) {
            return "Erro ao tentar efetuar o Cadastre_se";
        }else{
            return "Cadastro efetuado com sucesso";
        }
    }

    // funcao para verificar se denunciante e senha existem (tela de Login Denunciante)
    // select idDenunciante, nome, email, celular, senha from denunciante where email = 'aaa@aaa.com.br' and senha = '123@123'
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
        //Os métodos ProcuraDadosLogin e ProcuraDadosLoginAbri chamam db.close() antes de retornar o cursor.
        // Isso é problemático porque o fechamento do banco de dados invalida o cursor, causando falhas ao tentar acessá-lo. (Segundo Chat-GPT)
        //Teste para encontrar erro, ao "desativar" db.close()

        db.close();
        return cursor;
    }

    // funcao para verificar se abrigo e senha existem (tela de Login Abrigo)
    // select idAbrigo, nome, email, cidade, telefone, cnpj, senha from abrigo where email = 'aaa@aaa.com.br' and senha = '123@123'
    public Cursor ProcuraDadosLoginAbri(String _EmailAbri, String _SenhaAbri) {
        Cursor cursor;
        String[] campos = { "idAbrigo", "nomeAbri", "emailAbri", "cidadeAbri", "telefoneAbri", "cnpjAbri", "senhaAbri" };
        String where = "emailAbri = '" + _EmailAbri + "' and senhaAbri = '" + _SenhaAbri + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("abrigo", campos , where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //Teste para encontrar erro, ao "desativar" db.close()

        db.close();
        return cursor;
    }

    //Atenção!!
    //Os métodos abaixo serão utilizados para armazenar
    //as informações da denúncia!!!

    //Esse é o segundo método utilizado em FormularioDenuncia:
    public String insereDadosAgendamento( String _email, String _data, String _numero, String _rua, String _bairro, String _cidade
            , String _descricaoProblema, String _urgencia, String _nomeDenun, String _celularDenun) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("email", _email);
        valores.put("data", _data);
        valores.put("numero", _numero);
        valores.put("rua", _rua);
        valores.put("bairro", _bairro);
        valores.put("cidade", _cidade);
        valores.put("descricaoProblema", _descricaoProblema);
        valores.put("urgencia", _urgencia);
        valores.put("nomeDenun", _nomeDenun);
        valores.put("celularDenun", _celularDenun);
        //os dados das variáveis nomeDenun e celularDenun só
        //serão utilizado pelo Menu do abrigo


        resultado = db.insert("dadosDenuncia", null, valores);
        db.close();

        if (resultado == -1)
            return "Atenção - Erro ao gravar denúncia";
        else
            return "Denúncia gravada com sucesso";
    }



    //Este método será utilizado no MenuDenunciante.java (No código do professor, ele é utilizado no Consulta_lista)
    //Não confundir "consultaAgendamentos" com "consultaAgendamento"
    public Cursor consultaAgendamentos2(String _email) {
        Cursor cursor;
        //SELECT idAgendameto, email, data, hora FROM agendamento
        //Pelo que eu entendi, temos colocar as variaveis do banco da tabela dadosDenuncia em "campos".
        String[] campos = { "idDenuncia", "email", "data", "numero", "rua", "bairro", "cidade", "descricaoProblema", "urgencia", "nomeDenun", "celularDenun"};
        String where = "email = '" + _email +  "'";
        db = banco.getReadableDatabase();
        cursor = db.query("dadosDenuncia", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor consultaAgendamentos3(String _data) {
        Cursor cursor;
        //SELECT idAgendameto, email, data, hora FROM agendamento
        //Pelo que eu entendi, temos colocar as variaveis do banco da tabela dadosDenuncia em "campos".
        String[] campos = { "idDenuncia", "email", "data", "numero", "rua", "bairro", "cidade", "descricaoProblema", "urgencia", "nomeDenun", "celularDenun"};
        String where = "data = '" + _data +  "'";
        db = banco.getReadableDatabase();
        cursor = db.query("dadosDenuncia", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
