package com.ed.medeiros.alzy.pacoteauxiliar;

public class Categorias {
    String nome, imagem, tipo, key;

    public Categorias(String nome /*,String imagem, String tipo, String key*/) {
        this.nome = nome;
        /*
        this.imagem = imagem;
        this.tipo = tipo;
        this.key = key;

         */
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
}
