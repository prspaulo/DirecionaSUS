package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class pescoco extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_garganta, cbk_difi_engolir, cbk_catarro, cbk_engasgo;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pescoco);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_garganta = (CheckBox)findViewById(R.id.cbx_dor_garganta);
        cbk_difi_engolir = (CheckBox)findViewById(R.id.cbx_difi_engolir);
        cbk_catarro = (CheckBox)findViewById(R.id.cbx_catarro);
        cbk_engasgo = (CheckBox)findViewById(R.id.cbx_engasgo);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
        final String onde_incomoda = i.getStringExtra("onde");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_gerar_rota = findViewById(R.id.btn_gerar_rota);
        btn_gerar_rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String result = "Sintomas: ";
                if(cbk_dor_garganta.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor de garganta");
                }
                if(cbk_difi_engolir.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Dificuldade de engolir");
                }
                if(cbk_catarro.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Catarro");
                }
                if(cbk_engasgo.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Engasgo");
                }
                if(!cbk_dor_garganta.isChecked() && !cbk_difi_engolir.isChecked()
                        && !cbk_catarro.isChecked() && !cbk_engasgo.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(pescoco.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                pescoco.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        btn_dor_garganta = findViewById(R.id.btn_dor_garganta);
//        btn_dor_garganta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pescoco.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor de garganta");
//                startActivity(i);
//                pescoco.this.finish();
//            }
//        });
//
//        btn_difi_engolir = findViewById(R.id.btn_difi_engolir);
//        btn_difi_engolir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pescoco.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dificuldade de engolir");
//                startActivity(i);
//                pescoco.this.finish();
//            }
//        });
//
//        btn_catarro = findViewById(R.id.btn_catarro);
//        btn_catarro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pescoco.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Catarro");
//                startActivity(i);
//                pescoco.this.finish();
//            }
//        });
//
//        btn_engasgo = findViewById(R.id.btn_engasgo);
//        btn_engasgo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pescoco.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Engasgo");
//                startActivity(i);
//                pescoco.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                pescoco.this.finish();
//            }
//        });
    }
}
