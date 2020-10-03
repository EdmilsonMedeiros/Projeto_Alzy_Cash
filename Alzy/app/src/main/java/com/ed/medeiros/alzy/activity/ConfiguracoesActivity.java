package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;

public class ConfiguracoesActivity extends AppCompatActivity {

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

}