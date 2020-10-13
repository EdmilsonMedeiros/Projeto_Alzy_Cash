package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.Calendar;

public class DespesaActivity extends AppCompatActivity {

    private FirebaseAuth        autenticacao = FirebaseAuth.getInstance();
    private String              idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    private DatabaseReference   databaseReference = FirebaseDatabase.getInstance().getReference();
    private EditText            editCategoria, editDescricao, editValor;
    private TextView            editData;
    private Button              buttonEditDataDespesa;
    private Double              valorRecuperado, despesaTotal;
    private Movimentacao        movimentacao;
    private String              dataPronta;

    //DatePicker:
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        editData        = findViewById(R.id.editDataDespesa);
        editValor       = findViewById(R.id.editValorDespesa);
        editCategoria   = findViewById(R.id.editCategoriaDespesa);
        editDescricao   = findViewById(R.id.editDescricaoDespesa);
        buttonEditDataDespesa = findViewById(R.id.buttonEditDataDespesa);



        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                //Formata a data:
                mes = mes + 1;
                if (dia >= 10 && mes >= 10){
                    dataPronta = dia +"/"+mes+"/"+ano;
                }if (dia < 10 && mes < 10){
                    dataPronta = "0"+dia +"/0"+mes+"/"+ano;
                }if (dia < 10 && mes >= 10){
                    dataPronta = "0"+dia +"/"+mes+"/"+ano;
                }if (dia >= 10 && mes < 10){
                    dataPronta = ""+dia +"/0"+mes+"/"+ano;
                }
                editData.setText(dataPronta);
            }
        };

        buttonEditDataDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int ano = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        DespesaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mOnDateSetListener,
                        ano, mes, dia
                );
                dialog.getWindow().setBackgroundDrawable(getDrawable(android.R.color.transparent));
                dialog.show();
            }
        });
        //--

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

