package com.ed.medeiros.alzy.pacoteauxiliar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movimentacao {

    private FirebaseAuth    autenticacao = FirebaseAuth.getInstance();
    private String          data, categoria, descricao, tipo, key;
    private Double          valor, despesaMes, receitaMes;

    public Movimentacao(){

    }
    public void salvar(String dataEscolhida){
        String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        String mesAno = DataCustomizada.mesAnoEscolhido(dataEscolhida);

        firebase.child("movimentacoes")
                .child(idUsuario)
                .child(mesAno)
                .push()
                .setValue(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDespesaMes() {
        return despesaMes;
    }

    public void setDespesaMes(Double despesaMes) {
        this.despesaMes = despesaMes;
    }

    public Double getReceitaMes() {
        return receitaMes;
    }

    public void setReceitaMes(Double receitaMes) {
        this.receitaMes = receitaMes;
    }
}
