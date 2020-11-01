package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        Categorias c = lst.get(position);

        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_categorias_adapter, null);

        textViewNome = convertView.findViewById(R.id.textListItem);

        textViewNome.setText(c.getNome());

        return convertView;
    }
}
