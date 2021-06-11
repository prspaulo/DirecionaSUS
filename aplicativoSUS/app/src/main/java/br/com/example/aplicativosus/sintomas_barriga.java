package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sintomas_barriga extends AppCompatActivity {

    Button btn_s_digestorio, btn_rins_tratourinario, btn_genitalia_m, btn_genitalia_f, btn_pele_barriga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sintomas_barriga);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        btn_s_digestorio = findViewById(R.id.btn_s_digestorio);
        btn_s_digestorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sintomas_barriga.this, sistema_digestorio.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Sistema digestório");
                startActivity(i);
            }
        });

        btn_rins_tratourinario = findViewById(R.id.btn_rins_trato);
        btn_rins_tratourinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), rins_trato_urinario.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Rins e trato urinário");
                startActivity(i);
            }
        });

        btn_genitalia_m = findViewById(R.id.btn_genitalia_m);
        btn_genitalia_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), genitalia_m.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Genitália masculina");
                startActivity(i);
            }
        });

        btn_genitalia_f = findViewById(R.id.btn_genitalia_f);
        btn_genitalia_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), genitalia_f.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Genitália feminina");
                startActivity(i);
            }
        });

        btn_pele_barriga = findViewById(R.id.btn_pele_barriga);
        btn_pele_barriga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), pele_barriga.class);
                i.putExtra("regiao", regiao_sintoma);
                i.putExtra("onde", "Pele");
                startActivity(i);
            }
        });

    }
}
