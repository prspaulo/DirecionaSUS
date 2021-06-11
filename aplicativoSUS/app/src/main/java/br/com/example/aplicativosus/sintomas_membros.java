package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sintomas_membros extends AppCompatActivity {

    Button btn_costas, btn_braco, btn_mao, btn_perna, btn_pe, btn_pele_membros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sintomas_membros);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btn_costas = findViewById(R.id.btn_costas);
        btn_costas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, costas.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Costas");
                startActivity(i);
            }
        });

        btn_braco = findViewById(R.id.btn_braco);
        btn_braco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, braco.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Braço");
                startActivity(i);
            }
        });

        btn_mao = findViewById(R.id.btn_mao);
        btn_mao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, mao.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Mão");
                startActivity(i);
            }
        });

        btn_perna = findViewById(R.id.btn_perna);
        btn_perna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, perna.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Perna");
                startActivity(i);
            }
        });

        btn_pe = findViewById(R.id.btn_pe);
        btn_pe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, pe.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pé");
                startActivity(i);
            }
        });

        btn_pele_membros = findViewById(R.id.btn_pele_membros);
        btn_pele_membros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_membros.this, pele_reg_membros.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pele região membros");
                startActivity(i);
            }
        });
    }
}
