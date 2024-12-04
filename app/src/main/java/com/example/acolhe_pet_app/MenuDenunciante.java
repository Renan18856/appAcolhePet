package com.example.acolhe_pet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MenuDenunciante extends AppCompatActivity implements View.OnClickListener{
    //Aqui deve colocar todos os dados que o denunciante fez
    //ATENÇÃO: Não confudir "lista" com listaDenuncia

    ImageButton btFormulario;
    //A variável "lista" abaixo é utilizado apenas pelo MenuDenunciante.java
    //Já o "listaDenuncia" é o id do ListView que está em "activity_menu_denunciante".
    ListView lista;
    String email_denun, data, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_denunciante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu_denunciante), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent telaMenuDenun = getIntent();  // Aqui estamos usando o Intent da tela de login do denunciante, para achar o email logado do denunciante
        Bundle parametro = telaMenuDenun.getExtras();
        email_denun = parametro.getString("email");



        List<ModeloDenuncia> listaDenuncia = null;
        listaDenuncia = consultaTodosAgendamentos(email_denun);

        DenunciaAdapter adaptador = new DenunciaAdapter(this,listaDenuncia);

        lista = (ListView) findViewById(R.id.listaDenuncia);//Este id deve ser o id da lista, que está em activity_menu_denunciante.xml
        lista.setAdapter(adaptador);

        // Aqui, associamos os componentes do MenuDenunciante aos id que estão em activity_menu_denunciante:
        btFormulario = (ImageButton) findViewById(R.id.btFormulario);

        btFormulario.setOnClickListener(this);
    }

    private List<ModeloDenuncia> consultaTodosAgendamentos(String email) {
        List<ModeloDenuncia> lista = new LinkedList<ModeloDenuncia>();

        BancoController bd = new BancoController(getBaseContext());
        Cursor dados = bd.consultaAgendamentos2(email);

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
                item.setUrgencia(dados.getString(8));
                item.setCelularDenun(dados.getString(9));
                item.setNomeDenun(dados.getString(10));

                lista.add(item);
            } while (dados.moveToNext());
        }else{
            String msg = "Não há denúncias efetuadas!";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        return lista;
    }

    //Clique do botão que leva ao formulário:
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btFormulario) {
            // carregar a tela do formulario denuncia
            //Copiei e colei o comando da linha 39 - 44 do app do professor no arquivo "SelecionaData.java"
            //Os códigos abaixo são obrigatórios para que o ImageButton funcione!!
            //Cuidado com os parametros que chama! Eles precisam existir na tela para que funcionem!
            Intent telaForm = new Intent(this, FormularioDenuncia.class);
            Bundle parametro = new Bundle();
            parametro.putString("email", email_denun);
            //parametro.putString("data", data); //Desativei esse comando pois estava dando erro
            telaForm.putExtras(parametro);
            startActivity(telaForm);
        }
    }
}