package com.devemg.data.entities;

public class Usuario {
    private int idProduct;
    private String nome;
    private String cpf;
    private String dt_nascimento;


    public Usuario(){

    }

    /**
     * Delete product
     * @param idProduct
     */
    public Usuario(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Create Product
     * @param nome
     * @param cpf
     * @param dt_nascimento
     */
    public Usuario(String nome, String cpf, String dt_nascimento){
        this.nome = nome;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
    }

    /**
     * Show product
     * @param idProduct
     * @param nome
     * @param cpf
     * @param dt_nascimento
     */
    public Usuario(int idProduct, String nome, String cpf, String dt_nascimento){
        this.idProduct = idProduct;
        this.nome = nome;
        this.cpf = cpf;
        this.dt_nascimento = dt_nascimento;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDt_nasc() {
        return dt_nascimento;
    }

    public void setDt_nasc(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id:" + idProduct +
                ", nome:'" + nome + '\'' +
                ", cpf:" + cpf  + '\'' +
                ", dt_nascimento:" + dt_nascimento +
                '}';
    }
}

