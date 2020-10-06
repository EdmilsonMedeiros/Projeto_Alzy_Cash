package com.ed.medeiros.alzy.pacoteauxiliar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class Usuario {
    private String nome, email, senha, idUsuario;
    private Double totalDespesa = 0.00;
    private Double totalReceita = 0.00;


    public void salvar(){
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        firebase.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getTotalDespesa() {
        return totalDespesa;
    }

    public void setTotalDespesa(Double totalDespesa) {
        this.totalDespesa = totalDespesa;
    }

    public Double getTotalReceita() {
        return totalReceita;
    }

    public void setTotalReceita(Double totalReceita) {
        this.totalReceita = totalReceita;
    }
}
