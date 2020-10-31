package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.ed.medeiros.alzy.R;

import java.util.List;

public class AdapterCategoriasDatabase extends RecyclerView.Adapter<AdapterCategoriasDatabase.MyViewHolder> {

    List<CategoriasDatabase> categorias;
    Context context;

    public AdapterCategoriasDatabase(List<CategoriasDatabase> categorias, Context context) {
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_categorias, parent, false);
        return new MyViewHolder(itemLista);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoriasDatabase categoriasDatabase = categorias.get(position);

        holder.nome.setText(categoriasDatabase.getNome());
        //holder.cardCategoria.setCardBackgroundColor(context.getResources().getColor(R.color.colorCardReceita));


    }
    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView nome;
        LinearLayout cardCategoria;

        public MyViewHolder(final View itemView) {
            super(itemView);


            nome = itemView.findViewById(R.id.textViewNome);

            cardCategoria = itemView.findViewById(R.id.cardCategorias);

        }

    }

}