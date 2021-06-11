package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class coracao extends AppCompatActivity {

    Button btn_gerar_rota;

    CheckBox cbk_bat_rapido, cbk_bat_fraco, cbk_bat_forte, cbk_bat_semritmo, cbk_dor_peito;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coracao);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_bat_rapido = (CheckBox)findViewById(R.id.cbx_bat_rapido);
        cbk_bat_fraco = (CheckBox)findViewById(R.id.cbx_bat_fraco);
        cbk_bat_forte = (CheckBox)findViewById(R.id.cbx_bat_forte);
        cbk_bat_semritmo = (CheckBox)findViewById(R.id.cbx_bat_semritmo);
        cbk_dor_peito = (CheckBox)findViewById(R.id.cbx_dor_peito);
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
                if(cbk_bat_rapido.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Batimento rápido");
                }
                if(cbk_bat_fraco.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Batimento fraco");
                }
                if(cbk_bat_forte.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Batimento forte");
                }
                if(cbk_bat_semritmo.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Batimento sem ritmo");
                }
                if(cbk_dor_peito.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Dor no peito");
                }

                if(!cbk_bat_rapido.isChecked() && !cbk_bat_fraco.isChecked()
                        && !cbk_bat_forte.isChecked() && !cbk_bat_semritmo.isChecked() && !cbk_dor_peito.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(coracao.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                coracao.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        btn_bat_rapido = findViewById(R.id.btn_bat_rapido);
//        btn_bat_rapido.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(coracao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Batimento rápido");
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
//
//        btn_bat_fraco = findViewById(R.id.btn_bat_fraco);
//        btn_bat_fraco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(coracao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Batimento fraco");
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
//
//        btn_bat_forte = findViewById(R.id.btn_bat_forte);
//        btn_bat_forte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(coracao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Batimento forte");
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
//
//        btn_bat_semritmo = findViewById(R.id.btn_bat_semritmo);
//        btn_bat_semritmo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(coracao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Batimento sem ritmo");
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
//
//        btn_dor_peito = findViewById(R.id.btn_dor_peito);
//        btn_dor_peito.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(coracao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor");
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                coracao.this.finish();
//            }
//        });
    }
}
