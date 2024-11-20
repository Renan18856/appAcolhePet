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
    EditText denunCADNome, denunCADEmail, denunCADCelular, denunCADSenha, denunCADSenha2;

    //Aqui serão armazenadas as classes/variáveis que vão para o banco
    String denunNome, denunEmail, denunCelular, denunSenha, denunSenha2;

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

        // Aqui, associamos as variáveis aos componentes (no caso eles sendo: EditText) que estão em activity_cadastro_denunciante,xml
        btCADSalvar     = (Button)   findViewById(R.id.btCADSalvar);
        denunCADNome    = (EditText) findViewById(R.id.denunCADNome);
        denunCADEmail   = (EditText) findViewById(R.id.denunCADEmail);
        denunCADCelular = (EditText) findViewById(R.id.denunCADCelular);
        denunCADSenha   = (EditText) findViewById(R.id.denunCADSenha);
        denunCADSenha2  = (EditText) findViewById(R.id.denunCADSenha2);

        btCADSalvar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Boolean erro = false;
        //Aqui estamos pegando os dados digitados pelo usuario, e colocando elas nas variaveis
        //do banco (No caso as variaveis do banco, são as da esquerda):
        denunNome    = denunCADNome.getText().toString();
        denunEmail   = denunCADEmail.getText().toString();
        denunCelular = denunCADCelular.getText().toString();
        denunSenha   = denunCADSenha.getText().toString();
        denunSenha2  = denunCADSenha2.getText().toString();

        erro = VerificaDados();
        if (!erro){    // se erro == false
            //gravar os dados
            BancoController bd = new BancoController(getBaseContext());
            String resultado;

            resultado = bd.gravaDenun(denunNome, denunEmail, denunCelular, denunSenha);


            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        }


    }
    public boolean VerificaDados() {
        if (denunNome.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo NOME deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (denunEmail.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo E-MAIL deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (denunCelular.isEmpty()) {
            //O novo comando verifica se a variável denunCelular está vazia.
            Toast.makeText(getApplicationContext(), "Atenção - O campo Celular deve ser preenchido", Toast.LENGTH_LONG).show();
            return true;
        }
        //Novo comando!!!!
        // Explicação do comando ^\\d+$ Segundo (ChatGPT):
        // ^: Representa o início do texto.
        // \\d: Corresponde a um único dígito (0–9).
        // +: Significa "um ou mais" dígitos.
        // $: Representa o final do texto.
        // O operador ! verifica se o conteúdo NÃO corresponde ao padrão "apenas números".
        // Caso haja letras ou uma combinação de letras e números, a condição será verdadeira, e a mensagem será exibida.
        if (!denunCelular.matches("^\\d+$") ){
            Toast.makeText(getApplicationContext(), "Atenção - O campo Celular deve ter apenas números", Toast.LENGTH_LONG).show();
            return true;
        }
        if (denunSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (denunSenha2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Atenção - O campo CONFIRMAÇÃO DE SENHA deve ser preenchido!", Toast.LENGTH_LONG).show();
            return true;
        }
        if (!denunSenha.equals(denunSenha2)){
            Toast.makeText(getApplicationContext(), "Atenção - O campos Senha e Confirma Senha não são iguais", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
