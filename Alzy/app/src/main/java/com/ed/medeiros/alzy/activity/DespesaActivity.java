package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.DataCustomizada;
import com.ed.medeiros.alzy.pacoteauxiliar.Movimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DespesaActivity extends AppCompatActivity {

    private FirebaseAuth        autenticacao = FirebaseAuth.getInstance();
    private String              idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    private DatabaseReference   databaseReference = FirebaseDatabase.getInstance().getReference();
    private EditText            editData, editCategoria, editDescricao, editValor;
    private Double              valorRecuperado, despesaTotal;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        editValor       = findViewById(R.id.editValorDespesa);
        editData        = findViewById(R.id.editDataDespesa);
        editCategoria   = findViewById(R.id.editCategoriaDespesa);
        editDescricao   = findViewById(R.id.editDescricaoDespesa);

    }


    public void recuperarDespesa() {
        DatabaseReference referenceRecuperaDespesa = databaseReference.child("usuarios").child(idUsuario);
        referenceRecuperaDespesa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getTotalDespesa();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void validarCamposESalvarDespesa(View view){
        String valor        = editValor.getText().toString();
        String data         = editData.getText().toString();
        String categoria    = editCategoria.getText().toString();
        String descricao    = editDescricao.getText().toString();
        if (valor.equals("") || data.equals("") || categoria.equals("") || descricao.equals("")){
            Toast.makeText(this, "Preencha Todos os Campos!!!", Toast.LENGTH_LONG).show();
        }else {
            valorRecuperado = Double.parseDouble(valor);
            DatabaseReference referenceDespesa = databaseReference.child("usuarios").child(idUsuario);
            referenceDespesa.child("totalDespesa").setValue(valorRecuperado + despesaTotal);
            salvarNovaMovimentacao();
            Toast.makeText(this, "Despesa Salva!!!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void salvarNovaMovimentacao() {
        String valor        = editValor.getText().toString();
        String data         = editData.getText().toString();
        String categoria    = editCategoria.getText().toString();
        String descricao    = editDescricao.getText().toString();
        Double valorFinal   = Double.parseDouble(valor);

        movimentacao = new Movimentacao();
        movimentacao.setValor(valorFinal);
        movimentacao.setData(data);
        movimentacao.setCategoria(categoria);
        movimentacao.setDescricao(descricao);
        movimentacao.setTipo("d");
        movimentacao.salvar(data);
    }


    public void sair(View view){
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDespesa();
        editData.setText(DataCustomizada.dataAtual());

        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }


}