package com.ed.medeiros.alzy.pacoteauxiliar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.medeiros.alzy.R;

import java.util.List;

public class AdapterListViewCategorias extends BaseAdapter {
    Context context;
    List<Categorias> lst;

    public AdapterListViewCategorias(Context context, List<Categorias> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView   imageViewCategoria;
        TextView    textViewTitulo;

        Categorias c = lst.get(i);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.list_categorias, null);

        imageViewCategoria    =   view.findViewById(R.id.imageViewCategoria);
        textViewTitulo        =   view.findViewById(R.id.textViewTitulo);


        imageViewCategoria.setImageResource(c.imagem);
        textViewTitulo.setText(c.nome);

        return view;
    }
}
