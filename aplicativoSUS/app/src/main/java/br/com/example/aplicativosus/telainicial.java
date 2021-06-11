package br.com.example.aplicativosus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class telainicial extends AppCompatActivity {

    final Context context = this;
    private TextView textBV;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userId;

    Button btn_sintomas, btn_corona_virus, btn_servicos, btn_perfil, btn_sobre_o_SUS, btn_salerta;
    ArrayList<String> sintomas = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telainicial);

        btn_sintomas = findViewById(R.id.btn_sintoma);
        btn_sintomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), continuar_sem_sinais_alerta.class);
                startActivity(i);
            }
        });

        btn_salerta = findViewById(R.id.btn_sinais_alerta);
        btn_salerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sintomas.add("Urgência");
                Intent i = new Intent(getApplicationContext(), mapa.class);
                i.putExtra("servico", "4");
                i.putExtra("regiao_sintoma", "Urgência");
                i.putExtra("onde", "Urgência");
                i.putStringArrayListExtra("sintomas", (ArrayList<String>) sintomas);
                startActivity(i);
            }
        });

        btn_corona_virus = findViewById(R.id.btn_corona_virus);
        btn_corona_virus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), corona_virus.class);
                startActivity(i);
            }
        });

        btn_servicos = findViewById(R.id.btn_servico);
        btn_servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), servicos.class);
                startActivity(i);
            }
        });

        btn_perfil = findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), perfil.class);0o
//                startActivity(i);

                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(telainicial.this);
                alertDialogBuilder.setTitle("Direciona SUS");
                alertDialogBuilder
                        .setMessage("Estará disponível em breve.")
                        .setCancelable(false)
                        .setPositiveButton("Fechar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btn_sobre_o_SUS = findViewById(R.id.btn_sobre_o_SUS);
        btn_sobre_o_SUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), perfil.class);0o
//                startActivity(i);
                String sobre_o_SUS = getString(R.string.sobre);

                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(telainicial.this);
                alertDialogBuilder.setTitle("Conheça o SUS");
                alertDialogBuilder
                        .setMessage(sobre_o_SUS)
                        .setCancelable(false)
                        .setPositiveButton("Fechar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        getSupportActionBar().setTitle("Direciona SUS");

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(telainicial.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);
        mDatabaseReference = LibraryClass.getFirebase();
        mDatabaseReference.getRef();


        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabaseInstance.getReference("users");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        userId = firebaseAuth.getCurrentUser().getUid();

        mDatabaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
    //            Usuario us = dataSnapshot.getValue(Usuario.class);
  //              textBV = (TextView) findViewById(R.id.textBV);
//                textBV.setText("Bem vindo(a): " + us.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acao_sair) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
