package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterListViewCategorias;
import com.ed.medeiros.alzy.pacoteauxiliar.Categorias;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private ListView listView;
    private List<Categorias> lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private List<Categorias> GetDate() {
        lst = new ArrayList<>();
        lst.add(new Categorias("Salário", R.mipmap.salario));
        lst.add(new Categorias("Extra", R.mipmap.salario));
        lst.add(new Categorias("Poupança", R.mipmap.salario));
        lst.add(new Categorias("Bonificação", R.mipmap.salario));
        return lst;
    }

    public void sair(View view){
        finish();
    }
}