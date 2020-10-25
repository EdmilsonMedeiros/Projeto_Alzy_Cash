package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.medeiros.alzy.R;

public class AdicionaCategoriaActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_categoria);

        myDialog = new Dialog(this);

    }

    public void ShowDialog(View view){
        TextView textFechar;
        ImageView imageView;

        myDialog.setContentView(R.layout.popup_icone_receita);
        textFechar = (TextView) myDialog.findViewById(R.id.textFechar);
        imageView = (ImageView) myDialog.findViewById(R.id.imageView);

        textFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void sair(View view){
        finish();
    }
}