package com.example.acolhe_pet_app;

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


public class CadastroDenunciante extends AppCompatActivity implements View.OnClickListener {
    Button btCADSalvar;
    EditText txtCADNome, txtCADEmail, txtCADCpf, txtCADSenha, txtCADSenha2;


    String txtNome, txtEmail, txtCpf, txtSenha, txtSenha2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_denunciante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Cadastro_denunciante), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btCADSalvar   = (Button)   findViewById(R.id.btCADSalvar);
        txtCADNome    = (EditText) findViewById(R.id.txtCADNome);
        txtCADEmail   = (EditText) findViewById(R.id.txtCADEmail);
        txtCADCpf     = (EditText) findViewById(R.id.txtCADCpf);
        txtCADSenha   = (EditText) findViewById(R.id.txtCADSenha);
        txtCADSenha2  = (EditText) findViewById(R.id.txtCADSenha2);

        btCADSalvar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Boolean erro = false;
        txtNome   = txtCADNome.getText().toString();
        txtEmail  = txtCADEmail.getText().toString();
        txtCpf    = txtCADCpf.getText().toString();
        txtSenha  = txtCADSenha.getText().toString();
        txtSenha2 = txtCADSenha2.getText().toString();

        erro = VerificaDados();
        if (!erro){    // se erro == false
            //gravar os dados
            BancoController bd = new BancoController(getBaseContext());
            String resultado;


            resultado = bd.gravaUsuario(txtNome, txtEmail, txtCpf, txtSenha);


            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }


    }
    public boolean VerificaDados() {
        if (txtNome.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NOME deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (txtEmail.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo E-MAIL deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (txtCpf.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CPF deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (txtSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (txtSenha2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CONFIRMAÇÃO DE SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (!txtSenha.equals(txtSenha2)){
            Toast.makeText(getApplicationContext(), "Atenção - O campos Senha e Confirma Senha não são iguais", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
