package com.example.acolhe_pet_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class DenuncianteLogin extends AppCompatActivity implements View.OnClickListener {
    Button btLOGAcessar, btLOGCadastre_se;
    EditText txtLOGEmail, txtLOGSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_denunciante_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.denunciante_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btLOGAcessar = (Button) findViewById(R.id.btLOGAcessar);
        btLOGCadastre_se = (Button) findViewById(R.id.btLOGCadastre_se);
        txtLOGEmail = (EditText) findViewById(R.id.txtLOGEmail);
        txtLOGSenha = (EditText) findViewById(R.id.txtLOGSenha);


        btLOGAcessar.setOnClickListener(this);
        btLOGCadastre_se.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btLOGAcessar) {
            // carregar a tela do menu
            if (VerificaDados()) {
                Intent telaMenu = new Intent(this, MainActivity.class);
                startActivity(telaMenu);
            }
        }
        if (v.getId()==R.id.btLOGCadastre_se) {
            // carregar a tela do cadastre_se
            Intent telaCadastre_se = new Intent(this, CadastroDenunciante.class);
            startActivity(telaCadastre_se);
        }
    }
    public boolean VerificaDados(){
        String Email = txtLOGEmail.getText().toString();
        String Senha = txtLOGSenha.getText().toString();
        if (Email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "O campo E_MAIL deve ser preenchido!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (Senha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "O campo SENHA deve ser preenchido!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        BancoController bd = new BancoController(getBaseContext());


        Cursor dados = bd.ProcuraDadosLogin(Email, Senha) ;


        if(dados.moveToFirst()){
            return true;
        }else{
            Toast.makeText(getApplicationContext(), "Usuário / senha não cadastrada!", Toast.LENGTH_LONG).show();
            return false;
        }


    }
}
