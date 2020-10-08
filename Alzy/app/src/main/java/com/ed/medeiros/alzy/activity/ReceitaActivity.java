package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReceitaActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();
    private Double valorRecuperado, receitaTotal;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private EditText editValor, editData, editCategoria, editDescricao;
    private String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        editValor = findViewById(R.id.editValorReceita);
        editCategoria = findViewById(R.id.editCategoriaReceita);
        editData = findViewById(R.id.editDataReceita);
        editDescricao = findViewById(R.id.editDescricaoReceita);

    }
    public void recuperarReceita() {
        DatabaseReference referenceRecuperaReceita = databaseReference.child("usuarios").child(idUsuario);
        referenceRecuperaReceita.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getTotalReceita();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void validarCamposESalvarReceita(View view){

        String valor        = editValor.getText().toString();
        String data         = editData.getText().toString();
        String categoria    = editCategoria.getText().toString();
        String descricao    = editDescricao.getText().toString();

        if (valor.equals("") || data.equals("") || categoria.equals("") || descricao.equals("")){
            Toast.makeText(this, "Preencha Todos os Campos!!!", Toast.LENGTH_LONG).show();
        }else {
            valorRecuperado = Double.parseDouble(valor);
            DatabaseReference referenceDespesa = databaseReference.child("usuarios").child(idUsuario);
            referenceDespesa.child("totalReceita").setValue(valorRecuperado + receitaTotal);
            Toast.makeText(this, "Receita Salva!!!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        recuperarReceita();

        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}