package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.ed.medeiros.alzy.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Exibe a tela em FullScreen:
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Redireciona após 2s para PrincipalActivity:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (autenticacao.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 2000);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}