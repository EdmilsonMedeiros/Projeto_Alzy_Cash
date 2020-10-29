package com.ed.medeiros.alzy.pacoteauxiliar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriasDatabase {
    FirebaseAuth autenticacao = FirebaseAuth.getInstance();
    String idUsuario = Base64ID.codificarBase64(autenticacao.getCurrentUser().getEmail());
    DatabaseReference firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference();

    String nome, imagem, tipo, key;

    public CategoriasDatabase() {

    }

    public void salvar(){
        firebaseDatabaseRef.child("categorias")
                .child(idUsuario)
                .push()
                .setValue(this);
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
