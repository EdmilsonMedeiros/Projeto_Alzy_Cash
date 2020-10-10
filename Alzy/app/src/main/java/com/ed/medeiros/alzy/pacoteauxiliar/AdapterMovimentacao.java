package com.ed.medeiros.alzy.pacoteauxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.ed.medeiros.alzy.R;

import java.util.List;

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacoes;
    Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());
        holder.data.setText(movimentacao.getData());



        if (movimentacao.getTipo().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorPrimaryDarkDespesa));
            holder.valor.setText("-" + movimentacao.getValor());
            holder.cardMovimentacao.setCardBackgroundColor(context.getResources().getColor(R.color.colorCardDespesa));
        }
        if (movimentacao.getTipo().equals("r")){
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorPrimaryReceita));
            holder.cardMovimentacao.setCardBackgroundColor(context.getResources().getColor(R.color.colorCardReceita));
        }
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria, data;
        CardView cardMovimentacao;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
            data = itemView.findViewById(R.id.textDataAdapter);
            cardMovimentacao = itemView.findViewById(R.id.cardMovimentacao);

        }

    }

}
