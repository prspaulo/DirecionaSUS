package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class olhos extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_ardencia, cbk_dif_enxergar, cbk_inchaco, cbk_corte, cbk_vermelho, cbk_coceira, cbk_machucado, cbk_caroco;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.olhos);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_ardencia = (CheckBox)findViewById(R.id.cbx_ardencia);
        cbk_dif_enxergar = (CheckBox)findViewById(R.id.cbx_dif_enxergar);
        cbk_inchaco = (CheckBox)findViewById(R.id.cbx_inchaco);
        cbk_corte = (CheckBox)findViewById(R.id.cbx_corte);
        cbk_coceira = (CheckBox)findViewById(R.id.cbx_coceira);
        cbk_machucado = (CheckBox)findViewById(R.id.cbx_machucado);
        cbk_vermelho = (CheckBox)findViewById(R.id.cbx_vermelho);
        cbk_caroco = (CheckBox)findViewById(R.id.cbx_caroco);
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
                if(cbk_ardencia.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Ardência");
                }
                if(cbk_dif_enxergar.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Dificuldade de enxergar");
                }
                if(cbk_inchaco.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Inchaço");
                }
                if(cbk_corte.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Corte");
                }
                if(cbk_coceira.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Coceira");
                }
                if(cbk_machucado.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Machucado");
                }
                if(cbk_vermelho.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Vermelho");
                }
                if(cbk_caroco.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Caroço");
                }
                if(!cbk_ardencia.isChecked() && !cbk_dif_enxergar.isChecked()
                        && !cbk_inchaco.isChecked() && !cbk_corte.isChecked()
                        && !cbk_coceira.isChecked() && !cbk_machucado.isChecked()
                        && !cbk_vermelho.isChecked() && !cbk_caroco.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(olhos.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                olhos.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        btn_ardencia = findViewById(R.id.btn_ardencia);
//        btn_ardencia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Ardência");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_dif_enxergar = findViewById(R.id.btn_dif_exergar);
//        btn_dif_enxergar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dificuldade de enxergar");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_inchaco = findViewById(R.id.btn_inchaco);
//        btn_inchaco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Inchaço");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_corte = findViewById(R.id.btn_corte);
//        btn_corte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Corte");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_vermelho = findViewById(R.id.btn_vermelho);
//        btn_vermelho.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Vermelhidão");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_coceira = findViewById(R.id.btn_coceira);
//        btn_coceira.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Coceira");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_machucado = findViewById(R.id.btn_machucado);
//        btn_machucado.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Machucado");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
//
//        btn_caroco = findViewById(R.id.btn_caroco);
//        btn_caroco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(olhos.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Caroço");
//                startActivity(i);
//                olhos.this.finish();
//            }
//        });
    }
}
