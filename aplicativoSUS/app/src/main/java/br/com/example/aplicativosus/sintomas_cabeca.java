package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sintomas_cabeca extends AppCompatActivity {

    Button btn_cabeca, btn_olhos, btn_nariz, btn_orelha, btn_boca, btn_pescoco, btn_pele_cabeca, btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sintomas_cabeca);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btn_cabeca = findViewById(R.id.btn_cabeca);
        btn_cabeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), cabeca.class);
                //startActivity(i);
                Intent i = new Intent(sintomas_cabeca.this, cabeca.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Cabeça");
                startActivity(i);
            }
        });

        btn_olhos = findViewById(R.id.btn_olhos);
        btn_olhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, olhos.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Olhos");
                startActivity(i);
            }
        });

        btn_nariz = findViewById(R.id.btn_nariz);
        btn_nariz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, nariz.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Nariz");
                startActivity(i);
            }
        });

        btn_orelha = findViewById(R.id.btn_orelha);
        btn_orelha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, orelha.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Orelha");
                startActivity(i);
            }
        });

        btn_boca = findViewById(R.id.btn_boca);
        btn_boca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, boca.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Boca");
                startActivity(i);
            }
        });

        btn_pescoco = findViewById(R.id.btn_pescoco);
        btn_pescoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, pescoco.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pescoço");
                startActivity(i);
            }
        });

        btn_pele_cabeca = findViewById(R.id.btn_pele_cabeça);
        btn_pele_cabeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_cabeca.this, pele_cabeca.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pele");
                startActivity(i);
            }
        });

        btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), telainicial.class);
                startActivity(i);
            }
        });

    }
}
