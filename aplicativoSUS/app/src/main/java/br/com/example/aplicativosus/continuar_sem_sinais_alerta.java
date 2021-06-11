package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class continuar_sem_sinais_alerta extends AppCompatActivity {
    Button btn_cabeca, btn_peito, btn_barriga, btn_costas_membros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continuar);

        btn_cabeca = findViewById(R.id.btn_cabeca);
        btn_cabeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), sintomas_cabeca.class);
                //startActivity(i);
                Intent i = new Intent(continuar_sem_sinais_alerta.this, sintomas_cabeca.class);
                i.putExtra("regiao", "Cabeça");
                startActivity(i);
            }
        });

        btn_peito = findViewById(R.id.btn_peito);
        btn_peito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(continuar_sem_sinais_alerta.this, sintomas_torax.class);
                i.putExtra("regiao", "Tórax");
                startActivity(i);
            }
        });

        btn_barriga = findViewById(R.id.btn_barriga);
        btn_barriga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(continuar_sem_sinais_alerta.this, sintomas_barriga.class);
                i.putExtra("regiao", "Barriga");
                startActivity(i);
            }
        });

        btn_costas_membros = findViewById(R.id.btn_costas_membros);
        btn_costas_membros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(continuar_sem_sinais_alerta.this, sintomas_membros.class);
                i.putExtra("regiao", "CostasMembros");
                startActivity(i);
            }
        });

    }
}
