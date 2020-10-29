package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterCategoriasDatabase;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterMovimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.CategoriasDatabase;
import com.ed.medeiros.alzy.pacoteauxiliar.Movimentacao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TesteCategoriasReceitaActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    private DatabaseReference categoriasRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ValueEventListener valueEventListenerCategorias;
    private List<CategoriasDatabase> categoriasDatabases = new ArrayList<>();
    private AdapterCategoriasDatabase adapterCategoriasDatabase;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_categorias_receita);

        recyclerView = findViewById(R.id.RecyclerViewCategorias);

        adapterCategoriasDatabase = new AdapterCategoriasDatabase(categoriasDatabases, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterCategoriasDatabase);
    }

    public void recuperarMovimentacao(){
        //String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
        categoriasRef = databaseReference
                .child("categorias")
                .child(idUsuario);

        valueEventListenerCategorias = categoriasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoriasDatabases.clear();
                for (DataSnapshot dados: dataSnapshot.getChildren()){

                    CategoriasDatabase categoriasDatabaseRetorno = dados.getValue(CategoriasDatabase.class);
                    categoriasDatabaseRetorno.setKey(dados.getKey());
                    categoriasDatabases.add(categoriasDatabaseRetorno);

                }
                adapterCategoriasDatabase.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void irTelaAdcionarCategoria(View view){
        startActivity(new Intent(this, AdicionaCategoriaActivity.class));
    }
    public void sair(View view){
        finish();
    }
}