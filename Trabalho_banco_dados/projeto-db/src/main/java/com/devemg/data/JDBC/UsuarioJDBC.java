package com.devemg.data.JDBC;

import com.devemg.data.dao.UsuarioDAO;
import com.devemg.data.entities.Usuario;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import static com.devemg.data.MysqlConnection.*;
import java.sql.*;
import java.util.*;

public class UsuarioJDBC implements UsuarioDAO {

    private Connection transConnection;

    // Define SQL sentences
    private static final String SQL_SELECT = "SELECT * FROM tb_usuario";
    private static final String SQL_SELECT_ONE = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
    private static final String SQL_INSERT = "INSERT INTO tb_usuario(nome, cpf, dt_nascimento) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tb_usuario SET nome=?,cpf=?,dt_nascimento=? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM tb_usuario WHERE id_usuario = ?";

    public UsuarioJDBC(Connection conn){
        this.transConnection = conn;
    }

    public UsuarioJDBC(){
        super();
    }

   public List<Usuario> select() throws SQLException{
       Connection conn = null;
       PreparedStatement pStatement = null;
       ResultSet rs = null;
       List<Usuario> products = new ArrayList<>();

       try {
           conn = this.transConnection !=null?this.transConnection:getConnection();
           pStatement = conn.prepareStatement(SQL_SELECT);
           rs = pStatement.executeQuery();
           while (rs.next()){
               products.add(new Usuario(
                       rs.getInt("id_usuario"),
                       rs.getString("nome"),
                       rs.getString("cpf"),
                       rs.getString("dt_nascimento")
               ));
           }
       }catch (SQLSyntaxErrorException ex){
           System.err.println("Error: "+ex.getMessage());
       }catch (CommunicationsException ex){
           System.err.println("Error: Can't connect with database server");
       }
       finally {
           try {
               if(rs != null)close(rs);
               if(pStatement != null)close(pStatement);
               if(conn != null) {
                   if(this.transConnection == null )close(conn);
               }
           } catch (SQLException throwables) {
               //throwables.printStackTrace();
           }
       }
    return products;
   }

    public Usuario select(int idProduct) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Usuario product = null;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_SELECT_ONE);
            pStatement.setInt(1,idProduct);
            rs = pStatement.executeQuery();
            while (rs.next()){
                product = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("dt_nascimento")
                );
            }


        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(rs != null)close(rs);
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return product;
    }

    public int insert(Usuario product) throws SQLException{
       // vaidate name
        if(product.getName() == null) {
            return 0;
        }
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_INSERT);
            pStatement.setString(1,product.getName());
            pStatement.setString(2,product.getCpf());
            pStatement.setString(3,product.getDt_nasc());
            result = pStatement.executeUpdate();
            
        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

    public int update(Usuario product) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_UPDATE);
            pStatement.setString(1,product.getName());
            pStatement.setString(2,product.getCpf());
            pStatement.setString(3,product.getDt_nasc());
            pStatement.setInt(4,product.getIdProduct());
            result = pStatement.executeUpdate();
        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

    public int delete(int idProduct) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_DELETE);
            pStatement.setInt(1,idProduct);
            result = pStatement.executeUpdate();
        }catch (SQLSyntaxErrorException ex){
            System.err.println("Error: "+ex.getMessage());
        }catch (CommunicationsException ex){
            System.err.println("Error: Can't connect with database server");
        }
        finally {
            try {
                if(pStatement != null)close(pStatement);
                if(conn != null) {
                    if(this.transConnection == null) close(conn);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }

}
