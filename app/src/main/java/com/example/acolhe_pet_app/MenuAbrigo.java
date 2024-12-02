package com.example.acolhe_pet_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.nio.BufferUnderflowException;

public class MenuAbrigo extends AppCompatActivity implements View.OnClickListener {
    String nome_denun;
    DatePicker txtSELData;
    Button btSELSeleciona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_abrigo);

        Intent telaMenuDenun = getIntent();
        Bundle parametro = telaMenuDenun.getExtras();
        nome_denun = parametro.getString("nomeDenun");

        txtSELData = (DatePicker) findViewById(R.id.txtSELData);
        btSELSeleciona = (Button) findViewById(R.id.btSELSeleciona);
        btSELSeleciona.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String data = "";
        int mes = txtSELData.getMonth()+1;
        data = txtSELData.getDayOfMonth() + "/" + mes + "/" + txtSELData.getYear();

        //ir para a tela de consulta_lista
        Intent tela = new Intent(this,TelaDenunciaAbrigo.class);
        Bundle parametro = new Bundle();
        parametro.putString("email", nome_denun);
        parametro.putString("data", data);
        tela.putExtras(parametro);
        startActivity(tela);
    }
}