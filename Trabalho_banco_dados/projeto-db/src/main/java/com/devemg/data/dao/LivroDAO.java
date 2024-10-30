package com.devemg.data.dao;

import com.devemg.data.entities.Livro;

import java.sql.SQLException;
import java.util.List;

public interface LivroDAO {

    public int insert(Livro product) throws SQLException;
    public int update(Livro product) throws SQLException;
    public int delete(int idProduct) throws SQLException;
    public Livro select(int idProduct) throws SQLException;
    public List<Livro> select() throws SQLException;

}
