package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterCategoriasDatabase;
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

public class ListCategoriasActivity extends AppCompatActivity {


    private RecyclerView                recyclerCategorias;
    private List<CategoriasDatabase>    categoriasDatabaseList = new ArrayList<>();
    private ValueEventListener          valueEventListener;
    protected DatabaseReference         databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference           firebaseRef;
    private FirebaseAuth                autenticacao = FirebaseAuth.getInstance();
    private AdapterCategoriasDatabase   adapterCategoriasDatabase;
    private String                      idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    /*




     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categorias);

        recyclerCategorias = findViewById(R.id.recyclerCategorias);
        //recyclerCategorias.addOnItemTouchListener(new );

        adapterCategoriasDatabase = new AdapterCategoriasDatabase(categoriasDatabaseList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerCategorias.setAdapter(adapterCategoriasDatabase);
        recyclerCategorias.setHasFixedSize(true);
        recyclerCategorias.setLayoutManager(layoutManager);

        recuperaCategorias();


    }


    public void recuperaCategorias(){
        firebaseRef = databaseReference.child("categorias").child(idUsuario);

        valueEventListener = firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoriasDatabaseList.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    CategoriasDatabase categoriasDatabase = dados.getValue(CategoriasDatabase.class);
                    categoriasDatabase.setKey(dados.getKey());
                    categoriasDatabaseList.add(categoriasDatabase);

                }
                adapterCategoriasDatabase.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void sair(View view){
        finish();
    }


}