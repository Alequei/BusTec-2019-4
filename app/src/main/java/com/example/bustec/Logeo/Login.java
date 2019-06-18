package com.example.bustec.Logeo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bustec.PrincipaAxtivity.Principal;
import com.example.bustec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
   private ImageButton btRegister;
    private Button btnIngreso;
    private  TextView tvLogin;

    //Campos para LOS Editex
    private EditText useremail,userparword;
    private Button ingresologin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Intent MenuActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Determinamso los campos con los variables
        useremail=findViewById(R.id.ingresouser);
        userparword=findViewById(R.id.ingresopass);
        ingresologin=findViewById(R.id.ingresologeo);
        progressBar=findViewById(R.id.progresslogin);
        auth=FirebaseAuth.getInstance();
        MenuActivity=new Intent(this, Principal.class);//Llamas al campo principal
        //Botones para dinamico de pagina
        btRegister  = findViewById(R.id.btRegister);
        tvLogin     = findViewById(R.id.tvLogin);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }

        });
        btnIngreso= findViewById(R.id.ingresologeo);
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Principal.class);
                startActivity(intent);
            }
        });
        //Efecto de los campos de login y processbar
        progressBar.setVisibility(View.INVISIBLE);
        ingresologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ingresologin.setVisibility(View.INVISIBLE);
                final String email=useremail.getText().toString();
                final String clave=userparword.getText().toString();
                if (email.isEmpty()||clave.isEmpty()){
                    showMessage("Verificar los campos");
                    ingresologin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }else {
                    logearce(email,clave);
                }
            }
        });
    }

    private void logearce(String email, String clave) {

        auth.signInWithEmailAndPassword(email, clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    ingresologin.setVisibility(View.VISIBLE);
                    updateUI();
                }else {

                    showMessage(task.getException().getMessage());
                    ingresologin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void updateUI() {
        startActivity(MenuActivity);
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario=auth.getCurrentUser();
        if(usuario!=null){
            //el usuario ya está conectado, así que tenemos que redirigirlo a la página de inicio
            updateUI();
        }
    }
}
