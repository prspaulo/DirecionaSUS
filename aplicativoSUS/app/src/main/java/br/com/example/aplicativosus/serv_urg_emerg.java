package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class serv_urg_emerg extends AppCompatActivity {

    Button btn_upas, btn_hospitais, btn_hp_infantis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serv_urg_emerg);

        btn_upas = findViewById(R.id.btn_upa);
        btn_upas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), upas.class);
                startActivity(i);
            }
        });

        btn_hospitais = findViewById(R.id.btn_hospital);
        btn_hospitais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), hospitais.class);
                startActivity(i);
            }
        });

        btn_hp_infantis = findViewById(R.id.btn_hosp_infantil);
        btn_hp_infantis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), hospitais_infantis.class);
                startActivity(i);
            }
        });
    }
}
