package com.ed.medeiros.alzy.pacoteauxiliar;

public class Categorias {
    String nome;
    int imagem;

    public Categorias(String nome, int imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
