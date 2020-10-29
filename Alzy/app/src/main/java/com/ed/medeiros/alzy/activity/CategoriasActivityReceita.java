package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterListViewCategorias;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Categorias;
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

public class CategoriasActivityReceita extends AppCompatActivity {

    private String              idUsuario = Base64ID.codificarBase64(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    private DatabaseReference   databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference   categoriasRef;
    private ValueEventListener  valueEventListenerCategorias;
    private String              categoriaRetornada;


    private ListView            listView;
    private List<Categorias>    lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_receita);

        listView = findViewById(R.id.listViewCategorias);


    }

    private void retornaCategoriasReceita(){

        categoriasRef = databaseReference
                .child("categorias")
                .child(idUsuario)
                ;

        valueEventListenerCategorias = categoriasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dados: dataSnapshot.getChildren()){
                    Categorias categorias = dados.getValue(Categorias.class);
                    categorias.setKey(dados.getKey());
                    categoriaRetornada = categorias.getNome();
                    Log.i("CAT", categoriaRetornada);

                }
                AdapterListViewCategorias adapterListViewCategorias = new AdapterListViewCategorias(getApplicationContext(), GetDate());
                listView.setAdapter(adapterListViewCategorias);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Categorias c = lst.get(i);
                        Toast.makeText(getBaseContext(), c.getNome(), Toast.LENGTH_LONG).show();

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private List<Categorias> GetDate() {
        lst = new ArrayList<>();
        lst.add(new Categorias(categoriaRetornada, R.mipmap.ic_nota));
        return lst;
    }
    public void irTelaAdcionarCategoria(View view){
        startActivity(new Intent(this, AdicionaCategoriaActivity.class));
    }
    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        retornaCategoriasReceita();

    }

}