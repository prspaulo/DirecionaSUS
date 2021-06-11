package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;


public class rins_trato_urinario extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_rim, cbk_freq_urinar, cbk_jato_diminuido, cbk_aum_vol_urina, cbk_incont_urinaria, cbk_sangue_urina, cbk_odor_forte, cbk_urina_espuma;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rins_trato_urinario);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_rim = (CheckBox)findViewById(R.id.cbx_dor_rim);
        cbk_freq_urinar = (CheckBox)findViewById(R.id.cbx_freq_urinar);
        cbk_jato_diminuido = (CheckBox)findViewById(R.id.cbx_jato_diminuido);
        cbk_aum_vol_urina = (CheckBox)findViewById(R.id.cbx_aum_vol_urina);
        cbk_incont_urinaria = (CheckBox)findViewById(R.id.cbx_incont_urinaria);
        cbk_sangue_urina = (CheckBox)findViewById(R.id.cbx_sangue_urina);
        cbk_odor_forte = (CheckBox)findViewById(R.id.cbx_urina_odor_forte);
        cbk_urina_espuma = (CheckBox)findViewById(R.id.cbx_urina_com_espuma);
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
                if(cbk_dor_rim.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor no rim");
                }
                if(cbk_freq_urinar.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Dor, Urgência, Maior frequência para urinar");
                }
                if(cbk_jato_diminuido.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Jato diminuído em homens");
                }
                if(cbk_aum_vol_urina.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Aumento do volume de urina");
                }
                if(cbk_incont_urinaria.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Incontinência urinária");
                }
                if(cbk_sangue_urina.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Sangue na urina");
                }
                if(cbk_odor_forte.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Urina com odor forte");
                }
                if(cbk_urina_espuma.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Urina com espuma");
                }
                if(!cbk_freq_urinar.isChecked() && !cbk_jato_diminuido.isChecked()
                        && !cbk_aum_vol_urina.isChecked() && !cbk_incont_urinaria.isChecked()
                        && !cbk_sangue_urina.isChecked() && !cbk_odor_forte.isChecked()
                        && !cbk_urina_espuma.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                rins_trato_urinario.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_dor_rim = findViewById(R.id.btn_dor_rim);
//        btn_dor_rim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Dor no rim");
//                startActivity(i);
//            }
//        });
//
//        btn_freq_urinar = findViewById(R.id.btn_freq_urinar);
//        btn_freq_urinar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Dor, Urgência, Maior frequência para urinar");
//                startActivity(i);
//            }
//        });
//
//        btn_jato_diminuido = findViewById(R.id.btn_jato_diminuido);
//        btn_jato_diminuido.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Jato diminuído em homens");
//                startActivity(i);
//            }
//        });
//
//        btn_aum_vol_urina = findViewById(R.id.btn_aum_vol_urina);
//        btn_aum_vol_urina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Aumento do volume de urina");
//                startActivity(i);
//            }
//        });
//
//        btn_incont_urinaria = findViewById(R.id.btn_incont_urinaria);
//        btn_incont_urinaria.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Incontinência urinária");
//                startActivity(i);
//            }
//        });
//
//        btn_sangue_urina = findViewById(R.id.btn_sangue_urina);
//        btn_sangue_urina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Sangue na urina");
//                startActivity(i);
//            }
//        });
//
//        btn_odor_forte = findViewById(R.id.btn_urina_odor_forte);
//        btn_odor_forte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Urina com odor forte");
//                startActivity(i);
//            }
//        });
//
//        btn_urina_espuma = findViewById(R.id.btn_urina_com_espuma);
//        btn_urina_espuma.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(rins_trato_urinario.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("sintoma", "Urina com espuma");
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
