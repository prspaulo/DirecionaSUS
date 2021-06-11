package br.com.example.aplicativosus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CadastroActivity extends CommonActivity implements DatabaseReference.CompletionListener, View.OnClickListener {

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Usuario usuario;
    private EditText nome;
    private FloatingActionButton FabCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cadastro");

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null) {
                    sendVerificationEmail();
                }
//                usuario.setId(firebaseUser.getUid());
//                usuario.saveDB(CadastroActivity.this);
            }
        };

        inicializarViews();

        FabCadastrar = (FloatingActionButton) findViewById(R.id.fab_enviarDados_Cadastro);
        FabCadastrar.setOnClickListener(this);
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                        }
                    }
                });
    }

    protected void inicializarViews() {
        name = (EditText) findViewById(R.id.edt_Nome_Cadastro);
        email = (EditText) findViewById(R.id.edt_Email_Cadastro);
        password = (EditText) findViewById(R.id.edt_Senha_Cadastro);
        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
    }

    protected void inicializarUsuario() {
        usuario = new Usuario();
        usuario.setName(name.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setPassword(password.getText().toString());
    }

    @Override
    public void onClick(View v) {
        inicializarUsuario();

        String NOME = name.getText().toString();
        String EMAIL = email.getText().toString();
        String SENHA = password.getText().toString();

        boolean ok = true;

        if (NOME.isEmpty()) {
            name.setError("O campo nome não pode ser vazio");
            ok = false;
        }

        if (EMAIL.isEmpty()) {
            email.setError("O campo email não pode ser vazio");
            ok = false;
        }

        if (SENHA.isEmpty()) {
            password.setError("Por favor, informe uma senha!");
            ok = false;
        }

        if (ok) {
            FabCadastrar.setEnabled(false);
            progressBar.setFocusable(true);
            salvarUsuario();

        } else {
            closeProgressBar();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void salvarUsuario() {
        mAuth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder alertDialogBuilder;
                    alertDialogBuilder = new AlertDialog.Builder(CadastroActivity.this);
                    alertDialogBuilder.setTitle("Direciona SUS");
                    alertDialogBuilder
                            .setMessage("Sua conta foi criada. Para efetuar logon verifique sua conta pelo e-mail enviado para " + usuario.getEmail())
                            .setCancelable(false)
                            .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                    mAuth.signOut();
                                    startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                                    finish();
                                    //FirebaseAuth.getInstance().signOut();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else{
                    closeProgressBar();
                    showSnackbar("Erro ao efetuar cadastro!!!");
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //showSnackbar(e.getMessage());
                if(e.getMessage().endsWith("]")){
                    showSnackbar("Senha deve possuir no mínimo 6 caracteres!!!");
                }
                else if(e.getMessage().endsWith("t.")){
                    showSnackbar("O email informado já está cadastrado!!!");
                }
                FabCadastrar.setEnabled(true);
            }
        });
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        //showSnackbar("Conta criada com sucesso!");
    }

    @Override
    void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}