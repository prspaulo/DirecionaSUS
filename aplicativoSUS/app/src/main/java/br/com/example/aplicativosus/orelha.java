package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class orelha extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_ouvido, cbk_secrecao, cbk_sangramento, cbk_entupimento, cbk_dimi_audicao;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orelha);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_ouvido = (CheckBox)findViewById(R.id.cbx_dor_ouvido);
        cbk_secrecao = (CheckBox)findViewById(R.id.cbx_secrecao);
        cbk_sangramento = (CheckBox)findViewById(R.id.cbx_sangramento);
        cbk_entupimento = (CheckBox)findViewById(R.id.cbx_entupimento);
        cbk_dimi_audicao = (CheckBox)findViewById(R.id.cbx_dimi_audicao);
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
                if(cbk_dor_ouvido.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor no ouvido");
                }
                if(cbk_secrecao.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Secreção");
                }
                if(cbk_sangramento.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Sangramento");
                }
                if(cbk_entupimento.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Entupimento");
                }
                if(cbk_dimi_audicao.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas.add("Diminuição da audição");
                }
                if(!cbk_dor_ouvido.isChecked() && !cbk_secrecao.isChecked()
                        && !cbk_sangramento.isChecked() && !cbk_entupimento.isChecked()
                        && !cbk_dimi_audicao.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(orelha.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                orelha.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_dor_ouvido = findViewById(R.id.btn_dor_ouvido);
//        btn_dor_ouvido.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(orelha.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor no ouvido");
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
//
//        btn_secrecao = findViewById(R.id.btn_secrecao);
//        btn_secrecao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(orelha.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Secreção");
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
//
//        btn_sangue = findViewById(R.id.btn_sangue);
//        btn_sangue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(orelha.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Sangramento");
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
//
//        btn_entupimento = findViewById(R.id.btn_entupimento);
//        btn_entupimento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(orelha.this, mapa.class);
//                i.putExtra("servico", "2");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Entupimento");
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
//
//        btn_dimi_audicao = findViewById(R.id.btn_dimi_audicao);
//        btn_dimi_audicao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(orelha.this, mapa.class);
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Diminuição da audição");
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                orelha.this.finish();
//            }
//        });
    }
}
