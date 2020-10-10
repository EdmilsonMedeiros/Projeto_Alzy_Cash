package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ed.medeiros.alzy.R;
import com.google.firebase.auth.FirebaseAuth;

public class AlteraNomeActivity extends AppCompatActivity {
    private TextView textViewEmail;
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_nome);
        textViewEmail = findViewById(R.id.textViewEmail);
    }
    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        textViewEmail.setText(autenticacao.getCurrentUser().getEmail());
        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}