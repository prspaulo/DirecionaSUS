package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class boca extends AppCompatActivity {

    Button btn_gerar_rota;
    CheckBox cbk_corte, cbk_dente_quebrado, cbk_dor_de_dente;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boca);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_corte = (CheckBox)findViewById(R.id.cbx_corte);
        cbk_dente_quebrado = (CheckBox)findViewById(R.id.cbx_dente_quebrado);
        cbk_dor_de_dente = (CheckBox)findViewById(R.id.cbx_dor_de_dente);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
        final String onde_incomoda = i.getStringExtra("onde");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_gerar_rota = findViewById(R.id.btn_gerar_rota);
        btn_gerar_rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String result = "Sintomas: ";
                if(cbk_corte.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Corte");
                }
                if(cbk_dente_quebrado.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dente quebrado");
                }
                if(cbk_dor_de_dente.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Dor de dente");
                }

                if(!cbk_corte.isChecked() && !cbk_dente_quebrado.isChecked() && !cbk_dor_de_dente.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(boca.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                boca.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        btn_corte = findViewById(R.id.btn_corte);
//        btn_corte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(boca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Corte");
//                startActivity(i);
//                boca.this.finish();
//            }
//        });
//
//        btn_dente_quebrado = findViewById(R.id.btn_dente_quebrado);
//        btn_dente_quebrado.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(boca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dente quebrado");
//                startActivity(i);
//                boca.this.finish();
//            }
//        });
//
//        btn_dor_de_dente = findViewById(R.id.btn_dor_de_dente);
//        btn_dor_de_dente.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(boca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor de dente");
//                startActivity(i);
//                boca.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                boca.this.finish();
//            }
//        });
    }
}
