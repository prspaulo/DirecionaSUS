package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class praticas_integrativas extends AppCompatActivity {

    Button btn2_liangong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.praticas_integrativas);

        btn2_liangong = findViewById(R.id.btn_liangong);
        btn2_liangong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), liangong.class);
                startActivity(i);
            }
        });
    }
}
