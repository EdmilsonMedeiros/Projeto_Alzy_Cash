package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;
import com.google.firebase.auth.FirebaseAuth;

public class AlteraEmailActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_email);
    }
    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}