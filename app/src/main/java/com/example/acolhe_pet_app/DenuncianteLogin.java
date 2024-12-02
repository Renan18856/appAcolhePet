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
    EditText denunLOGEmail, denunLOGSenha;
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


        btLOGAcessar     = (Button) findViewById(R.id.btLOGAcessar);
        btLOGCadastre_se = (Button) findViewById(R.id.btLOGCadastre_se);
        denunLOGEmail    = (EditText) findViewById(R.id.denunLOGEmail);
        denunLOGSenha    = (EditText) findViewById(R.id.denunLOGSenha);


        btLOGAcessar.setOnClickListener(this);
        btLOGCadastre_se.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btLOGAcessar) {
            // carregar a tela do menu
            if (VerificaDados()) {
                Intent telaMenuDenun = new Intent(this, MenuDenunciante.class);
                //É obrigatório o comando abaixo, senão o email da tabela dadosDenun não funciona!
                Bundle parametro = new Bundle();
                parametro.putString("email", denunLOGEmail.getText().toString());
                telaMenuDenun.putExtras(parametro);
                startActivity(telaMenuDenun);
            }
        }
        if (v.getId()==R.id.btLOGCadastre_se) {
            // carregar a tela do cadastre_se
            Intent telaCadDenun = new Intent(this, CadastroDenunciante.class);
            startActivity(telaCadDenun);
        }
    }
    public boolean VerificaDados(){
        String Email = denunLOGEmail.getText().toString();
        String Senha = denunLOGSenha.getText().toString();
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

        // Aqui provavelmente é onde os dados serão procurados no banco
        Cursor dados = bd.ProcuraDadosLogin(Email, Senha) ;


        if(dados.moveToFirst()){
            return true;
        }else{
            Toast.makeText(getApplicationContext(), "Denunciante / senha não cadastrada!", Toast.LENGTH_LONG).show();
            return false;
        }


    }
}
