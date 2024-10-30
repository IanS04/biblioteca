package com.devemg.data.dao;

import com.devemg.data.entities.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {

    public int insert(Usuario product) throws SQLException;
    public int update(Usuario product) throws SQLException;
    public int delete(int idProduct) throws SQLException;
    public Usuario select(int idProduct) throws SQLException;
    public List<Usuario> select() throws SQLException;

}
