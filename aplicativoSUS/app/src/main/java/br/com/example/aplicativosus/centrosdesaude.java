package br.com.example.aplicativosus;

import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class centrosdesaude extends AppCompatActivity {

    Button btn_voltar_menu, csaude_barreiro, csaude_centro_sul, csaude_leste, csaude_nordeste,csaude_noroeste, csaude_norte, csaude_oeste, csaude_pampulha, csaude_vendanova;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centrosdesaude);

        csaude_barreiro = findViewById(R.id.btn_barreiro);
        csaude_barreiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/barreiro";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_centro_sul = findViewById(R.id.btn_centro_sul);
        csaude_centro_sul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/centro-sul";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_leste = findViewById(R.id.btn_leste);
        csaude_leste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/leste";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_nordeste = findViewById(R.id.btn_nordeste);
        csaude_nordeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/nordeste";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_noroeste = findViewById(R.id.btn_noroeste);
        csaude_noroeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/noroeste";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_norte = findViewById(R.id.btn_norte);
        csaude_norte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/norte";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_oeste = findViewById(R.id.btn_oeste);
        csaude_oeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/oeste";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_pampulha = findViewById(R.id.btn_pampulha);
        csaude_pampulha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/pampulha";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        csaude_vendanova = findViewById(R.id.btn_venda_nova);
        csaude_vendanova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://prefeitura.pbh.gov.br/saude/informacoes/atencao-a-saude/atencao-primaria/centro-de-saude/venda-nova";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });




    }
}
