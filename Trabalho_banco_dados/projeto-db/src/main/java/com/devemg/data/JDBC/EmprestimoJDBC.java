package com.devemg.data.JDBC;

import com.devemg.data.dao.EmprestimoDAO;
import com.devemg.data.entities.Emprestimo;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import static com.devemg.data.MysqlConnection.*;
import java.sql.*;
import java.util.*;

public class EmprestimoJDBC implements EmprestimoDAO {

    private Connection transConnection;

    // Define SQL sentences
    private static final String SQL_SELECT = "SELECT e.id_emprestimo, u.nome, o.titulo FROM tb_emprestimo e INNER JOIN tb_usuario u ON u.id_usuario = e.id_usuario INNER JOIN tb_obra o ON o.id_obra = e.id_obra;";
    private static final String SQL_INSERT = "INSERT INTO tb_emprestimo (id_usuario, id_obra) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM tb_emprestimo WHERE id_emprestimo = ?";

    public EmprestimoJDBC(Connection conn){
        this.transConnection = conn;
    }

    public EmprestimoJDBC(){
        super();
    }

   public void select() throws SQLException{
       Connection conn = null;
       PreparedStatement pStatement = null;
       ResultSet rs = null;

       try {
           conn = this.transConnection !=null?this.transConnection:getConnection();
           pStatement = conn.prepareStatement(SQL_SELECT);
           rs = pStatement.executeQuery();
           while (rs.next()){
                System.out.println("Emprestimo{"+
                "Id:'" + rs.getString("id_emprestimo") + '\'' +
                ", Nome:" + rs.getString("nome")  + '\'' +
                ", Titulo:" + rs.getString("titulo")  + '\'' +
                '}');
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
   }

    public int insert(Emprestimo product) throws SQLException{

        Connection conn = null;
        PreparedStatement pStatement = null;
        int result = 0;
        try {
            conn = this.transConnection !=null?this.transConnection:getConnection();
            pStatement = conn.prepareStatement(SQL_INSERT);
            pStatement.setInt(1,product.getid_usuario());
            pStatement.setInt(2,product.getid_obra());
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
