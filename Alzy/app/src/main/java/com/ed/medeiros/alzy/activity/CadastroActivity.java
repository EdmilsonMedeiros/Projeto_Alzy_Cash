package com.ed.medeiros.alzy.activity;

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

public class CadastroActivity extends AppCompatActivity {
    private ProgressBar progressBarCircle;
    private EditText campoUsuario, campoEmail, campoSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoUsuario        = findViewById(R.id.editNomeCadastro);
        campoEmail          = findViewById(R.id.editEmailCadastro);
        campoSenha          = findViewById(R.id.editSenhaCadastro);
        progressBarCircle   = findViewById(R.id.progressBarCircularCadastro);

        progressBarCircle.setVisibility(View.GONE);
        //OBS
    }
    //--
    public void validarECadastrarUsuario(View view){
        if (isOnline()){
            String nome = campoUsuario.getText().toString();
            String email = campoEmail.getText().toString();
            String senha = campoSenha.getText().toString();
            if (nome.equals("") || email.equals("") || senha.equals("")){
                Toast.makeText(this, "Preencha todos os campos!!", Toast.LENGTH_LONG).show();
            }else {
                irTelaSplash();
                //==Cadastro aqui
            }
        }
    }
    //--
    public void irTelaLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
    public void irTelaSplash(){
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
    //--
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public void irTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }

}