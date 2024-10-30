package com.devemg.data.JDBC;

import com.devemg.data.dao.LivroDAO;
import com.devemg.data.entities.Livro;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import static com.devemg.data.MysqlConnection.*;
import java.sql.*;
import java.util.*;

public class LivroJDBC implements LivroDAO {

    private Connection transConnection;

    // Define SQL sentences
    private static final String SQL_SELECT = "SELECT * FROM tb_obra";
    private static final String SQL_SELECT_ONE = "SELECT * FROM tb_obra WHERE id_obra = ?";
    private static final String SQL_INSERT = "INSERT INTO tb_obra(titulo, quantidade, ano_publicacao, editora, autor, genero) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tb_obra SET titulo=?,quantidade=?,ano_publicacao=?,editora=?,autor=?, genero=? WHERE id_obra = ?";
    private static final String SQL_DELETE = "DELETE FROM tb_obra WHERE id_obra = ?";

    public LivroJDBC(Connection conn){
        this.transConnection = conn;
    }

    public LivroJDBC(){
        super();
    }

   public List<Livro> select() throws SQLException{
       Connection conn = null;
       PreparedStatement pStatement = null;
       ResultSet rs = null;
       List<Livro> products = new ArrayList<>();

       try {
           conn = this.transConnection !=null?this.transConnection:getConnection();
           pStatement = conn.prepareStatement(SQL_SELECT);
           rs = pStatement.executeQuery();
           while (rs.next()){
               products.add(new Livro(
                       rs.getInt("id_obra"),
                       rs.getString("titulo"),
                       rs.getInt("quantidade"),
                       rs.getInt("ano_publicacao"),
                       rs.getString("editora"),
                       rs.getString("autor"),
                       rs.getString("genero")

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

    public Livro select(int idProduct) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        Livro product = null;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_SELECT_ONE);
            pStatement.setInt(1,idProduct);
            rs = pStatement.executeQuery();
            while (rs.next()){
                product = new Livro(
                    rs.getInt("id_obra"),
                    rs.getString("titulo"),
                    rs.getInt("quantidade"),
                    rs.getInt("ano_publicacao"),
                    rs.getString("editora"),
                    rs.getString("autor"),
                    rs.getString("genero")

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

    public int insert(Livro product) throws SQLException{
       // vaidate name
        if(product.getTitulo() == null) {
            return 0;
        }
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_INSERT);
            pStatement.setString(1,product.getTitulo());
            pStatement.setInt(2,product.getQnt());
            pStatement.setInt(3,product.getAno_publicacao());
            pStatement.setString(4,product.getEditora());
            pStatement.setString(5,product.getAutor());
            pStatement.setString(6,product.getGenero());
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

    public int update(Livro product) throws SQLException{
        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_UPDATE);
            pStatement.setString(1,product.getTitulo());
            pStatement.setInt(2,product.getQnt());
            pStatement.setInt(3,product.getAno_publicacao());
            pStatement.setString(4,product.getEditora());
            pStatement.setString(5,product.getAutor());
            pStatement.setString(6,product.getAutor());
            pStatement.setInt(7,product.getIdProduct());
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
