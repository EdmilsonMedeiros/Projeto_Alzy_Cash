package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ed.medeiros.alzy.R;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar progressBarCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBarCircle = findViewById(R.id.progressBarCircular);
        progressBarCircle.setVisibility(View.GONE);
    }
    //--
    public void validarELogarUsuario(View view){
        if (isOnline()){
            irTelaSplash();


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
}