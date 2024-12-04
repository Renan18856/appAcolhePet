package com.example.acolhe_pet_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FormularioDenuncia extends AppCompatActivity implements View.OnClickListener {
    Button btAGESalvarDenuncia;
    DatePicker txtAGEData;
    EditText txtAGENumero;
    EditText txtAGERua;
    EditText txtAGEBairro;
    EditText txtAGECidade;
    EditText txtAGEDescricaoProblema;
    RadioGroup txtAGEUrgencia;
    EditText txtAGECelular;
    EditText txtAGENome;

    //Variavel do email do usuario, que veio da tela de login.
    String email_denun;

    //Variáveis que serão usadas para levar os dados a tabela dadosDenun:
    //Decidi colocar elas aqui para facilitar a filtragem de dados.
    String data, numero, rua, bairro, cidade, descricaoProblema, urgencia, celularDenun, nomeDenun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_denuncia);

        Intent telaForm = getIntent();
        Bundle parametro = telaForm.getExtras();
        email_denun = parametro.getString("email");


        btAGESalvarDenuncia = (Button) findViewById(R.id.btAGESalvarDenuncia);
        txtAGEData = (DatePicker) findViewById(R.id.txtAGEData);
        txtAGENumero = (EditText) findViewById(R.id.txtAGENumero);
        txtAGERua = (EditText) findViewById(R.id.txtAGERua);
        txtAGEBairro = (EditText) findViewById(R.id.txtAGEBairro);
        txtAGECidade = (EditText) findViewById(R.id.txtAGECidade);
        txtAGEDescricaoProblema = (EditText) findViewById(R.id.txtAGEDescricaoProblema);
        txtAGEUrgencia = (RadioGroup) findViewById(R.id.txtAGEUrgencia);
        txtAGECelular = (EditText) findViewById(R.id.txtAGECelular);
        txtAGENome = (EditText) findViewById(R.id.txtAGENome);

        btAGESalvarDenuncia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Boolean erro = false;

        // Obtem o RadioButton selecionado pelo ID
        int selectedId = txtAGEUrgencia.getCheckedRadioButtonId();

        if (selectedId != -1) { // Verifica se algum botão foi selecionado
            RadioButton selectedButton = findViewById(selectedId);
            urgencia = selectedButton.getText().toString(); // Obtém o texto do botão selecionado
        } else {
            urgencia = ""; // Define como vazio caso nada seja selecionado
        }


        int mes = txtAGEData.getMonth() + 1;
        data = txtAGEData.getDayOfMonth() + "/" + mes + "/" + txtAGEData.getYear();
        numero = txtAGENumero.getText().toString();
        rua = txtAGERua.getText().toString();
        bairro = txtAGEBairro.getText().toString();
        cidade = txtAGECidade.getText().toString();
        descricaoProblema = txtAGEDescricaoProblema.getText().toString();
        //Tirei o urgencia desta parte do código e coloquei ele na parte de cima, onde é verificado se uma opção foi selecionada.
        celularDenun = txtAGECelular.getText().toString();
        nomeDenun = txtAGENome.getText().toString();

        //método para verificar os dados inseridos pelo usuario:
        erro = VerificaDadosDenuncia();
        if (!erro){    // se erro == false
            //gravar os dados
            BancoController bd = new BancoController(getBaseContext());
            String resultado;

            resultado = bd.insereDadosAgendamento(email_denun, data, numero, rua, bairro, cidade, descricaoProblema, urgencia, celularDenun, nomeDenun);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }
    }
    public boolean VerificaDadosDenuncia() {
        if (numero.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NÚMERO deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        //Verifica se tem apenas números neste editText
        if (!numero.matches("^\\d+$")) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NÚMERO deve ter apenas números!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (rua.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo RUA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (bairro.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo BAIRRO deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (cidade.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CIDADE deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (descricaoProblema.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo DESCRIÇÃO DO PROBLEMA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (urgencia.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - Em URGÊNCIA selecione uma opção", Toast.LENGTH_LONG).show();
            return true;
        }
        if (celularDenun.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CELULAR deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (!celularDenun.matches("^\\d+$")) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CELULAR deve ter apensa números!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (nomeDenun.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NOME deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}