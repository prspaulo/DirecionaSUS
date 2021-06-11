package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sintomas_torax extends AppCompatActivity {

    Button btn_coracao, btn_pulmao, btn_pele_peito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sintomas_torax);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btn_coracao = findViewById(R.id.btn_coracao);
        btn_coracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), pulmao.class);
                //startActivity(i);
                Intent i = new Intent(sintomas_torax.this, coracao.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Coração");
                startActivity(i);
                sintomas_torax.this.finish();
            }
        });

        btn_pulmao = findViewById(R.id.btn_pulmao);
        btn_pulmao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_torax.this, pulmao.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pulmão");
                startActivity(i);
                sintomas_torax.this.finish();
            }
        });

        btn_pele_peito = findViewById(R.id.btn_pele_peito);
        btn_pele_peito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_torax.this, pele_peito.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pele");
                startActivity(i);
                sintomas_torax.this.finish();
            }
        });


    }
}
