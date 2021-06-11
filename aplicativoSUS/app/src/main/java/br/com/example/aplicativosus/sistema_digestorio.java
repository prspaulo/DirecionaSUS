package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class sistema_digestorio extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_abdominal, cbk_indigestao, cbk_nausea_vomito, cbk_falta_apetite, cbk_dor_dif_engolir, cbk_diarreia, cbk_intestino_preso;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sistema_digestorio);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_abdominal = (CheckBox)findViewById(R.id.cbx_dor_abdominal);
        cbk_indigestao = (CheckBox)findViewById(R.id.cbx_indigestao);
        cbk_nausea_vomito = (CheckBox)findViewById(R.id.cbx_nausea_vomito);
        cbk_falta_apetite = (CheckBox)findViewById(R.id.cbx_falta_apetite);
        cbk_dor_dif_engolir = (CheckBox)findViewById(R.id.cbx_dor_difi_engolir);
        cbk_diarreia = (CheckBox)findViewById(R.id.cbx_diarreia);
        cbk_intestino_preso = (CheckBox)findViewById(R.id.cbx_intestino_preso);
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
                if(cbk_dor_abdominal.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor abdominal");
                }
                if(cbk_indigestao.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Indigestão");
                }
                if(cbk_nausea_vomito.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Náuseas e vômitos");
                }
                if(cbk_falta_apetite.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Falta de apetite");
                }
                if(cbk_dor_dif_engolir.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Dor ou dificuldade de engolir");
                }
                if(cbk_diarreia.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Diarreia");
                }
                if(cbk_intestino_preso.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Intestino preso");
                }
                if(!cbk_dor_abdominal.isChecked() && !cbk_indigestao.isChecked()
                        && !cbk_nausea_vomito.isChecked() && !cbk_falta_apetite.isChecked()
                        && !cbk_dor_dif_engolir.isChecked() && !cbk_diarreia.isChecked()
                        && !cbk_intestino_preso.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(sistema_digestorio.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                sistema_digestorio.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_dor_abdominal = findViewById(R.id.btn_dor_abdominal);
//        btn_dor_abdominal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor abdominal");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_indigestao = findViewById(R.id.btn_indigestao);
//        btn_indigestao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Indigestão");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_nausea_vomito = findViewById(R.id.btn_nausea_vomito);
//        btn_nausea_vomito.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Náusea/Vômito");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_falta_apetite = findViewById(R.id.btn_falta_apetite);
//        btn_falta_apetite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Falta de apetite");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_dor_dif_engolir = findViewById(R.id.btn_dor_dif_engolir);
//        btn_dor_dif_engolir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dificuldade de engolir");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_diarreia = findViewById(R.id.btn_diarreia);
//        btn_diarreia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Diarréia");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_intestino_preso = findViewById(R.id.btn_intestino_preso);
//        btn_intestino_preso.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(sistema_digestorio.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Intestino preso");
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),  telainicial.class);
//                startActivity(i);
//                sistema_digestorio.this.finish();
//            }
//        });
    }
}
