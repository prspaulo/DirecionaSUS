package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class costas extends AppCompatActivity {

    //postos de saude == 1, upas == 2, hospitais == 3 e serviços de emergÊncia == 4
    Button btn_gerar_rota;

    CheckBox cbk_dor_costas, cbk_entortamento, cbk_fratura;
    ArrayList<String> sintomas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.costas);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_dor_costas = (CheckBox)findViewById(R.id.cbx_dor_costas);
        cbk_entortamento = (CheckBox)findViewById(R.id.cbx_entortamento);
        cbk_fratura = (CheckBox)findViewById(R.id.cbx_fratura);
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
                if(cbk_dor_costas.isChecked()){
                    //result += "\nEngasgo";
                    sintomas.add("Dor nas costas");
                }
                if(cbk_entortamento.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Entortamento");
                }
                if(cbk_fratura.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas.add("Fratura");
                }
                if(!cbk_dor_costas.isChecked() && !cbk_entortamento.isChecked()
                        && !cbk_fratura.isChecked()){
                    result = "Selecione ao menos um sintoma para continuar";
                }
                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent i = new Intent(costas.this, mapa.class);
                i.putExtra("servico", "2");
                i.putExtra("regiao_sintoma", regiao_sintoma);
                i.putExtra("onde", onde_incomoda);
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
                costas.this.finish();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        btn_dor_costas = findViewById(R.id.btn_dor_costas);
//        btn_dor_costas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(costas.this, mapa.class);
//                i.putExtra("servico", "1");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Dor nas costas");
//                startActivity(i);
//                costas.this.finish();
//            }
//        });
//
//        btn_entortamento = findViewById(R.id.btn_entortamento);
//        btn_entortamento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(costas.this, mapa.class);
//                i.putExtra("servico", "4");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Entortamento");
//                startActivity(i);
//                costas.this.finish();
//            }
//        });
//
//        btn_fratura = findViewById(R.id.btn_fratura);
//        btn_fratura.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(costas.this, mapa.class);
//                i.putExtra("servico", "4");
//                i.putExtra("regiao_sintoma", regiao_sintoma);
//                i.putExtra("onde", onde_incomoda);
//                i.putExtra("sintoma", "Fratura");
//                startActivity(i);
//                costas.this.finish();
//            }
//        });
//
//        btn_menu = findViewById(R.id.btn_menu);
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), telainicial.class);
//                startActivity(i);
//                costas.this.finish();
//            }
//        });
    }
}
