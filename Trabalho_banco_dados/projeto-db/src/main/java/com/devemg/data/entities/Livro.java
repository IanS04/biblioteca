package com.devemg.data.entities;

public class Livro {
    private int idProduct;
    private String titulo;
    private int qnt;
    private int ano_publicacao;
    private String editora;
    private String autor;
    private String genero;


    public Livro(){

    }

    /**
     * Delete product
     * @param idProduct
     */
    public Livro(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Create Product
     * @param titulo
     * @param qnt
     * @param ano_publicacao
     * @param editora
     * @param autor
     * @param genero
     */
    public Livro(String titulo , int qnt, int ano_publicacao, String editora, String autor, String genero){
        this.titulo = titulo;
        this.qnt = qnt;
        this.ano_publicacao = ano_publicacao;
        this.editora = editora;
        this.autor = autor;
        this.genero = genero;
    }

    /**
     * Show product
     * @param idProduct
     * @param titulo
     * @param qnt
     * @param ano_publicacao
     * @param editora
     * @param autor
     * @param genero
     */
    public Livro(int idProduct, String titulo, int qnt, int ano_publicacao, String editora, String autor, String genero){
        this.idProduct = idProduct;
        this.titulo = titulo;
        this.qnt = qnt;
        this.ano_publicacao = ano_publicacao;
        this.editora = editora;
        this.autor = autor;
        this.genero = genero;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEditora(){
        return editora;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor(){
        return autor;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero(){
        return genero;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id:" + idProduct +
                ", titulo:'" + titulo + '\'' +
                ", qnt:" + qnt  + '\'' +
                ", ano publicacao:" + ano_publicacao +
                ", editora:" + editora +
                ", autor:" + autor +
                '}';
    }
}

