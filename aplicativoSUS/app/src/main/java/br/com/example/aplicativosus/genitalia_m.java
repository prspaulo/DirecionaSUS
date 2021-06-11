package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class genitalia_m extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_torcao, cbk_erec_dolorosa, cbk_coceira, cbk_odor_forte, cbk_secrecao, cbk_dor, cbk_sexo, cbk_verruga, cbk_feridas, cbk_dst;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genitalia_m);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_torcao = (CheckBox)findViewById(R.id.cbx_torcao);
        cbk_erec_dolorosa = (CheckBox)findViewById(R.id.cbx_erec_dolorosa);
        cbk_coceira = (CheckBox)findViewById(R.id.cbx_coceira);
        cbk_odor_forte = (CheckBox)findViewById(R.id.cbx_urina_odor_forte);
        cbk_secrecao = (CheckBox)findViewById(R.id.cbx_secrecao);
        cbk_dor = (CheckBox)findViewById(R.id.cbx_dor);
        cbk_sexo = (CheckBox)findViewById(R.id.cbx_sexo);
        cbk_verruga = (CheckBox)findViewById(R.id.cbx_verrugas);
        cbk_feridas = (CheckBox)findViewById(R.id.cbx_feridas);
        cbk_dst = (CheckBox)findViewById(R.id.cbx_dst);
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
                if(cbk_torcao.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Torção");
                }
                if(cbk_erec_dolorosa.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Ereção dolorosa");
                }
                if(cbk_coceira.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Coceira");
                }
                if(cbk_odor_forte.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Odor forte");
                }
                if(cbk_secrecao.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Secreção");
                }
                if(cbk_dor.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Dor");
                }
                if(cbk_sexo.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Sexo");
                }
                if(cbk_verruga.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Verrugas");
                }
                if(cbk_feridas.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Feridas");
                }
                if(cbk_dst.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("DST");
                }
                if(!cbk_torcao.isChecked() && !cbk_erec_dolorosa.isChecked()
                        && !cbk_coceira.isChecked() && !cbk_odor_forte.isChecked()
                        && !cbk_secrecao.isChecked() && !cbk_dor.isChecked()
                        && !cbk_sexo.isChecked() && !cbk_verruga.isChecked()
                        && !cbk_feridas.isChecked() && !cbk_dst.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(genitalia_m.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                genitalia_m.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_torcao = findViewById(R.id.btn_torcao);
//        btn_torcao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "3");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Torção");
//                startActivity(i);
//            }
//        });
//
//        btn_erec_dolorosa = findViewById(R.id.btn_erec_dolorosa);
//        btn_erec_dolorosa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "3");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Ereção dolorosa");
//                startActivity(i);
//            }
//        });
//
//        btn_coceira = findViewById(R.id.btn_coceira);
//        btn_coceira.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Coceira");
//                startActivity(i);
//            }
//        });
//
//        btn_odor_forte = findViewById(R.id.btn_odor_forte);
//        btn_odor_forte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Odor forte");
//                startActivity(i);
//            }
//        });
//
//        btn_secrecao = findViewById(R.id.btn_secrecao);
//        btn_secrecao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Secreção");
//                startActivity(i);
//            }
//        });
//
//        btn_dor = findViewById(R.id.btn_dor);
//        btn_dor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Dor");
//                startActivity(i);
//            }
//        });
//
//        btn_sexo = findViewById(R.id.btn_sexo);
//        btn_sexo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Sexo");
//                startActivity(i);
//            }
//        });
//
//        btn_verrugas = findViewById(R.id.btn_verrugas);
//        btn_verrugas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Verrugas");
//                startActivity(i);
//            }
//        });
//
//        btn_feridas = findViewById(R.id.btn_feridas);
//        btn_feridas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Ferida");
//                startActivity(i);
//            }
//        });
//
//        btn_dst = findViewById(R.id.btn_dst);
//        btn_dst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(genitalia_m.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "DST");
//                startActivity(i);
//            }
//        });
    }
}
