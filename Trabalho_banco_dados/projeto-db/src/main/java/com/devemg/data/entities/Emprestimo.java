package com.devemg.data.entities;

public class Emprestimo {
    private int idProduct;
    private int id_usuario;
    private int id_obra;


    public Emprestimo(){

    }

    /**
     * Delete product
     * @param idProduct
     */
    public Emprestimo(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Create Product
     * @param id_usuario
     * @param id_obra
     */
    public Emprestimo(int id_obra, int id_usuario){
        this.id_obra = id_obra;
        this.id_usuario = id_usuario;
    }

    /**
     * Show product
     * @param idProduct
     * @param id_obra
     * @param id_usuario
     */
    public Emprestimo(int idProduct, int id_obra, int id_usuario){
        this.idProduct = idProduct;
        this.id_obra = id_obra;
        this.id_usuario = id_usuario;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getid_obra() {
        return id_obra;
    }

    public void setid_obra(int id_obra) {
        this.id_obra = id_obra;
    }

    public int getid_usuario() {
        return id_usuario;
    }

    public void setid_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}

