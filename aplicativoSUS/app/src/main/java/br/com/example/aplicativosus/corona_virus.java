package br.com.example.aplicativosus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class corona_virus extends AppCompatActivity {

    Button btn_confirmar;
    String temDoenca;
    int idade;
    ArrayList<String> sintomas_corona = new ArrayList<String>();
    ArrayList<String> doencas_risco = new ArrayList<String>();
    CheckBox cbk_cansaco, cbk_febre, cbk_tosse, cbk_dif_respirar;
    RadioButton rdSim, rdNao, rdMenorde18, rdMaior18Menor40, rdMaior39Menor59, rdMaior60;


    CheckBox cbk_insuficiencia_card, cbk_cardiopatia, cbk_asma, cbk_imunodepressao, cbk_doenca_renal, cbk_diabetes, cbk_doenca_cromossomica, cbk_gestacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_virus);

        cbk_insuficiencia_card = (CheckBox) findViewById(R.id.cbx_insuficiencia_cardiaca);
        cbk_cardiopatia = (CheckBox) findViewById(R.id.cbx_cardiopatia);
        cbk_asma = (CheckBox) findViewById(R.id.cbx_asma);
        cbk_imunodepressao = (CheckBox) findViewById(R.id.cbx_imunodepressao);
        cbk_doenca_renal = (CheckBox) findViewById(R.id.cbx_doenca_renal);
        cbk_diabetes = (CheckBox) findViewById(R.id.cbx_diabetes);
        cbk_doenca_cromossomica = (CheckBox) findViewById(R.id.cbx_doencas_cromossomicas);
        cbk_gestacao = (CheckBox) findViewById(R.id.cbx_gestacao);

        cbk_insuficiencia_card.setEnabled(false);
        cbk_cardiopatia.setEnabled(false);
        cbk_asma.setEnabled(false);
        cbk_imunodepressao.setEnabled(false);
        cbk_doenca_renal.setEnabled(false);
        cbk_diabetes.setEnabled(false);
        cbk_doenca_cromossomica.setEnabled(false);
        cbk_gestacao.setEnabled(false);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        cbk_cansaco = (CheckBox)findViewById(R.id.cbx_cansaco);
        cbk_febre = (CheckBox)findViewById(R.id.cbx_febre);
        cbk_tosse = (CheckBox)findViewById(R.id.cbx_tosse);
        cbk_dif_respirar = (CheckBox)findViewById(R.id.cbx_dif_respirar);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdSim = (RadioButton) findViewById(R.id.rdSim);
        rdNao = (RadioButton) findViewById(R.id.rdNao);

        rdSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdSim.isChecked()){
                    cbk_insuficiencia_card.setEnabled(true);
                    cbk_cardiopatia.setEnabled(true);
                    cbk_asma.setEnabled(true);
                    cbk_imunodepressao.setEnabled(true);
                    cbk_doenca_renal.setEnabled(true);
                    cbk_diabetes.setEnabled(true);
                    cbk_doenca_cromossomica.setEnabled(true);
                    cbk_gestacao.setEnabled(true);
                    rdNao.setChecked(false);
                    temDoenca = "S";
                }
            }
        });

        rdNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdNao.isChecked()){
                    cbk_insuficiencia_card.setEnabled(false);
                    cbk_cardiopatia.setEnabled(false);
                    cbk_asma.setEnabled(false);
                    cbk_imunodepressao.setEnabled(false);
                    cbk_doenca_renal.setEnabled(false);
                    cbk_diabetes.setEnabled(false);
                    cbk_doenca_cromossomica.setEnabled(false);
                    cbk_gestacao.setEnabled(false);
                    rdSim.setChecked(false);
                    temDoenca = "N";
                }
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //rdMenorde18, rdMaior18Menor40, rdMaior39Menor59, rdMaior60
        rdMenorde18 = (RadioButton) findViewById(R.id.rdMenorde18);
        rdMaior18Menor40 = (RadioButton) findViewById(R.id.rdMaior18Menor40);
        rdMaior39Menor59 = (RadioButton) findViewById(R.id.rdMaior39Menor59);
        rdMaior60 = (RadioButton) findViewById(R.id.rdMaior60);

        rdMenorde18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdMenorde18.isChecked()){
                    rdMaior18Menor40.setChecked(false);
                    rdMaior39Menor59.setChecked(false);
                    rdMaior60.setChecked(false);
                    idade = 1;
                }
            }
        });
        rdMaior18Menor40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdMaior18Menor40.isChecked()){
                    rdMenorde18.setChecked(false);
                    rdMaior39Menor59.setChecked(false);
                    rdMaior60.setChecked(false);
                    idade = 2;
                }
            }
        });
        rdMaior39Menor59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdMaior39Menor59.isChecked()){
                    rdMaior18Menor40.setChecked(false);
                    rdMenorde18.setChecked(false);
                    rdMaior60.setChecked(false);
                    idade = 3;
                }
            }
        });
        rdMaior60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdMaior60.isChecked()){
                    rdMaior18Menor40.setChecked(false);
                    rdMaior39Menor59.setChecked(false);
                    rdMenorde18.setChecked(false);
                    idade = 4;
                }
            }
        });

        btn_confirmar = findViewById(R.id.btn_confirm);
        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(cbk_cansaco.isChecked()){
                    //result += "\nFalta de ar";
                    sintomas_corona.add("Cansaço");
                }
                if(cbk_febre.isChecked()){
                    //result += "\nEngasgo";
                    sintomas_corona.add("Febre");
                }
                if(cbk_tosse.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas_corona.add("Tosse");
                }
                if(cbk_dif_respirar.isChecked()){
                    //result += "\nRespiração rápida";
                    sintomas_corona.add("Dificuldde de respirar");
                }

                if(temDoenca.equals("S")){
                    if(cbk_insuficiencia_card.isChecked()){
                        //result += "\nFalta de ar";
                        doencas_risco.add("Insuficiência cardiáca");
                    }
                    if(cbk_cardiopatia.isChecked()){
                        //result += "\nEngasgo";
                        doencas_risco.add("Cardiopatia");
                    }
                    if(cbk_asma.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Asma");
                    }
                    if(cbk_imunodepressao.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Imunodepressão");
                    }
                    if(cbk_doenca_renal.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Doença renal");
                    }
                    if(cbk_diabetes.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Diabetes");
                    }
                    if(cbk_doenca_cromossomica.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Doença cromossômica");
                    }
                    if(cbk_gestacao.isChecked()){
                        //result += "\nRespiração rápida";
                        doencas_risco.add("Gestação");
                    }
                }

//                if(cbk_febre.isChecked() && cbk_dif_respirar.isChecked())

//                if(!cbk_cansaco.isChecked() && !cbk_febre.isChecked() && !cbk_tosse.isChecked() && !cbk_dif_respirar.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Todas as perguntas devem ser respondidas", Toast.LENGTH_LONG).show();
//                }
//                else if(cbk_febre.isChecked() )
            }
        });

    }
}
