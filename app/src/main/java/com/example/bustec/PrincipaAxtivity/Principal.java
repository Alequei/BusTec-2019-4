package com.example.bustec.PrincipaAxtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bustec.Fragmets.Ruta1;
import com.example.bustec.Logeo.Login;
import com.example.bustec.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    FirebaseUser currentuser;
    FirebaseAuth  auth;
    private ImageView photo;
    private TextView nombrecorreo;
    private TextView nombre;
    private GoogleApiClient googleApiClient;
    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Variables de Gmail
        photo=(ImageView)findViewById(R.id.nav_user_photo);
        nombrecorreo=(TextView)findViewById(R.id.nav_useremail);
        nombre=(TextView)findViewById(R.id.nav_username);
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).
                enableAutoManage(this,this).
                addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        //Varialbes de Firebase
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Agrega elementos a la barra de acción si está presente
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar los clics del elemento de la barra de acción aquí. La barra de acción
        // Manejar automáticamente los clics en el botón Inicio / Arriba, durante tanto tiempo.
        // a medida que especifique una actividad principal en AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Manejar los elementos de la vista de navegación aquí..
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Principal");
            getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,new PrincipalFragment()).commit();
                // Manejar la acción de la cámara.
            } else if (id == R.id.ruta1) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 1);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.056878);
                bundle.putDouble("longitud-destino", -76.973848);
                bundle.putString("title-destino", "Puente Santa Anita");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta).commit();
            }else if (id == R.id.ruta2) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 2);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.047668);
                bundle.putDouble("longitud-destino", -77.004567);
                bundle.putString("title-destino", "El Agustino");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, ruta).commit();
            }else if (id == R.id.ruta3) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 3);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.052378);
                bundle.putDouble("longitud-destino", -77.035436);
                bundle.putString("title-destino", "Liam Centro");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta ).commit();
            }else if (id == R.id.ruta4) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 4);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.100448);
                bundle.putDouble("longitud-destino", -77.001772);
                bundle.putString("title-destino", "San borja");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta).commit();
            }else if (id == R.id.ruta5) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 5);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -11.964168);
                bundle.putDouble("longitud-destino", -77.077391);
                bundle.putString("title-destino", "Los Olivos");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta).commit();
            }else if (id == R.id.ruta6) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 6);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.130191);
                bundle.putDouble("longitud-destino", -76.987708);
                bundle.putString("title-destino", "Chaclacayo");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta).commit();
            }else if (id == R.id.ruta7) {
                getSupportActionBar().setTitle("Rutas");
                Ruta1 ruta = new Ruta1();
                Bundle bundle = new Bundle();
                bundle.putInt("id", 7);
                bundle.putDouble("latitud-origen", -12.043970);
                bundle.putDouble("longitud-origen", -76.952846);
                bundle.putString("title-origen", "Tecsup Santa Anita");
                bundle.putDouble("latitud-destino", -12.043970);
                bundle.putDouble("longitud-destino", -76.952846);
                bundle.putString("title-destino", "Tecsup Santa Anita");
                ruta.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,ruta).commit();
            }
        else if (id == R.id.nav_horarios) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,new HorariosFragment()).commit();
        } else if (id == R.id.nav_perfil) {
            getSupportActionBar().setTitle("Perfil");
                getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout,new PerfilFragment()).commit();
        }else if (id == R.id.nav_end) {
            FirebaseAuth.getInstance().signOut();
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()){
                       /* goLogInScreen();*/
                    }else {
                        Toast.makeText(getApplicationContext(),"No se pudo cerrar sesion",Toast.LENGTH_SHORT).show();

                    }
                }
            });
            Intent loginactivity=new Intent(this, Login.class);

            startActivity(loginactivity);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void updateNavHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        TextView navUsername=headerView.findViewById(R.id.nav_username);
        TextView navUserEmail=headerView.findViewById(R.id.nav_useremail);
        ImageView navUserPhoto=headerView.findViewById(R.id.nav_user_photo);
        navUserEmail.setText(currentuser.getEmail());
        navUsername.setText(currentuser.getDisplayName());
        //Ahora usaremos Glide para cargar la imagen del usuario.
        //Primero necesitamos importar la biblioteca.
        Glide.with(this).load(currentuser.getPhotoUrl()).into(navUserPhoto);

    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> sign=Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (sign.isDone()){
        GoogleSignInResult result=sign.get();
        handleSignInResult(result);
        }else {
            sign.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
    if (result.isSuccess()){
        GoogleSignInAccount account=result.getSignInAccount();
        nombre.setText(account.getDisplayName());
         nombrecorreo.setText(account.getEmail());
        Glide.with(this).load(account.getPhotoUrl()).into(photo);
    }else {
       /* goLogInScreen();*/
    }
    }
/*    private  void goLogInScreen(){
        Intent  intent=new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
