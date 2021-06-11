package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class pulmao extends AppCompatActivity {

    Button btn_gerar_rota;
    CheckBox cbk_falta_ar, cbk_engasgo, cbk_resp_rapida, cbk_chiado, cbk_dor_resp, cbk_tosse, cbk_escarro_sangue, cbk_enforcamento;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulmao);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_falta_ar = (CheckBox)findViewById(R.id.cbx_falta_ar);
        cbk_engasgo = (CheckBox)findViewById(R.id.cbx_engasgo);
        cbk_resp_rapida = (CheckBox)findViewById(R.id.cbx_resp_rapida);
        cbk_chiado = (CheckBox)findViewById(R.id.cbx_chiado);
        cbk_dor_resp = (CheckBox)findViewById(R.id.cbx_dor_resp);
        cbk_tosse = (CheckBox)findViewById(R.id.cbx_tosse);
        cbk_escarro_sangue = (CheckBox)findViewById(R.id.cbx_escarro_sangue);
        cbk_enforcamento = (CheckBox)findViewById(R.id.cbx_enforcamento);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        final String regiao_sintoma = i.getStringExtra("regiao");
        final String onde_incomoda = i.getStringExtra("onde");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        btn_gerar_rota = findViewById(R.id.btn_gerar_rota);
        btn_gerar_rota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String result = "Sintomas: ";
                if(cbk_falta_ar.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Falta de ar");
                }
                if(cbk_engasgo.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Engasgo");
                }
                if(cbk_resp_rapida.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Respiração rápida");
                }
                if(cbk_chiado.isChecked()){
                    //result += "\nChiado";
                    sintomas.add("Chiado");
                }
                if(cbk_dor_resp.isChecked()){
                    //result += "\nDor ao respirar";
                    sintomas.add("Dor ao respirar");
                }
                if(cbk_tosse.isChecked()){
                    //result += "\nTosse";
                    sintomas.add("Tosse");
                }
                if(cbk_escarro_sangue.isChecked()){
                    //result += "\nEscarro com sangue";
                    sintomas.add("Escarro com sangue");
                }
                if(cbk_enforcamento.isChecked()){
                    //result += "\nEnforcamento";
                    sintomas.add("Enforcamento");
                }

                if(!cbk_falta_ar.isChecked() && !cbk_engasgo.isChecked() && !cbk_resp_rapida.isChecked() && !cbk_chiado.isChecked() &&
                        !cbk_dor_resp.isChecked() && !cbk_tosse.isChecked() && !cbk_escarro_sangue.isChecked() && !cbk_enforcamento.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }

                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                Intent i = new Intent(pulmao.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                pulmao.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_falta_ar = findViewById(R.id.btn_falta_ar);
//        btn_falta_ar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Falta de ar");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_engasgo = findViewById(R.id.btn_engasgo);
//        btn_engasgo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Engasgo");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_resp_rapida = findViewById(R.id.btn_resp_rapida);
//        btn_resp_rapida.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Respiração rápida");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_chiado = findViewById(R.id.btn_chiado);
//        btn_chiado.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Chiado");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_dor_respirar = findViewById(R.id.btn_dor_respirar);
//        btn_dor_respirar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor ao respirar");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_tosse = findViewById(R.id.btn_tosse);
//        btn_tosse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Tosse");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_escarro_sangue = findViewById(R.id.btn_escarro_sangue);
//        btn_escarro_sangue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Escarro com sangue");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });
//
//        btn_enforcamento = findViewById(R.id.btn_enforcamento);
//        btn_enforcamento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(pulmao.this, mapa.class);
//                i.putExtra("servico", "3");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Enforcamento");
//                startActivity(i);
//                pulmao.this.finish();
//            }
//        });

    }
}
