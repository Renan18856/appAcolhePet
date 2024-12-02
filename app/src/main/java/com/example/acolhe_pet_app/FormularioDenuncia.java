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
import android.widget.Toast;

public class FormularioDenuncia extends AppCompatActivity implements View.OnClickListener {
    Button btAGESalvarDenuncia;
    DatePicker txtAGEData;
    EditText txtAGENumero;
    EditText txtAGERua;
    EditText txtAGEBairro;
    EditText txtAGECidade;
    EditText txtAGEDescricaoProblema;
    EditText txtAGESituacao;
    EditText txtAGECelular;
    EditText txtAGENome;

    String email_denun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_denuncia);

        Intent tela = getIntent();
        Bundle parametro = tela.getExtras();
        email_denun = parametro.getString("email");

        btAGESalvarDenuncia = (Button) findViewById(R.id.btAGESalvarDenuncia);
        txtAGEData = (DatePicker) findViewById(R.id.txtAGEData);
        txtAGENumero = (EditText) findViewById(R.id.txtAGENumero);
        txtAGERua = (EditText) findViewById(R.id.txtAGERua);
        txtAGEBairro = (EditText) findViewById(R.id.txtAGEBairro);
        txtAGECidade = (EditText) findViewById(R.id.txtAGECidade);
        txtAGEDescricaoProblema = (EditText) findViewById(R.id.txtAGEDescricaoProblema);
        txtAGESituacao = (EditText) findViewById(R.id.txtAGESituacao);
        txtAGECelular = (EditText) findViewById(R.id.txtAGECelular);
        txtAGENome = (EditText) findViewById(R.id.txtAGENome);

        btAGESalvarDenuncia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String data, numero, rua, bairro, cidade, descricaoProblema, situacao, celularDenun, nomeDenun;

        int mes = txtAGEData.getMonth() + 1;
        data = txtAGEData.getDayOfMonth() + "/" + mes + "/" + txtAGEData.getYear();
        numero = txtAGENumero.getText().toString();
        rua = txtAGERua.getText().toString();
        bairro = txtAGEBairro.getText().toString();
        cidade = txtAGECidade.getText().toString();
        descricaoProblema = txtAGEDescricaoProblema.getText().toString();
        situacao = txtAGESituacao.getText().toString();
        celularDenun = txtAGECelular.getText().toString();
        nomeDenun = txtAGENome.getText().toString();



        //gravar os dados
        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        //verificar se a data e hora estão disponíveis (consulta)
        Cursor dados = bd.consultaAgendamento1(data, numero, rua, bairro, cidade, descricaoProblema, situacao, celularDenun, nomeDenun) ;
        if(dados.moveToFirst()) {
            //nao pode gravar
            resultado = "Não é possível agendar, data e hora indisponível";
        }else{
            resultado = bd.insereDadosAgendamento(email_denun, data, numero, rua, bairro, cidade, descricaoProblema, situacao, celularDenun, nomeDenun);
        }
        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

    }
}