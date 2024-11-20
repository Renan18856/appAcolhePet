package com.example.acolhe_pet_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.app.Activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuDenunciante extends AppCompatActivity implements View.OnClickListener {

    Button botao1;
    ImageView img_one;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_denunciante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu_denunciante), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botao1 = (Button) findViewById(R.id.botao1);
        botao1.setOnClickListener(this);
        img_one = (ImageView) findViewById(R.id.img_one);
        img_one.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Escolha sua imagem"), 1);

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.botao1) {
            Intent telaDenuncia = new Intent(this, TelaDenuncia.class);
            startActivity(telaDenuncia);
        }
    }

    public void onActivityResult(int RequestCode, int ResultCode, Intent dados){
        super.onActivityResult(RequestCode, ResultCode, dados);
        if (ResultCode == Activity.RESULT_OK){
            if(RequestCode == 1){
                img_one.setImageURI(dados.getData());
            }
        }
    }

}