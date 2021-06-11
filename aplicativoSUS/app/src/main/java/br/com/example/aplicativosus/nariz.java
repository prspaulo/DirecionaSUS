package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class nariz extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_sangramento, cbk_entupimento, cbk_falta_de_ar, cbk_fratura, cbk_secrecao;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nariz);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_sangramento = (CheckBox)findViewById(R.id.cbx_sangramento);
        cbk_entupimento = (CheckBox)findViewById(R.id.cbx_entupimento);
        cbk_falta_de_ar = (CheckBox)findViewById(R.id.cbx_falta_de_ar);
        cbk_fratura = (CheckBox)findViewById(R.id.cbx_fratura);
        cbk_secrecao = (CheckBox)findViewById(R.id.cbx_secrecao);
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
                if(cbk_sangramento.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Sangramento");
                }
                if(cbk_entupimento.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Entupimento");
                }
                if(cbk_falta_de_ar.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Falta de ar");
                }
                if(cbk_fratura.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Fratura");
                }
                if(cbk_secrecao.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Secreção");
                }
                if(!cbk_sangramento.isChecked() && !cbk_entupimento.isChecked()
                        && !cbk_falta_de_ar.isChecked() && !cbk_fratura.isChecked()
                        && !cbk_secrecao.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(nariz.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                nariz.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_sangue = findViewById(R.id.btn_sangue);
//        btn_sangue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(nariz.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Sangramento");
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
//
//        btn_entupimento = findViewById(R.id.btn_entupimento);
//        btn_entupimento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(nariz.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Entupimento");
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
//
//        btn_falta_ar = findViewById(R.id.btn_falta_ar);
//        btn_falta_ar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(nariz.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Falta de ar");
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
//
//        btn_fratura = findViewById(R.id.btn_fratura);
//        btn_fratura.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(nariz.this, mapa.class);
//                i.putExtra("servico", "4");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Fratura");
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
//
//        btn_secrecao = findViewById(R.id.btn_secrecao);
//        btn_secrecao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(nariz.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Secreção");
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                nariz.this.finish();
//            }
//        });
    }
}
