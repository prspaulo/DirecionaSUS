package br.com.example.aplicativosus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class resetarSenha extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnResetPassword;
    private Button btnBack;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetar_senha);

        edtEmail = (EditText) findViewById(R.id.email_reset);
        btnResetPassword = (Button) findViewById(R.id.btn_resetar_senha);
        btnBack = (Button) findViewById(R.id.btn_voltar);

        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Digite seu email", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(resetarSenha.this, "Verifique seu e-mail para redefinir sua senha!", Toast.LENGTH_LONG).show();
                            AlertDialog.Builder alertDialogBuilder;
                            alertDialogBuilder = new AlertDialog.Builder(resetarSenha.this);
                            alertDialogBuilder.setTitle("Direciona SUS");
                            alertDialogBuilder
                                    .setMessage("Verifique seu e-mail para redefinir sua senha!")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                            mAuth.signOut();
                                            startActivity(new Intent(resetarSenha.this, LoginActivity.class));
                                            finish();
                                            //FirebaseAuth.getInstance().signOut();
                                        }
                                    });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        } else {
                            Toast.makeText(resetarSenha.this, "Email digitado inválido!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
