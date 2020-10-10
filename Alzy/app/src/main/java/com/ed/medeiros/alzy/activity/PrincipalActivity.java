package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterMovimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Movimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialCalendarView    calendarView;
    private FirebaseAuth            autenticacao = FirebaseAuth.getInstance();
    private String                  idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    private DatabaseReference       databaseReference = FirebaseDatabase.getInstance().getReference();
    private Double                  despesaTotal, receitaTotal;
    private TextView                textDespesa, textReceita, textSaldo, textBoasVindas;
    private String                  mesAnoSelecionado;

    //Adapter/recycler
    private Movimentacao            movimentacao;
    private RecyclerView            recyclerView;
    private AdapterMovimentacao     adapterMovimentacao;
    private List<Movimentacao>      movimentacoes = new ArrayList<>();
    private DatabaseReference       movimentacaoRef;
    private ValueEventListener      valueEventListenerMovimentacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        calendarView    = findViewById(R.id.calendarView);
        textDespesa     = findViewById(R.id.textDespesa);
        textReceita     = findViewById(R.id.textReceita);
        textSaldo       = findViewById(R.id.textSaldo);
        textBoasVindas  = findViewById(R.id.textBemVindo);
        recyclerView    = findViewById(R.id.recyclerMovimentos);


        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);

    }

    private void configuraCalendarView() {
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);
        CalendarDay dataAtual = calendarView.getCurrentDate();
        String mesSelecionado = String.format("%02d", (dataAtual.getMonth() + 1));
        mesAnoSelecionado = String.valueOf(mesSelecionado + "" + dataAtual.getYear());
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mesSelecionado = String.format("%02d", (date.getMonth() + 1));
                mesAnoSelecionado = String.valueOf(mesSelecionado + "" + date.getYear());
                movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
                recuperarMovimentacao();
            }
        });
    }

    public void recuperarMovimentacao(){
        //String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
        movimentacaoRef = databaseReference.child("movimentacoes")
                .child(idUsuario)
                .child(mesAnoSelecionado);

        valueEventListenerMovimentacoes = movimentacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movimentacoes.clear();
                for (DataSnapshot dados: dataSnapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                    movimentacao.setKey(dados.getKey());
                    movimentacoes.add(movimentacao);

                }
                adapterMovimentacao.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void recuperarSaldo() {
        DatabaseReference referenceSaldo = databaseReference.child("usuarios").child(idUsuario);
        referenceSaldo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getTotalReceita();
                despesaTotal = usuario.getTotalDespesa();
                Double calculo = receitaTotal - despesaTotal;

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(calculo);
                String total = "R$ " + resultadoFormatado;
                textSaldo.setText(total);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void recuperarReceita() {
        final DatabaseReference referenceReceita = databaseReference.child("usuarios").child(idUsuario);
        referenceReceita.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getTotalReceita();

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(receitaTotal);
                resultadoFormatado = "R$ "+resultadoFormatado;
                textReceita.setText(resultadoFormatado);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void recuperarDespesa() {
        DatabaseReference referenceDespesa = databaseReference.child("usuarios").child(idUsuario);
        referenceDespesa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getTotalDespesa();

                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(despesaTotal);
                resultadoFormatado = "R$ " + resultadoFormatado;
                textDespesa.setText(resultadoFormatado);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void darBoasVindas(){
        DatabaseReference referenceBoasVindas = databaseReference.child("usuarios").child(idUsuario);
        referenceBoasVindas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                String nomeUsuario = usuario.getNome();
                textBoasVindas.setText("Olá, " + nomeUsuario);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    //--
    public void irTelaConfiguracoes(View view){
        startActivity(new Intent(this, ConfiguracoesActivity.class));
    }
    public void irTelaReceita(View view){
        startActivity(new Intent(this, ReceitaActivity.class));
    }
    public void irTelaDespesa(View view){
        startActivity(new Intent(this, DespesaActivity.class));
    }
    public void irtelaDetalhes(View view){
        startActivity(new Intent(this, DetalhesActivity.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        recuperarDespesa();
        recuperarReceita();
        recuperarSaldo();
        darBoasVindas();

        configuraCalendarView();
        recuperarMovimentacao();

        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}