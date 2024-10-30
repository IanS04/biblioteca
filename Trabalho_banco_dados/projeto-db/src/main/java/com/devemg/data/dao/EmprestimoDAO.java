package com.devemg.data.dao;

import com.devemg.data.entities.Emprestimo;

import java.sql.SQLException;

public interface EmprestimoDAO {

    public int insert(Emprestimo product) throws SQLException;
    public int delete(int idProduct) throws SQLException;
    public void select() throws SQLException;

}
