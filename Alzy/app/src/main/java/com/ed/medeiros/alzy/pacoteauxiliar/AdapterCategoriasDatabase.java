package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.medeiros.alzy.R;

import java.util.List;

public class AdapterCategoriasDatabase extends RecyclerView.Adapter<AdapterCategoriasDatabase.MyViewHolder> {
    List<CategoriasDatabase> categoriasDatabaseList;
    Context context;

    public AdapterCategoriasDatabase(List<CategoriasDatabase> categoriasDatabaseList, Context context) {
        this.categoriasDatabaseList = categoriasDatabaseList;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterCategoriasDatabase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categorias, parent, false);
        return new AdapterCategoriasDatabase.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoriasDatabase.MyViewHolder holder, int position) {
        CategoriasDatabase categoriasDatabase = categoriasDatabaseList.get(position);

        holder.nome.setText(categoriasDatabase.getNome());
    }

    @Override
    public int getItemCount() {
        return categoriasDatabaseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        CardView cardCategorias;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewTitulo);
            cardCategorias = itemView.findViewById(R.id.cardCategorias);

        }

    }
}
