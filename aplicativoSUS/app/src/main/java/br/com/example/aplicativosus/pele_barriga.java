package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class pele_barriga extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_vermelhidao, cbk_coceira, cbk_inchaco, cbk_picada, cbk_descamacao, cbk_bolha, cbk_queimadura, cbk_corte, cbk_mancha;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pele_barriga);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_vermelhidao = (CheckBox)findViewById(R.id.cbx_vermelhidao);
        cbk_coceira = (CheckBox)findViewById(R.id.cbx_coceira);
        cbk_inchaco = (CheckBox)findViewById(R.id.cbx_inchaco);
        cbk_picada = (CheckBox)findViewById(R.id.cbx_picada);
        cbk_descamacao = (CheckBox)findViewById(R.id.cbx_descamacao);
        cbk_bolha = (CheckBox)findViewById(R.id.cbx_bolha);
        cbk_queimadura = (CheckBox)findViewById(R.id.cbx_queimadura);
        cbk_corte = (CheckBox)findViewById(R.id.cbx_corte);
        cbk_mancha = (CheckBox)findViewById(R.id.cbx_mancha);
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
                if(cbk_vermelhidao.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Vermelhidão");
                }
                if(cbk_coceira.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Coceira");
                }
                if(cbk_inchaco.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Inchaço");
                }
                if(cbk_picada.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Picada");
                }
                if(cbk_descamacao.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Descamação");
                }
                if(cbk_bolha.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Bolha");
                }
                if(cbk_queimadura.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Queimadura");
                }
                if(cbk_corte.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Corte");
                }
                if(cbk_mancha.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Mancha");
                }

                if(!cbk_vermelhidao.isChecked() && !cbk_coceira.isChecked()
                        && !cbk_inchaco.isChecked() && !cbk_picada.isChecked() && !cbk_descamacao.isChecked()
                        && !cbk_bolha.isChecked() && !cbk_queimadura.isChecked() && !cbk_corte.isChecked()
                        && !cbk_mancha.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(pele_barriga.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                pele_barriga.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_vermelhidao = findViewById(R.id.btn_vermelhidao);
//        btn_vermelhidao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Vermelhidão");
//                startActivity(i);
//            }
//        });
//
//        btn_coceira = findViewById(R.id.btn_coceira);
//        btn_coceira.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Coceira");
//                startActivity(i);
//            }
//        });
//
//        btn_inchaco = findViewById(R.id.btn_inchaco);
//        btn_inchaco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Inchaço");
//                startActivity(i);
//            }
//        });
//
//        btn_picada = findViewById(R.id.btn_picada);
//        btn_picada.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Picada");
//                startActivity(i);
//            }
//        });
//
//        btn_descamacao = findViewById(R.id.btn_descamacao);
//        btn_descamacao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Descamação");
//                startActivity(i);
//            }
//        });
//
//        btn_bolha = findViewById(R.id.btn_bolha);
//        btn_bolha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Bolha");
//                startActivity(i);
//            }
//        });
//
//        btn_queimadura = findViewById(R.id.btn_queimadura);
//        btn_queimadura.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "3");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Queimadura");
//                startActivity(i);
//            }
//        });
//
//        btn_corte = findViewById(R.id.btn_corte);
//        btn_corte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Corte");
//                startActivity(i);
//            }
//        });
//
//        btn_mancha = findViewById(R.id.btn_mancha);
//        btn_mancha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pele_barriga.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Mancha");
//                startActivity(i);
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//            }
//        });
    }
}
