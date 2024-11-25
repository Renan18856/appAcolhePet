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

public class AbrigoLogin extends AppCompatActivity implements View.OnClickListener{
    Button btLOGAbrigoAcesso, btLOGAbrigoCad;
    EditText emailLogAbrigo, senhaLogAbrigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abrigo_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.abrigo_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btLOGAbrigoAcesso = (Button)findViewById(R.id.btLOGAbrigoAcesso);
        btLOGAbrigoCad    = (Button)findViewById(R.id.btLOGAbrigoCad);
        emailLogAbrigo    = (EditText) findViewById(R.id.emailLogAbrigo);
        senhaLogAbrigo    = (EditText) findViewById(R.id.senhaLogAbrigo);

        btLOGAbrigoAcesso.setOnClickListener(this);
        btLOGAbrigoCad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btLOGAbrigoAcesso) {
            // carregar a tela do menu do abrigo
            if (VerificaDados()) {
                Intent telaMenuAbri = new Intent(this, MenuAbrigo.class);
                startActivity(telaMenuAbri);
            }
        }
        if (v.getId() == R.id.btLOGAbrigoCad) {
            // carregar a tela do cadastre_se abrigo
            Intent telaCadAbri = new Intent(this, CadastroAbrigo.class);
            startActivity(telaCadAbri);
        }
    }

    public boolean VerificaDados() {
        String Email = emailLogAbrigo.getText().toString();
        String Senha = senhaLogAbrigo.getText().toString();
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
        Cursor dados = bd.ProcuraDadosLoginAbri(Email, Senha);


        if (dados.moveToFirst()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Denunciante / senha não cadastrada!", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}