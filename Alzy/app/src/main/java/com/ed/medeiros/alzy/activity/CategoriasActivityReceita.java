package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterListViewCategorias;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Categorias;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivityReceita extends AppCompatActivity {

    private String              idUsuario = Base64ID.codificarBase64(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    private DatabaseReference   databaseReference = FirebaseDatabase.getInstance().getReference();
    private ListView            listView;
    private List<Categorias>    lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_receita);

        listView = findViewById(R.id.listViewCategorias);

        AdapterListViewCategorias adapterListViewCategorias = new AdapterListViewCategorias(this, GetDate());
        listView.setAdapter(adapterListViewCategorias);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categorias c = lst.get(i);
                Toast.makeText(getBaseContext(), c.getNome(), Toast.LENGTH_LONG).show();

            }
        });



    }

    private List<Categorias> GetDate() {
        lst = new ArrayList<>();
        lst.add(new Categorias("Salário", R.mipmap.ic_nota));
        lst.add(new Categorias("Extra", R.mipmap.ic_extra));
        lst.add(new Categorias("Poupança", R.mipmap.ic_poupanca));
        lst.add(new Categorias("Bonificação", R.mipmap.ic_bonificacao));
        return lst;
    }

    public void sair(View view){
        finish();
    }
}