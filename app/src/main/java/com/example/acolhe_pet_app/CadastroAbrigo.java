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

public class CadastroAbrigo extends AppCompatActivity implements View.OnClickListener {
    Button btCADAbrigoSalvar;
    EditText abriCADNome, abriCADEmail, abriCADCidade, abriCADTelefone, abriCADCnpj, abriCADSenha, abriCADSenha2;

    //Aqui serão armazenadas as classes/variáveis que vão para o banco, ao coloca-las nessa parte do código, todas elas se tornam "public".
    String abriNome, abriEmail, abriCidade, abriTelefone, abriCnpj, abriSenha, abriSenha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_abrigo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cadastro_abrigo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        abriCADNome = (EditText) findViewById(R.id.abriCADNome);
        abriCADEmail = (EditText) findViewById(R.id.abriCADEmail);
        abriCADCidade = (EditText) findViewById(R.id.abriCADCidade);
        abriCADTelefone = (EditText) findViewById(R.id.abriCADTelefone);
        abriCADCnpj = (EditText) findViewById(R.id.abriCADCnpj);
        abriCADSenha = (EditText) findViewById(R.id.abriCADSenha);
        abriCADSenha2 = (EditText) findViewById(R.id.abriCADSenha2);

        btCADAbrigoSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Boolean erro = false;
        //Aqui estamos pegando os dados digitados pelo usuario, e colocando elas nas variaveis
        //do banco (No caso as variaveis do banco, são as da esquerda):
        abriNome     = abriCADNome.getText().toString();
        abriEmail    = abriCADEmail.getText().toString();
        abriCidade   = abriCADCidade.getText().toString();
        abriTelefone = abriCADTelefone.getText().toString();
        abriCnpj     = abriCADCnpj.getText().toString();
        abriSenha    = abriCADSenha.getText().toString();
        abriSenha2   = abriCADSenha2.getText().toString();

        erro = VerificaDados();
        if (!erro) {    // se erro == false
            //gravar os dados
            BancoController bd = new BancoController(getBaseContext());
            String resultado;

            resultado = bd.gravaAbrigo(abriNome, abriEmail, abriCidade, abriTelefone, abriCnpj, abriSenha);


            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }
    }

    public boolean VerificaDados() {
        if (abriNome.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NOME deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriEmail.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo EMAIL deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriCidade.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CIDADE deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriTelefone.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo TELEFONE deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        //Comando para que o telefone aceite apenas números
        if (!abriTelefone.matches("^\\d+$")) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo TELEFONE deve ter apenas números!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriCnpj.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CNPJ deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        //Comando para que o CNPJ aceite apenas números
        if (!abriCnpj.matches("^\\d+$")) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CNPJ deve ter apenas números!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (abriSenha2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (!abriSenha.equals(abriSenha2)){
            Toast.makeText(getApplicationContext(), "Atenção - O campos Senha e Confirma Senha não são iguais", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}