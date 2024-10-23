package com.example.acolhe_pet_app;
// Imports que eu criei: view. View e button do primeiro bloco dos imports
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btDenunciante, btAbrigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Vinculando os objetos com a interface gráfica
        btDenunciante = (Button) findViewById(R.id.btDenunciante);
        btAbrigo = (Button) findViewById(R.id.btAbrigo);

        //Direcionando o click dos botões no evento click
        btDenunciante.setOnClickListener(this);
        btAbrigo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Se clicou no btDenunciante, chamar a activity DenuncianteLogin
        if (view.getId() == R.id.btDenunciante){
            Intent denuncianteLogin = new Intent(this,DenuncianteLogin.class);
            startActivity(denuncianteLogin);
            //OBS: O "DenuncianteLogin" da linha 42, dentro do parenteses,
            //deve ser identico ao nome da activity no arquivo AndroidManifest.xml.
            //OBS: O Intent criado na linha 42 pode ter qualquer nome.
        }

        //Se clicou no btAbrigo, chamar a activity AbrigoLogin
        if (view.getId() == R.id.btAbrigo){
            Intent abrigoLogin = new Intent(this,AbrigoLogin.class);
            startActivity(abrigoLogin);
        }
    }
}