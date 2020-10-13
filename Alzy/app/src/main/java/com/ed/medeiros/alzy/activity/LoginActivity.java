package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth    autenticacao = FirebaseAuth.getInstance();
    private ProgressBar     progressBarCircle;
    private EditText        editEmail, editSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmailLogin);
        editSenha = findViewById(R.id.editSenhaLogin);

        progressBarCircle = findViewById(R.id.progressBarCircular);
        progressBarCircle.setVisibility(View.GONE);
    }
    //--
    public void validarELogarUsuario(View view){
        if (isOnline()){
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            if (email.equals("") || senha.equals("")){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            }else{
                progressBarCircle.setVisibility(View.VISIBLE);
                if (autenticacao.getCurrentUser() == null){
                    autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                                finish();
                            }else {
                                progressBarCircle.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Erro!! Reveja os dados", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    progressBarCircle.setVisibility(View.GONE);
                    Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
                }
            }

        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            //Configura o alertDialog:
            alertDialog.setTitle("Erro!");
            alertDialog.setMessage("Vocês está sem acesso a internet. Verifique sua conexão.");
            alertDialog.setIcon(R.drawable.ic_baseline_warning_24);

            alertDialog.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
    }
    //--
    public void irTelaCadastro(View view){
        startActivity(new Intent(this, CadastroActivity.class));
        finish();
    }
    public void irTelaRecuperar(View view){
        startActivity(new Intent(this, RecuperaActivity.class));
        finish();
    }
    public void irTelaSplash(){
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
    public void irTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
    //--
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (autenticacao.getCurrentUser() != null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}