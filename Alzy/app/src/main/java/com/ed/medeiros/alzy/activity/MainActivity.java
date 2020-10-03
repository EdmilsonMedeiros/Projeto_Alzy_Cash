package com.ed.medeiros.alzy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ed.medeiros.alzy.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setButtonBackVisible(false);
        addSlide(new FragmentSlide.Builder()
        .background(R.color.whiteColorAux).fragment(R.layout.intro_1).backgroundDark(R.color.colorPrimaryDarkDefault)
        .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.whiteColorAux).fragment(R.layout.intro_2).backgroundDark(R.color.colorPrimaryDarkDefault)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.whiteColorAux).fragment(R.layout.intro_3).backgroundDark(R.color.colorPrimaryDarkDefault).canGoForward(false)
                .build());
    }

    public void irTelaCadastro(View v){
        startActivity(new Intent(this, CadastroActivity.class));
        finish();
    }
    public void irTelaLogin(View v){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}