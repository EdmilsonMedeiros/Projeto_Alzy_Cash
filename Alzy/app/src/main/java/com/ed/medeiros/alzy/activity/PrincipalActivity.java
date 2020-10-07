package com.ed.medeiros.alzy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ed.medeiros.alzy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;
    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        calendarView = findViewById(R.id.calendarView);
    }

    public void irTelaConfiguracoes(View view){
        startActivity(new Intent(this, ConfiguracoesActivity.class));
    }
    public void irTelaReceita(View view){
        startActivity(new Intent(this, ReceitaActivity.class));
    }
    public void irTelaDespesa(View view){
        startActivity(new Intent(this, DespesaActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        configuraCalendarView();

        if (autenticacao.getCurrentUser() == null){
            startActivity(new Intent(this, SplashActivity.class));
        }

    }
    //--
    public void configuraCalendarView(){
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);
        final CalendarDay dataAtual= calendarView.getCurrentDate();

        String mesSelecionado =String.format("%02d", dataAtual.getMonth() + 1);
        String mesAnoSelecionado = mesSelecionado + "" + dataAtual.getYear();

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                //String mesAnoSelecionado = String.format("%02", date.getMonth() + 1);

            }
        });

    }
    public void irtelaDetalhes(View view){
        startActivity(new Intent(this, DetalhesActivity.class));
    }

}





















