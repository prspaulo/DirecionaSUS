package br.com.example.aplicativosus;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.views.MapController;
import org.osmdroid.config.Configuration;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class mapa extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private CollectionReference db;
    private FirebaseFirestore db_reference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    final Context context = this;
    private Polyline roadOverlay;
    private MapView osm;
    private MapController mc;
    private double lat = 0;
    private double lon = 0;
    //private GeoPoint center;
    private Marker marker, marker_origem, marker_destino, patual;
    //////////////////////////////////////////////////////////////////////////////////////////
    private Location location;
    private TextView locationTv;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 1000, FASTEST_INTERVAL = 1000; // = 1 second
    // lists for permissions
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    private static final String TAG = "";

    //////////////////////////////////////////////////////////////////////
    double distancia_Origem_Chegada = 5000;
    Road rota_inicial;
    GeoPoint ponto1, ponto2;
    String nrotaautomatica;
    ////////////////////////////////////////////////////////////////////
    String regiao_sintoma;
    String onde_incomoda;
    String sintoma;
    String tipo_servico;
    String servico;
    String regional_servico;
    ArrayList<String> sintomas = new ArrayList<String>();

    long hora_acholhimento = 0, tempo_espera = 0, MILLIS_IN_SEC = 1000L;
    int SECS_IN_MIN = 60;
    String classificacao;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Intent i = getIntent();
        tipo_servico = i.getStringExtra("servico");
        regiao_sintoma = i.getStringExtra("regiao_sintoma");
        onde_incomoda = i.getStringExtra("onde");
        sintomas = i.getStringArrayListExtra("sintomas");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // locationTv = findViewById(R.id.location);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        permissionsToRequest = permissionsToRequest(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        }

        // we build google api client
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        osm = (MapView) findViewById(R.id.mapaId);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);


        mc = (MapController) osm.getController();
        mc.setZoom(12);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        getSupportActionBar().setTitle("Mapa");

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {
                    Intent intent = new Intent(mapa.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        db_reference = FirebaseFirestore.getInstance();
        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        //Log.d(TAG, "TIPO SERVIÇO: " + tipo_servico);
        if (tipo_servico.equals("1")) {
            //mDatabaseReference = mFirebaseDatabaseInstance.getReference("postos_de_saude");
            db = db_reference.collection("postos_de_saude");
            servico = "posto";
        } else if (tipo_servico.equals("2")) {
            //mDatabaseReference = mFirebaseDatabaseInstance.getReference("upas");
            db = db_reference.collection("upas");
            servico = "upa";
        } else if (tipo_servico.equals("3")) {
            //mDatabaseReference = mFirebaseDatabaseInstance.getReference("hospitais");
            db = db_reference.collection("hospitais");
            servico = "hospital";
        } else {
            //mDatabaseReference = mFirebaseDatabaseInstance.getReference("serv_urgencia");
            db = db_reference.collection("serv_urgencia");
            servico = "urgencia";
        }

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);

        osm.setMapListener(new MapListener() {

            @Override
            public boolean onScroll(ScrollEvent arg0) {
                Log.i("Script", "onScroll()");
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent arg0) {
                Log.i("Script", "onZoom()");
                return false;
            }

        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //HABILITA O DOWNLOAD DA IMAGENS PARA GERAR O MAPA
        osm.invalidate();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////ADICIONA MARCADOR////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addMarker(final GeoPoint center) {
        marker = new Marker(osm);
        marker.setPosition(center);
        marker.setTitle("Meu local");
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        osm.getOverlays().add(marker);
        osm.invalidate();
    }

    public void recriaMarker(GeoPoint origem, GeoPoint destino, String nome_destino, Sintomas_cad sin) {
        marker_destino = new Marker(osm);
        marker_destino.setPosition(destino);
        marker_destino.setTitle(nome_destino);
        marker_destino.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker_destino.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_servicos, null));
        osm.getOverlays().add(marker_destino);

        marker_origem = new Marker(osm);
        marker_origem.setPosition(origem);
        marker_origem.setTitle("Minha localização");
        marker_origem.isInfoWindowShown();
        marker_origem.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker_origem.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_mylocation, null));
        osm.getOverlays().add(marker_origem);
        if(!servico.equals("urgencia")){
            marker_destino.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker marker, MapView mapView) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mapa.this);
                    /**************************************************************************************************************************/
                    View customLayout = LayoutInflater.from(mapa.this).inflate(R.layout.customlayout, null);

                    final CheckBox cbxAcolhimento = (CheckBox) customLayout.findViewById(R.id.cbx_acolhimento);
                    final CheckBox cbxAtendimento = (CheckBox) customLayout.findViewById(R.id.cbx_atendido);

                    final RadioButton rdAzul = (RadioButton) customLayout.findViewById(R.id.rdAzul);
                    final RadioButton rdVerde = (RadioButton) customLayout.findViewById(R.id.rdVerde);
                    final RadioButton rdAmarelo = (RadioButton) customLayout.findViewById(R.id.rdAmarelo);
                    final RadioButton rdLaranja = (RadioButton) customLayout.findViewById(R.id.rdLaranja);
                    final RadioButton rdVermelho = (RadioButton) customLayout.findViewById(R.id.rdVermelho);

                    final String[] tmpAtendimento = new String[1];

                    rdAzul.setEnabled(false);
                    rdVerde.setEnabled(false);
                    rdAmarelo.setEnabled(false);
                    rdLaranja.setEnabled(false);
                    rdVermelho.setEnabled(false);
                    cbxAtendimento.setEnabled(false);


                    cbxAcolhimento.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(cbxAcolhimento.isChecked()){
                                rdAzul.setEnabled(true);
                                rdVerde.setEnabled(true);
                                rdAmarelo.setEnabled(true);
                                rdLaranja.setEnabled(true);
                                rdVermelho.setEnabled(true);
                                hora_acholhimento = System.currentTimeMillis();
                            }
                        }
                    });

                    cbxAtendimento.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (cbxAtendimento.isChecked()) {
                                rdAzul.setEnabled(false);
                                rdVerde.setEnabled(false);
                                rdAmarelo.setEnabled(false);
                                rdLaranja.setEnabled(false);
                                rdVermelho.setEnabled(false);
                                cbxAtendimento.setEnabled(false);
                                cbxAcolhimento.setEnabled(false);
                                tempo_espera = (System.currentTimeMillis() - hora_acholhimento);
                                //tmpAtendimento[0] = String.format( "%02d:%02d", tempo_espera / 3600000, ( tempo_espera / 60000 ) % 60 );
                                long segundos = tempo_espera / 1000;
                                long minutos = segundos / 60;
                                segundos = segundos % 60;
                                long horas = minutos / 60;
                                minutos = minutos % 60;
                                tmpAtendimento[0] = String.format ("%02d:%02d:%02d", horas, minutos, segundos);
                            }
                        }
                    });

                    rdAzul.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rdAzul.isChecked()) {
                                rdVerde.setChecked(false);
                                rdAmarelo.setChecked(false);
                                rdLaranja.setChecked(false);
                                rdVermelho.setChecked(false);
                                classificacao = Objects.requireNonNull(rdAzul).getText().toString();
                                cbxAtendimento.setEnabled(true);
                                cbxAcolhimento.setEnabled(false);
                            }
                        }
                    });

                    rdVerde.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rdVerde.isChecked()) {
                                rdAzul.setChecked(false);
                                rdAmarelo.setChecked(false);
                                rdLaranja.setChecked(false);
                                rdVermelho.setChecked(false);
                                classificacao = Objects.requireNonNull(rdVerde).getText().toString();
                                cbxAtendimento.setEnabled(true);
                                cbxAcolhimento.setEnabled(false);
                            }
                        }
                    });

                    rdAmarelo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rdAmarelo.isChecked()) {
                                rdVerde.setChecked(false);
                                rdAzul.setChecked(false);
                                rdLaranja.setChecked(false);
                                rdVermelho.setChecked(false);
                                classificacao = Objects.requireNonNull(rdAmarelo).getText().toString();
                                cbxAtendimento.setEnabled(true);
                                cbxAcolhimento.setEnabled(false);
                            }
                        }
                    });

                    rdLaranja.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rdLaranja.isChecked()) {
                                rdVerde.setChecked(false);
                                rdAmarelo.setChecked(false);
                                rdAzul.setChecked(false);
                                rdVermelho.setChecked(false);
                                classificacao = Objects.requireNonNull(rdLaranja).getText().toString();
                                cbxAtendimento.setEnabled(true);
                                cbxAcolhimento.setEnabled(false);
                            }
                        }
                    });

                    rdVermelho.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (rdVermelho.isChecked()) {
                                rdVerde.setChecked(false);
                                rdAmarelo.setChecked(false);
                                rdLaranja.setChecked(false);
                                rdAzul.setChecked(false);
                                classificacao = Objects.requireNonNull(rdVermelho).getText().toString();
                                cbxAtendimento.setEnabled(true);
                                cbxAcolhimento.setEnabled(false);
                            }
                        }
                    });
    /**************************************************************************************************************************/
                            builder.setView(customLayout);
                            builder.setTitle(nome_destino);
                            builder.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Dismiss Dialog
                                    dialogInterface.dismiss();
                                    //Toast.makeText(mapa.this, classificacao + " Hora: "+ tmpAtendimento[0], Toast.LENGTH_SHORT).show();
                                    cadastroSintomas(sin, classificacao, tmpAtendimento[0]);
                                }
                            });
                            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    builder.show();
                    return true;
                }
            });
        }
        else{
            marker_destino.isInfoWindowShown();
            cadastroSintomas(sin, "Vermelho", "0");
        }
    }

    public void cadastroSintomas(Sintomas_cad sin, String classeRisco, String tempoEspera) {
//       if(tipo_servico.equals("urgencia")){
//           Map<String, Object> sintoma = new HashMap<>();
//           sintoma.put("email_user", sin.email_user);
//           sintoma.put("data_direcionamento", sin.data_direcionamento);
//           sintoma.put("tipo_servico", sin.tipo_servico);
//           sintoma.put("hora_atendimento", sin.hora_direcionamento);
//           sintoma.put("latitude_destino", sin.latitude_destino);
//           sintoma.put("latitude_origem", sin.latitude_origem);
//           sintoma.put("longitude_destino", sin.longitude_destino);
//           sintoma.put("longitude_origem", sin.longitude_origem);
//           sintoma.put("onde", sin.onde);
//           sintoma.put("regiao_sintoma", sin.regiao_sintoma);
//           sintoma.put("servico_atendimento", sin.servico_atendimento);
//           sintoma.put("sintomas", Arrays.asList(sin.sintomas.toArray()));
//       }
//       else{
           Map<String, Object> sintoma = new HashMap<>();
           sintoma.put("email_user", sin.email_user);
           sintoma.put("servico_atendimento", sin.servico_atendimento);
           sintoma.put("regiao_sintoma", sin.regiao_sintoma);
           sintoma.put("onde", sin.onde);
           sintoma.put("sintomas", Arrays.asList(sin.sintomas.toArray()));
           sintoma.put("hora_atendimento", sin.hora_direcionamento);
           sintoma.put("data_direcionamento", sin.data_direcionamento);
           sintoma.put("tipo_servico", sin.tipo_servico);
           sintoma.put("latitude_destino", sin.latitude_destino);
           sintoma.put("longitude_destino", sin.longitude_destino);
           sintoma.put("latitude_origem", sin.latitude_origem);
           sintoma.put("longitude_origem", sin.longitude_origem);
           sintoma.put("classe_risco", classeRisco);
           sintoma.put("tempo_espera", tempoEspera);
       //}

        db_reference.collection("sintomas")
                .add(sintoma)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Cadastro efetuado!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        //mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //mDatabaseReference.child("sintomas").child(String.valueOf(new Date().getTime())).setValue(sin);
    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!checkPlayServices()) {
            locationTv.setText("You need to install Google Play Services to use the App properly");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // stop location updates
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                finish();
            }

            return false;
        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        final String[] local_atendimento = new String[1];

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            GeoPoint ponto = new GeoPoint(location.getLatitude(), location.getLongitude());
            mc.animateTo(ponto);

            patual = new Marker(osm);
            patual.setPosition(ponto);
            patual.setTitle("Minha localização");
            patual.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            patual.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_mylocation, null));
            osm.getOverlays().add(patual);

            if (db != null) {
                final String[] nome = {""};

                db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nome[0] = document.get("nome").toString();
                                double latitude = document.getGeoPoint("localizacao").getLatitude();
                                double longitude = document.getGeoPoint("localizacao").getLongitude();

                                GeoPoint ponto = new GeoPoint(latitude, longitude);

                                /////////////////////////////////////////////////////////////////////////////////////
                                final ArrayList<GeoPoint> rotaautomatica = new ArrayList<GeoPoint>();
                                rotaautomatica.add(new GeoPoint(lat, lon));
                                rotaautomatica.add(new GeoPoint(ponto));


                                new Thread() {
                                    public void run() {
                                        RoadManager roads = new OSRMRoadManager(mapa.this);
                                        //Log.d(TAG, " mLength ROTA: " + roads.getRoad(rotaautomatica).mLength +"\nNome: "+ document.get("nome").toString() + "\nValor inicial distancia ordem chegada: " + distancia_Origem_Chegada);

                                        if (roads.getRoad(rotaautomatica).mLength < distancia_Origem_Chegada) {
                                            //distancia_Origem_Chegada = 0;
                                            rota_inicial = null;
                                            local_atendimento[0] = null;
                                            distancia_Origem_Chegada = roads.getRoad(rotaautomatica).mLength;
                                            rota_inicial = roads.getRoad(rotaautomatica);
                                            ponto1 = rotaautomatica.get(0);
                                            ponto2 = rotaautomatica.get(1);
                                            local_atendimento[0] = document.get("nome").toString();
                                            //Log.d(TAG, "ROTA: " + roads.getRoad());
                                            //Log.d(TAG, "Ponto1: " + rotaautomatica.get(0) + " \nPonto2: " + rotaautomatica.get(1) + "\nLocal atendimento" + nome[0]);
                                            //Log.d(TAG, " mLength ROTA: " + roads.getRoad(rotaautomatica).mLength +"\nNome: "+ document.get("nome").toString() + "\nValor inicial distancia ordem chegada: " + distancia_Origem_Chegada);
                                        }
                                    }
                                }.start();

                                /////////////////////////////////////////////////////////////////////////////////////////////
                                Road rota = null;

                                marker = new Marker(osm);
                                marker.setPosition(ponto);

                                ///////////////////////////////////////////////////////////////////////////////////////////////
                                final ArrayList<GeoPoint> pontos = new ArrayList<GeoPoint>();
                                pontos.add(new GeoPoint(lat, lon));
                                pontos.add(new GeoPoint(marker.getPosition().getLatitude(), marker.getPosition().getLongitude()));

                                RoadManager roadManager = new OSRMRoadManager(mapa.this);
                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);
                                rota = roadManager.getRoad(pontos);

                                marker.setTitle(nome[0] + "\n Distância e tempo de chegada \n" + rota.getLengthDurationText(mapa.this, rota.mLength, rota.mDuration));
                                marker.setSnippet(nome[0]);
                                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                                marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.marker_servicos, null));
                                osm.getOverlays().add(marker);
                                //-19.880477, -43.984939

                                ////////MARCADOR////////////////////////////////////////////////////////////////////
                                Road finalRota = rota;
                                marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                    @Override
                                    public boolean onMarkerClick(final Marker marker, MapView mapView) {
                                        AlertDialog.Builder alertDialogBuilder;
                                        alertDialogBuilder = new AlertDialog.Builder(mapa.this);
                                        alertDialogBuilder.setTitle("Direciona SUS");
                                        alertDialogBuilder
                                                .setMessage("Ir para " + marker.getTitle() + "?")
                                                .setCancelable(false)
                                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH");
                                                        Date data = new Date();
                                                        Calendar cal = Calendar.getInstance();
                                                        cal.setTime(data);
                                                        Date data_atual = cal.getTime();
                                                        String data_completa = dateFormat.format(data_atual);
                                                        String hora_atual = dateFormat_hora.format(data_atual);
                                                        int hora = Integer.parseInt(hora_atual);

                                                        //Log.d(TAG, "Email: " +firebaseAuth.getCurrentUser().getEmail() +" "+marker.getSnippet()+" Local: " + marker.getSnippet()
                                                        //+ " Região Sintoma: " + regiao_sintoma + " Onde incomoda: " + onde_incomoda + "Sintomas"+ sintomas +" Hora: " + hora + " data completa: " + data_completa + " serviço: " + servico + "origem lat " + lat + "origem lon " + lon);

                                                        Sintomas_cad sint = new Sintomas_cad(firebaseAuth.getCurrentUser().getEmail(), marker.getSnippet(), regiao_sintoma, onde_incomoda, sintomas, hora, data_completa, servico, marker.getPosition().getLatitude(), marker.getPosition().getLongitude(), lat, lon);
                                                        //cadastroSintomas(sint);
                                                        osm.getOverlayManager().overlays().clear();

//                                            final ArrayList<GeoPoint> pontos = new ArrayList<GeoPoint>();
//                                            pontos.add(new GeoPoint(lat, lon));
//                                            pontos.add(new GeoPoint(marker.getPosition().getLatitude(), marker.getPosition().getLongitude()));
//
//                                            RoadManager roadManager = new OSRMRoadManager(mapa.this);
//                                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                                            StrictMode.setThreadPolicy(policy);
//                                            Road road = roadManager.getRoad(pontos);

                                                        new Thread() {
                                                            public void run() {
                                                                osm.getOverlayManager().overlays().clear();
                                                                roadOverlay = RoadManager.buildRoadOverlay(finalRota);
                                                                roadOverlay.setColor(context.getResources().getColor(R.color.colorAccent));
                                                                roadOverlay.setGeodesic(true);
                                                                osm.getOverlays().add(roadOverlay);
                                                                recriaMarker(pontos.get(0), pontos.get(1), marker.getTitle(), sint);
                                                                //recriaMarker(ponto1, ponto2, local_atendimento[0], sint);
                                                            }
                                                        }.start();

                                                        mapView.invalidate();
                                                        db = null;
                                                    }
                                                })
                                                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                        return true;
                                    }
                                });
                            }
//                            Log.d(TAG, "ROTA INICIAL: " + rota_inicial);
                        /******************************************************************************************************************************************/
                            if (rota_inicial != null) {//FAZER AQUI A ROTA DO SERVIÇO DE URGÊNCIA
//                               Log.d(TAG, "EXISTE UMA ROTA INICIAL");
//                                if (tipo_servico.equals("1") || tipo_servico.equals("2") || tipo_servico.equals("3")) {
//                                    Log.d(TAG, "NÃO É URGÊNCIA");
                                    AlertDialog.Builder alertDialogBuilder;
                                    alertDialogBuilder = new AlertDialog.Builder(mapa.this);
                                    alertDialogBuilder.setTitle("Direciona SUS");
                                    alertDialogBuilder
                                            .setMessage("Você foi direcionado!")
                                            .setCancelable(false)
                                            .setPositiveButton("Ir", new DialogInterface.OnClickListener() {

                                                public void onClick(DialogInterface dialog, int id) {
                                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH");
                                                    Date data = new Date();
                                                    Calendar cal = Calendar.getInstance();
                                                    cal.setTime(data);
                                                    Date data_atual = cal.getTime();
                                                    String data_completa = dateFormat.format(data_atual);
                                                    String hora_atual = dateFormat_hora.format(data_atual);
                                                    int hora = Integer.parseInt(hora_atual);

                                                    Sintomas_cad sint = new Sintomas_cad(firebaseAuth.getCurrentUser().getEmail(), local_atendimento[0], regiao_sintoma, onde_incomoda, sintomas, hora, data_completa, servico, ponto2.getLatitude(), ponto2.getLongitude(), lat, lon);
                                                    //cadastroSintomas(sint);

                                                    //Log.d(TAG, "Email: " +firebaseAuth.getCurrentUser().getEmail() +" Local atendimento: "+local_atendimento[0]
                                                      //      + " Região Sintoma: " + regiao_sintoma + " Onde incomoda: " + onde_incomoda + " Sintomas"+ sintomas +" Hora: " + hora + " data completa: " + data_completa + " serviço: " + servico + " servico lat " + ponto2.getLatitude() + " servico lon " + marker.getPosition().getLongitude());

                                                    osm.getOverlayManager().overlays().clear();
                                                    roadOverlay = RoadManager.buildRoadOverlay(rota_inicial);
                                                    roadOverlay.setColor(context.getResources().getColor(R.color.colorAccent));
                                                    roadOverlay.setGeodesic(true);
                                                    osm.getOverlays().add(roadOverlay);
//                                                    double distancia = rota_inicial.mDuration;
//                                                    double tempo_chegada = rota_inicial.mDuration;
                                                    recriaMarker(ponto1, ponto2, local_atendimento[0], sint);
                                                    osm.invalidate();
                                                    db = null;
                                                }

                                            })
                                            .setNegativeButton("Escolher outro local", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
//                                }
//                                else {
//                                    Log.d(TAG, "É URGÊNCIA");
//                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                                    SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH");
//                                    Date data = new Date();
//                                    Calendar cal = Calendar.getInstance();
//                                    cal.setTime(data);
//                                    Date data_atual = cal.getTime();
//                                    String data_completa = dateFormat.format(data_atual);
//                                    String hora_atual = dateFormat_hora.format(data_atual);
//                                    int hora = Integer.parseInt(hora_atual);
//
//                                    //Log.d(TAG, "Email: " +firebaseAuth.getCurrentUser().getEmail() +" Local atendimento: "+local_atendimento[0]
//                                      //    + " Região Sintoma: " + regiao_sintoma + " Onde incomoda: " + onde_incomoda + " Sintomas"+ sintomas +" Hora: " + hora + " data completa: " + data_completa + " serviço: " + servico + " servico lat " + ponto2.getLatitude() + " servico lon " + marker.getPosition().getLongitude());
//
//                                    Sintomas_cad sint = new Sintomas_cad(firebaseAuth.getCurrentUser().getEmail(), local_atendimento[0], regiao_sintoma, onde_incomoda, sintomas, hora, data_completa, servico, ponto2.getLatitude(), ponto2.getLongitude(), lat, lon);
//                                    cadastroSintomas(sint);
//
//                                            //osm.getOverlayManager().overlays().clear();
//                                            roadOverlay = RoadManager.buildRoadOverlay(rota_inicial);
//                                            roadOverlay.setColor(context.getResources().getColor(R.color.colorAccent));
//                                            roadOverlay.setGeodesic(true);
//                                            osm.getOverlays().add(roadOverlay);
//                                            //osm.invalidate();
//
//                                    recriaMarker(ponto1, ponto2, "TESTE");
//                                    db = null;
//                                }
                            }
                        }
                    }
                });
                osm.invalidate();
                ///////////////////////////////////////////////////////////////////////////////////////////
            }
        } else {
            startLocationUpdates();
            AlertDialog.Builder alertDialogBuilder;
            alertDialogBuilder = new AlertDialog.Builder(mapa.this);
            alertDialogBuilder.setTitle("Direciona SUS");
            alertDialogBuilder
                    .setMessage("Localização indisponível, verifique sua conexão!")
                    .setCancelable(false)
                    .setPositiveButton("Atualizar GPS", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (ActivityCompat.checkSelfPermission(mapa.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(mapa.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            ) {
                                return;
                            }
                            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                            recreate();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            osm.invalidate();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "É necessário habilitar as permissões de local e dados do aplicativo!", Toast.LENGTH_SHORT).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            //locationTv.setText("Latitude : " + location.getLatitude() + "Longitude : " + location.getLongitude());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perm : permissionsToRequest) {
                    if (!hasPermission(perm)) {
                        permissionsRejected.add(perm);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            new AlertDialog.Builder(mapa.this).
                                    setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.
                                                        toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    }).setNegativeButton("Cancel", null).create().show();

                            return;
                        }
                    }
                } else {
                    if (googleApiClient != null) {
                        googleApiClient.connect();
                    }
                }

                break;
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
