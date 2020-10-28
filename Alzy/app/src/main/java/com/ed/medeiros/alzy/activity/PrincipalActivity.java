package com.ed.medeiros.alzy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ed.medeiros.alzy.R;
import com.ed.medeiros.alzy.pacoteauxiliar.AdapterMovimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Base64ID;
import com.ed.medeiros.alzy.pacoteauxiliar.Movimentacao;
import com.ed.medeiros.alzy.pacoteauxiliar.Usuario;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
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
    private DatabaseReference       usuarioRef = FirebaseDatabase.getInstance().getReference();

    private LinearLayout            linearLayoutTopo;
    private LinearLayout            linearLayoutBottom;
    private FloatingActionButton    fb_voltar;
    private FloatingActionMenu      floatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        calendarView        = findViewById(R.id.calendarView);
        textDespesa         = findViewById(R.id.textDespesa);
        textReceita         = findViewById(R.id.textReceita);
        textSaldo           = findViewById(R.id.textSaldo);
        textBoasVindas      = findViewById(R.id.textBemVindo);
        recyclerView        = findViewById(R.id.recyclerMovimentos);
        linearLayoutTopo    = findViewById(R.id.linearLayoutTopo);
        linearLayoutBottom  = findViewById(R.id.linearLayoutBottom);
        fb_voltar           = findViewById(R.id.fb_voltar);
        floatingActionMenu  = findViewById(R.id.floatingActionMenu);


        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);

        if (isOnline()){

        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            //Configura o alertDialog:
            alertDialog.setTitle("Erro!");
            alertDialog.setMessage("Você está sem acesso a internet. Verifique sua conexão.");
            alertDialog.setIcon(R.drawable.ic_baseline_warning_24);

            alertDialog.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }

    }

    public void swipe(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                exluirMovimentacao(viewHolder);
            }

        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }

    public void exluirMovimentacao(final RecyclerView.ViewHolder viewHolder){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //Configura o alertDialog:
        alertDialog.setTitle("Excluir Movimentação!");
        alertDialog.setMessage("Tem certeza que deseja excluir?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(PrincipalActivity.this, "Função indisponível", Toast.LENGTH_LONG).show();
                int position = viewHolder.getAdapterPosition();
                movimentacao = movimentacoes.get(position);
                usuarioRef = databaseReference.child("movimentacoes")
                        .child(idUsuario)
                        .child(mesAnoSelecionado);
                movimentacaoRef.child(movimentacao.getKey()).removeValue();
                adapterMovimentacao.notifyItemRemoved(position);
                //--------------

                recuperarReceita();
                recuperarDespesa();
                //recuperarDespesa();
                //Recupera o valor da movimentação excluida
                Double valor = movimentacao.getValor();
                //recupera o tipo da movimentação excluida
                String tipo = movimentacao.getTipo();
                //Verifica o tipo para atualizar o total de receita/ou despesa
                if (tipo.equals("r")){
                    usuarioRef = databaseReference.child("usuarios").child(idUsuario);
                    usuarioRef.child("totalReceita").setValue(receitaTotal - valor);
                    Toast.makeText(PrincipalActivity.this, "Receita exluida", Toast.LENGTH_LONG).show();

                }else {
                    usuarioRef = databaseReference.child("usuarios").child(idUsuario);
                    usuarioRef.child("totalDespesa").setValue(despesaTotal - valor);
                    Toast.makeText(PrincipalActivity.this, "Despesa exluida", Toast.LENGTH_LONG).show();
                }

                //--------------
                recuperarReceita();
                recuperarDespesa();
                recuperarSaldo();
                adapterMovimentacao.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PrincipalActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
                adapterMovimentacao.notifyDataSetChanged();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
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
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    @Override
    protected void onStart() {
        super.onStart();
        recuperarDespesa();
        recuperarReceita();
        recuperarSaldo();
        darBoasVindas();

        configuraCalendarView();
        swipe();
        recuperarMovimentacao();

        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }
    }
}