package com.example.acolhe_pet_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class TelaDenunciaAbrigo extends AppCompatActivity {
    ListView lista;
    String email_login, data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_denuncia_abrigo);

        Intent telaMenuDenun = getIntent();
        Bundle parametro = telaMenuDenun.getExtras();
        email_login = parametro.getString("email");
        data = parametro.getString("data");


        List<ModeloDenuncia> listaDenuncia = null;
        listaDenuncia = consultaTodosAgendamentos(data);

        DenunciaAdapter adaptador = new DenunciaAdapter(this,listaDenuncia);

        lista = (ListView) findViewById(R.id.listaDenuncia);
        lista.setAdapter(adaptador);

    }

    private List<ModeloDenuncia> consultaTodosAgendamentos(String _data) {
        List<ModeloDenuncia> lista = new LinkedList<ModeloDenuncia>();

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultaAgendamentos2(_data);

        if (dados.moveToFirst()){
            // encontrou conteúdo para mostrar na lista
            do {
                ModeloDenuncia item = new ModeloDenuncia();
                item.setIdDenuncia(dados.getInt(0));
                item.setEmail(dados.getString(1));
                item.setData(dados.getString(2));
                item.setNumero(dados.getString(3));
                item.setRua(dados.getString(4));
                item.setBairro(dados.getString(5));
                item.setCidade(dados.getString(6));
                item.setDescricaoProblema(dados.getString(7));
                item.setSituacao(dados.getString(8));
                item.setCelularDenun(dados.getString(9));
                item.setNomeDenun(dados.getString(10));
                lista.add(item);
            } while (dados.moveToNext());
        }else{
            String msg = "Não há agendamentos efetuados!";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        return lista;
    }
}