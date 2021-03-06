package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterCategorias;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Categorias;
import com.ed.medeiros.alzy.pacoteauxiliar.CategoriasDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();
    private DatabaseReference databaseReferenceCategorias = FirebaseDatabase.getInstance().getReference();
    private String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListenerCategorias;

    private AdapterCategorias adapterCategorias;


    private ListView listViewCategorias;
    private List<Categorias> categoriasList = new ArrayList<>();
    private List<Categorias> lst= new ArrayList<>();
    String categoriasNome;
    String categoriasImagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);






        //-------------
        //-------------

    }

    public void recuperarCategorias(){

        databaseReference = databaseReferenceCategorias.child("categorias").child(idUsuario);

        valueEventListenerCategorias = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lst.clear();

                for (DataSnapshot dados: dataSnapshot.getChildren()){
                    CategoriasDatabase categorias = dados.getValue(CategoriasDatabase.class);
                    categorias.setKey(dados.getKey());
                    categoriasNome = categorias.getNome();
                    categoriasImagem = categorias.getImagem();
                    //Log.i("THIS", "AQUI: "+categoriasNome);
                    listViewCategorias = findViewById(R.id.lisViewCategorias);

                    adapterCategorias = new AdapterCategorias(GetDados(categoriasNome, categoriasImagem), getApplicationContext());
                    listViewCategorias.setAdapter(adapterCategorias);

                }
                adapterCategorias.notifyDataSetChanged();
                //Executa algo ao selecionar o item

                listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Categorias c = lst.get(position);
                        //Toast.makeText(getApplicationContext(), "Retorno: "+c.getNome(), Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    //-------------

    private List<Categorias> GetDados(String categoriasRetorno, String imagem) {
        lst.add(new Categorias(categoriasRetorno, imagem));
        return lst;
    }
    //-------------


    public void irTelaAdcionarCategoria(View view){
        startActivity(new Intent(this, AdicionaCategoriaActivity.class));
    }
    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarCategorias();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}