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

    private ImageView ic_extra,
    ic_bonificacao,
            ic_nota,
    ic_poupanca,
            ic_cifrao,
    ic_compras,
            ic_casa,
    ic_geral,
            ic_carro,
    ic_esporte,
            ic_presente,
    ic_roupa,
            ic_entretenimento,
    ic_ferias,
            ic_saude,
    ic_contas,
            ic_lanche,
    ic_comida,
            ic_viagem,
    ic_transporte,
            ic_livro;

    private TextView text_icone;
    private ImageView editImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_categoria);

        myDialog            = new Dialog(this);
        editImagem          = findViewById(R.id.editImagem);
        text_icone          = findViewById(R.id.text_icone);



        //----------------------------------------------------------

    }

    public void ShowDialog(View view){
        TextView textFechar;


        myDialog.setContentView(R.layout.popup_icone_receita);

        textFechar          = (TextView) myDialog.findViewById(R.id.textFechar);

        ic_extra            = myDialog.findViewById(R.id.ic_extra);
        ic_bonificacao      = myDialog.findViewById(R.id.ic_bonificacao);
        ic_nota             = myDialog.findViewById(R.id.ic_nota);
        ic_poupanca         = myDialog.findViewById(R.id.ic_poupanca);
        ic_cifrao           = myDialog.findViewById(R.id.ic_cifrao);
        ic_compras          = myDialog.findViewById(R.id.ic_compras);
        ic_casa             = myDialog.findViewById(R.id.ic_casa);
        ic_geral            = myDialog.findViewById(R.id.ic_geral);
        ic_carro            = myDialog.findViewById(R.id.ic_carro);
        ic_esporte          = myDialog.findViewById(R.id.ic_esporte);
        ic_presente         = myDialog.findViewById(R.id.ic_presente);
        ic_roupa            = myDialog.findViewById(R.id.ic_roupa);
        ic_entretenimento   = myDialog.findViewById(R.id.ic_entretenimento);
        ic_ferias           = myDialog.findViewById(R.id.ic_ferias);
        ic_saude            = myDialog.findViewById(R.id.ic_saude);
        ic_contas           = myDialog.findViewById(R.id.ic_contas);
        ic_lanche           = myDialog.findViewById(R.id.ic_lanche);
        ic_comida           = myDialog.findViewById(R.id.ic_comida);
        ic_viagem           = myDialog.findViewById(R.id.ic_viagem);
        ic_transporte       = myDialog.findViewById(R.id.ic_transporte);
        ic_livro            = myDialog.findViewById(R.id.ic_livro);

        //-------------------------------------------------------------

        textFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        ic_livro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_livro");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_livro));
                myDialog.dismiss();
            }
        });

        ic_transporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_transporte");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_transporte));

                myDialog.dismiss();
            }
        });

        ic_viagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_viagem");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_viagem));
                myDialog.dismiss();
            }
        });

        ic_comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_comida");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_comida));
                myDialog.dismiss();
            }
        });

        ic_lanche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_lanche");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_lanche));
                myDialog.dismiss();
            }
        });

        ic_contas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_contas");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_contas));
                myDialog.dismiss();
            }
        });

        ic_saude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_saude");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_saude));
                myDialog.dismiss();
            }
        });

        ic_ferias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_ferias");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_ferias));
                myDialog.dismiss();
            }
        });

        ic_entretenimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_entretenimento");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_entretenimento));
                myDialog.dismiss();
            }
        });

        ic_roupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_roupa");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_roupa));
                myDialog.dismiss();
            }
        });

        ic_presente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_presente");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_presente));
                myDialog.dismiss();
            }
        });

        ic_esporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_esporte");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_esporte));
                myDialog.dismiss();
            }
        });

        ic_carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_carro");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_carro));
                myDialog.dismiss();
            }
        });

        ic_geral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_geral");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_geral));
                myDialog.dismiss();
            }
        });

        ic_casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_casa");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_casa));
                myDialog.dismiss();
            }
        });

        ic_compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_compras");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_compras));
                myDialog.dismiss();
            }
        });

        ic_cifrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_cifrao");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_cifrao));
                myDialog.dismiss();
            }
        });

        ic_poupanca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_poupanca");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_poupanca));
                myDialog.dismiss();
            }
        });

        ic_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_nota");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_nota));
                myDialog.dismiss();
            }
        });

        ic_bonificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_bonificacao");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_bonificacao));
                myDialog.dismiss();
            }
        });

        ic_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_icone.setText("ic_extra");
                editImagem.setImageDrawable(getDrawable(R.mipmap.ic_extra));
                myDialog.dismiss();
            }
        });
    }

    public void sair(View view){
        finish();
    }
}