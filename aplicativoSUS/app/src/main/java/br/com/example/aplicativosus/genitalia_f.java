package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class genitalia_f extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_traba_parto, cbk_cheiro_forte, cbk_coceira, cbk_corrimento, cbk_ferida, cbk_atraso_menst, cbk_dor;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genitalia_f);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_traba_parto = (CheckBox)findViewById(R.id.cbx_traba_parto);
        cbk_cheiro_forte = (CheckBox)findViewById(R.id.cbx_cheiro_forte);
        cbk_coceira = (CheckBox)findViewById(R.id.cbx_coceira);
        cbk_corrimento = (CheckBox)findViewById(R.id.cbx_corri_vagina);
        cbk_ferida = (CheckBox)findViewById(R.id.cbx_feridas);
        cbk_atraso_menst = (CheckBox)findViewById(R.id.cbx_atraso_menst);
        cbk_dor = (CheckBox)findViewById(R.id.cbx_dor);
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
                if(cbk_traba_parto.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Trabalho de parto");
                }
                if(cbk_cheiro_forte.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Cheiro forte");
                }
                if(cbk_coceira.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Coceira");
                }
                if(cbk_corrimento.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Corrimento na vagina");
                }
                if(cbk_ferida.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Ferida");
                }
                if(cbk_atraso_menst.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Atraso da menstruação");
                }
                if(cbk_dor.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Dor");
                }
                if(!cbk_traba_parto.isChecked() && !cbk_cheiro_forte.isChecked()
                        && !cbk_coceira.isChecked() && !cbk_corrimento.isChecked()
                        && !cbk_ferida.isChecked() && !cbk_atraso_menst.isChecked()
                        && !cbk_dor.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(genitalia_f.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                genitalia_f.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_traba_parto = findViewById(R.id.btn_traba_parto);
//        btn_traba_parto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), maternidades.class);
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Torção");
//                startActivity(i);
//            }
//        });
//
//        btn_odor_forte = findViewById(R.id.btn_odor_forte);
//        btn_odor_forte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Odor forte");
//                startActivity(i);
//            }
//        });
//
//        btn_coceira = findViewById(R.id.btn_coceira);
//        btn_coceira.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Coceira");
//                startActivity(i);
//            }
//        });
//
//        btn_corrimento = findViewById(R.id.btn_corrimento);
//        btn_corrimento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Corrimento na vagina");
//                startActivity(i);
//            }
//        });
//
//        btn_ferida = findViewById(R.id.btn_ferida);
//        btn_ferida.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Ferida");
//                startActivity(i);
//            }
//        });
//
//        btn_atraso_menst = findViewById(R.id.btn_atraso_menst);
//        btn_atraso_menst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Atraso na menstruação");
//                startActivity(i);
//            }
//        });
//
//        btn_dor = findViewById(R.id.btn_dor);
//        btn_dor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_f.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Dor");
//                startActivity(i);
//            }
//        });
    }
}
