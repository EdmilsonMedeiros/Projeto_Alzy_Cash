package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.ed.medeiros.alzy.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategorias extends BaseAdapter {

    List<Categorias> lst;
    Context context;

    public AdapterCategorias(List<Categorias> lst, Context context){
        this.lst = lst;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textViewNome;
        ImageView imageViewCategoria;
        TextView textViewIcone;

        Categorias c = lst.get(position);

        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_categorias_adapter, null);

        textViewNome = convertView.findViewById(R.id.textListItem);
        imageViewCategoria = convertView.findViewById(R.id.imageViewListCategorias);
        textViewIcone = convertView.findViewById(R.id.textViewIcone);

        textViewIcone.setText(c.imagem);
        textViewNome.setText(c.getNome());


        //----------------------------------]


        //imageViewCategoria.setImageResource(R.mipmap.ic_contas);

        String imagens [] = {"ic_nota", "ic_extra", "ic_bonificacao", "ic_poupanca", "ic_cifrao",
                "ic_compras", "ic_casa", "ic_geral", "ic_carro", "ic_esporte", "ic_presente", "ic_roupa", "ic_entretenimento", "ic_ferias",
                "ic_saude", "ic_contas", "ic_lanche", "ic_comida", "ic_viagem", "ic_transporte", "ic_livro"};

        //

        List<String> lstImagens = new ArrayList<>();
        lstImagens.add(c.getImagem());


        int a = 0;
        while (a < lstImagens.size()){

            if ((lstImagens.toArray()[a]).equals("ic_nota")){
                imageViewCategoria.setImageResource(R.mipmap.ic_nota);
            }
            if ((lstImagens.toArray()[a]).equals("ic_bonificacao")){
                imageViewCategoria.setImageResource(R.mipmap.ic_bonificacao);
            }
            if ((lstImagens.toArray()[a]).equals("ic_poupanca")){
                imageViewCategoria.setImageResource(R.mipmap.ic_poupanca);
            }
            if ((lstImagens.toArray()[a]).equals("ic_cifrao")){
                imageViewCategoria.setImageResource(R.mipmap.ic_cifrao);
            }

            if ((lstImagens.toArray()[a]).equals("ic_compras")){
                imageViewCategoria.setImageResource(R.mipmap.ic_compras);
            }

            if ((lstImagens.toArray()[a]).equals("ic_casa")){
                imageViewCategoria.setImageResource(R.mipmap.ic_casa);
            }

            if ((lstImagens.toArray()[a]).equals("ic_geral")){
                imageViewCategoria.setImageResource(R.mipmap.ic_geral);
            }

            if ((lstImagens.toArray()[a]).equals("ic_carro")){
                imageViewCategoria.setImageResource(R.mipmap.ic_carro);
            }

            if ((lstImagens.toArray()[a]).equals("ic_esporte")){
                imageViewCategoria.setImageResource(R.mipmap.ic_esporte);
            }

            if ((lstImagens.toArray()[a]).equals("ic_presente")){
                imageViewCategoria.setImageResource(R.mipmap.ic_presente);
            }

            if ((lstImagens.toArray()[a]).equals("ic_roupa")){
                imageViewCategoria.setImageResource(R.mipmap.ic_roupa);
            }

            if ((lstImagens.toArray()[a]).equals("ic_entretenimento")){
                imageViewCategoria.setImageResource(R.mipmap.ic_entretenimento);
            }
            if ((lstImagens.toArray()[a]).equals("ic_ferias")){
                imageViewCategoria.setImageResource(R.mipmap.ic_ferias);
            }
            if ((lstImagens.toArray()[a]).equals("ic_saude")){
                imageViewCategoria.setImageResource(R.mipmap.ic_saude);
            }
            if ((lstImagens.toArray()[a]).equals("ic_contas")){
                imageViewCategoria.setImageResource(R.mipmap.ic_contas);
            }
            if ((lstImagens.toArray()[a]).equals("ic_lanche")){
                imageViewCategoria.setImageResource(R.mipmap.ic_lanche);
            }
            if ((lstImagens.toArray()[a]).equals("ic_comida")){
                imageViewCategoria.setImageResource(R.mipmap.ic_comida);
            }
            if ((lstImagens.toArray()[a]).equals("ic_viagem")){
                imageViewCategoria.setImageResource(R.mipmap.ic_viagem);
            }
            if ((lstImagens.toArray()[a]).equals("ic_transporte")){
                imageViewCategoria.setImageResource(R.mipmap.ic_transporte);
            }
            if ((lstImagens.toArray()[a]).equals("ic_livro")){
                imageViewCategoria.setImageResource(R.mipmap.ic_livro);
            }
            a++;
        }
        

        //----------------------------------

        return convertView;
    }
}
