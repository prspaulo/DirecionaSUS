package br.com.example.aplicativosus;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfil extends AppCompatActivity {
    private static final String TAG = perfil.class.getSimpleName();

    private TextView textEmail;
    private TextView textNome;
    private TextView textLocal;
    private TextView textRegiaoSintoma;
    private TextView textSintoma;
    private DatabaseReference mDatabaseReference, mReferenceSintomas;
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private String userId, email;

//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializaComponentes();

        getSupportActionBar().setTitle("Autenticação Firebase");

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(perfil.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabaseInstance.getReference("users");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        userId = firebaseAuth.getCurrentUser().getUid();
        email = firebaseAuth.getCurrentUser().getEmail();

        mDatabaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario us = dataSnapshot.getValue(Usuario.class);

                if(us == null){
                    Log.e(TAG, "Usuário não existe" + userId);
                    return;
                }

                //textNome.setText("Nome: " + us.getName());
                //textEmail.setText("Email: " + us.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mReferenceSintomas = mFirebaseDatabaseInstance.getReference("sintomas");
        mReferenceSintomas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Sintomas_cad sint = dataSnapshot.getValue(Sintomas_cad.class);
                Iterable<DataSnapshot> respostaChildren = dataSnapshot.getChildren();

                for (DataSnapshot resposta : respostaChildren) {
                    if(resposta.child("_1email_user").getValue().equals(email)){
                        //textLocal.setText("Local atendimento: " + resposta.child("_2servico_atendimento").getValue().toString());
                        //textRegiaoSintoma.setText("Região do sintoma: " + resposta.child("_3regiao_sintoma").getValue().toString());
                        textEmail.setText("Sintoma: " + resposta.child("_4onde").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void inicializaComponentes(){
        textEmail = (TextView) findViewById(R.id.idnome);
        //textNome = (TextView) findViewById(R.id.textPerfilnome);
        //textLocal = (TextView) findViewById(R.id.textlocalatendimento);
//        textRegiaoSintoma = (TextView) findViewById(R.id.textregiaosintoma);
//        textSintoma = (TextView) findViewById(R.id.textsintoma);
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
