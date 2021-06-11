package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class perna extends AppCompatActivity {

    Button btn_gerar_rota;

    CheckBox cbk_dor_muscular, cbk_fratura, cbk_torcao, cbk_est_muscular, cbk_corte, cbk_desloc_articular;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perna);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_muscular = (CheckBox)findViewById(R.id.cbx_dor_muscular);
        cbk_fratura = (CheckBox)findViewById(R.id.cbx_fratura);
        cbk_est_muscular = (CheckBox)findViewById(R.id.cbx_estiramento_muscular);
        cbk_corte = (CheckBox)findViewById(R.id.cbx_corte);
        cbk_desloc_articular = (CheckBox)findViewById(R.id.cbx_deslocamento_articular);
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
                if(cbk_dor_muscular.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor muscular");
                }
                if(cbk_fratura.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Fratura");
                }
                if(cbk_est_muscular.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Estiramento muscular");
                }
                if(cbk_corte.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Corte");
                }
                if(cbk_desloc_articular.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Deslocamento articular");
                }
                if(!cbk_dor_muscular.isChecked() && !cbk_fratura.isChecked()
                        && !cbk_est_muscular.isChecked() && !cbk_corte.isChecked() && !cbk_desloc_articular.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(perna.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                perna.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        btn_dor_muscular = findViewById(R.id.btn_dor_muscular);
//        btn_dor_muscular.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "1");
//                startActivity(i);
//            }
//        });
//
//        btn_fratura = findViewById(R.id.btn_fratura);
//        btn_fratura.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "4");
//                startActivity(i);
//            }
//        });
//
//        btn_torcao = findViewById(R.id.btn_torcao);
//        btn_torcao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "4");
//                startActivity(i);
//            }
//        });
//
//        btn_est_muscular = findViewById(R.id.btn_est_muscular);
//        btn_est_muscular.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "1");
//                startActivity(i);
//            }
//        });
//
//        btn_corte = findViewById(R.id.btn_corte);
//        btn_corte.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "2");
//                startActivity(i);
//            }
//        });
//
//        btn_desc_muscular = findViewById(R.id.btn_desc_articular);
//        btn_desc_muscular.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(perna.this, mapa.class);
//                i.putExtra("servico", "4");
//                startActivity(i);
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),  telainicial.class);
//                startActivity(i);
//            }
//        });
    }
}
