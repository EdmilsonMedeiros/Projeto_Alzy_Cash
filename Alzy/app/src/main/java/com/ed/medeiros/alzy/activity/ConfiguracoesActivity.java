package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;
import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracoesActivity extends AppCompatActivity {
    FirebaseAuth    autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
    }

    public void sairConfiguracoes(View view){
        finish();
    }
    public void irTelaSobre(View view){
        startActivity(new Intent(this, SobreActivity.class));
    }
    public void irTelaAlterarSenha(View view){
        startActivity(new Intent(this, AlteraSenhaActivity.class));
    }
    public void irTelaAlterarEmail(View view){
        startActivity(new Intent(this, AlteraEmailActivity.class));
    }
    public void irTelaAlterarNome(View view){
        startActivity(new Intent(this, AlteraNomeActivity.class));
    }
    public void irTelaReport(View view){
        startActivity(new Intent(this, ReportActivity.class));
    }
    public void irTelaAdcionarCategoria(View view){
        startActivity(new Intent(this, AdicionaCategoriaActivity.class));
    }
    public void logOut(View view){
        if (autenticacao != null){
            autenticacao.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}