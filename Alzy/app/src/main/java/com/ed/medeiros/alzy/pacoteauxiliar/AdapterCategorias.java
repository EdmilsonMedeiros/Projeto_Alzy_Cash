package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.medeiros.alzy.R;

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


        //----------------------------------
        imageViewCategoria.setImageResource(R.mipmap.ic_contas);

        if (c.getImagem().equals("ic_nota")){
            imageViewCategoria.setImageResource(R.mipmap.ic_nota);
        }
        if (c.getImagem().equals("ic_extra")){
            imageViewCategoria.setImageResource(R.mipmap.ic_extra);
        }
        if (c.getImagem().equals("ic_bonificacao")){
            imageViewCategoria.setImageResource(R.mipmap.ic_bonificacao);
        }
        if (c.getImagem().equals("ic_poupanca")){
            imageViewCategoria.setImageResource(R.mipmap.ic_poupanca);
        }
        if (c.getImagem().equals("ic_cifrao")){
            imageViewCategoria.setImageResource(R.mipmap.ic_cifrao);
        }
        if (c.getImagem().equals("ic_compras")){
            imageViewCategoria.setImageResource(R.mipmap.ic_compras);
        }
        if (c.getImagem().equals("ic_casa")){
            imageViewCategoria.setImageResource(R.mipmap.ic_casa);
        }
        if (c.getImagem().equals("ic_geral")){
            imageViewCategoria.setImageResource(R.mipmap.ic_geral);
        }
        if (c.getImagem().equals("ic_carro")){
            imageViewCategoria.setImageResource(R.mipmap.ic_carro);
        }
        if (c.getImagem().equals("ic_esporte")){
            imageViewCategoria.setImageResource(R.mipmap.ic_esporte);
        }
        if (c.getImagem().equals("ic_presente")){
            imageViewCategoria.setImageResource(R.mipmap.ic_presente);
        }
        if (c.getImagem().equals("ic_roupa")){
            imageViewCategoria.setImageResource(R.mipmap.ic_roupa);
        }
        if (c.getImagem().equals("ic_entretenimento")){
            imageViewCategoria.setImageResource(R.mipmap.ic_entretenimento);
        }
        if (c.getImagem().equals("ic_ferias")){
            imageViewCategoria.setImageResource(R.mipmap.ic_ferias);
        }
        if (c.getImagem().equals("ic_saude")){
            imageViewCategoria.setImageResource(R.mipmap.ic_saude);
        }
        if (c.getImagem().equals("ic_contas")){
            imageViewCategoria.setImageResource(R.mipmap.ic_contas);
        }
        if (c.getImagem().equals("ic_lanche")){
            imageViewCategoria.setImageResource(R.mipmap.ic_lanche);
        }
        if (c.getImagem().equals("ic_comida")){
            imageViewCategoria.setImageResource(R.mipmap.ic_comida);
        }
        if (c.getImagem().equals("ic_viagem")){
            imageViewCategoria.setImageResource(R.mipmap.ic_viagem);
        }
        if (c.getImagem().equals("ic_transporte")){
            imageViewCategoria.setImageResource(R.mipmap.ic_transporte);
        }
        if (c.getImagem().equals("ic_ic_livro")){
            imageViewCategoria.setImageResource(R.mipmap.ic_livro);
        }


        //----------------------------------

        return convertView;
    }
}
