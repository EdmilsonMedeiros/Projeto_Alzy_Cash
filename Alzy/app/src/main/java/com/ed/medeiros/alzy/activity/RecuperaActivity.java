package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.ed.medeiros.alzy.R;

public class RecuperaActivity extends AppCompatActivity {
    private ProgressBar progressBarCircleRecupera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera);
        progressBarCircleRecupera = findViewById(R.id.progressBarCircularRecupera);

        progressBarCircleRecupera.setVisibility(View.GONE);
    }
    public void irTelaLogin(View v){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}