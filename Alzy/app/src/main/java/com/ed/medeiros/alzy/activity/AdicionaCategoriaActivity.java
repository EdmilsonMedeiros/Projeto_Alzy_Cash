package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;

public class AdicionaCategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_categoria);
    }

    public void sair(View view){
        finish();
    }
}