package br.com.example.aplicativosus;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class servicos extends AppCompatActivity{

    Button btn2_csaude, btn2_upa, btn2_hospital, btn2_ambulatorios, btn2_pvacina, btn2_praticas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicos);

        btn2_csaude = findViewById(R.id.btn_csaude);
        btn2_csaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), centrosdesaude.class);
                startActivity(i);
            }
        });

        btn2_upa = findViewById(R.id.btn_upa);
        btn2_upa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), upas.class);
                startActivity(i);
            }
        });

        btn2_hospital = findViewById(R.id.btn_hospital);
        btn2_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), hospitais.class);
                startActivity(i);
            }
        });

        btn2_pvacina = findViewById(R.id.btn_pvacina);
        btn2_pvacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), centrosdesaude.class);
                startActivity(i);
            }
        });

        btn2_ambulatorios = findViewById(R.id.btn_ambulatorio);
        btn2_ambulatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ambulatorios.class);
                startActivity(i);
            }
        });
    }
}


