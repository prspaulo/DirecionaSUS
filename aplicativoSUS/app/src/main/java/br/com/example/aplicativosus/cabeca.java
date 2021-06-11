package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class cabeca extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_de_cabeca, cbk_nodulo_caroco, cbk_inchaco, cbk_queimacao, cbk_corte;
    ArrayList<String> sintomas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cabeca);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_de_cabeca = (CheckBox)findViewById(R.id.cbx_dor_de_cabeca);
        cbk_nodulo_caroco = (CheckBox)findViewById(R.id.cbx_nodulo_caroco);
        cbk_inchaco = (CheckBox)findViewById(R.id.cbx_inchaco);
        cbk_queimacao = (CheckBox)findViewById(R.id.cbx_queimacao);
        cbk_corte = (CheckBox)findViewById(R.id.cbx_corte);
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
                if(cbk_dor_de_cabeca.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor de cabeça");
                }
                if(cbk_nodulo_caroco.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Nódulo/Caroço");
                }
                if(cbk_inchaco.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Inchaço");
                }
                if(cbk_queimacao.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Queimação");
                }
                if(cbk_corte.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Corte");
                }

                if(!cbk_dor_de_cabeca.isChecked() && !cbk_nodulo_caroco.isChecked()
                        && !cbk_inchaco.isChecked() && !cbk_queimacao.isChecked() && !cbk_corte.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(cabeca.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                cabeca.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_d_cabeca = findViewById(R.id.btn_dor_cabeca);
//        btn_d_cabeca.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cabeca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor de cabeça");
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
//
//        btn_nodulo = findViewById(R.id.btn_nodulo_caroco);
//        btn_nodulo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cabeca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Nódulo/Caroço");
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
//
//        btn2_inchaco = findViewById(R.id.btn_inchaco);
//        btn2_inchaco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cabeca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Inchaço");
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
//
//        btn2_queimacao = findViewById(R.id.btn_queimacao);
//        btn2_queimacao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cabeca.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Queimação");
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
//
//        btn2_corte = findViewById(R.id.btn_corte);
//        btn2_corte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cabeca.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Corte");
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                cabeca.this.finish();
//            }
//        });
    }
}
