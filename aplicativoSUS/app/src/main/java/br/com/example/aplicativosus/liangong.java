package br.com.example.aplicativosus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class liangong extends AppCompatActivity {
    Button btn_ondeencontrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liangong);

        btn_ondeencontrar = findViewById(R.id.btn_ondeencontrar);
        btn_ondeencontrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), onde_liangon.class);
                startActivity(i);
            }
        });
    }
}
