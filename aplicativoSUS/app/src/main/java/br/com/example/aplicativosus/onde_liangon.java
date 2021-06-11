package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class onde_liangon extends AppCompatActivity {
    Button reg_barreiro, reg_nordeste, reg_noroeste, reg_centrosul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onde_liangon);

        reg_barreiro = findViewById(R.id.btn_reg_barreiro);
        reg_barreiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), liangong_barreiro.class);
                startActivity(i);
            }
        });

        reg_nordeste = findViewById(R.id.btn_reg_nordeste);
        reg_nordeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), liangong_nordeste.class);
                startActivity(i);
            }
        });

        reg_noroeste = findViewById(R.id.btn_reg_noroeste);
        reg_noroeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), liangong_noroeste.class);
                startActivity(i);
            }
        });

        reg_centrosul = findViewById(R.id.btn_reg_centrosul);
        reg_centrosul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), liangong_centrosul.class);
                startActivity(i);
            }
        });
    }
}
