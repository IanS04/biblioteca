package com.devemg;

import com.devemg.data.JDBC.UsuarioJDBC;
import com.devemg.data.JDBC.LivroJDBC;
import com.devemg.data.JDBC.EmprestimoJDBC;
import com.devemg.data.dao.UsuarioDAO;
import com.devemg.data.dao.EmprestimoDAO;
import com.devemg.data.dao.LivroDAO;
import com.devemg.data.entities.Usuario;
import com.devemg.data.entities.Emprestimo;
import com.devemg.data.entities.Livro;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HandlerUsuarios {
    private final UsuarioDAO usuarioJDBC;
    private final LivroDAO LivroJDBC;
    private final EmprestimoDAO EmprestimoJDBC;

    public HandlerUsuarios() {
        this.usuarioJDBC = new UsuarioJDBC();
        this.LivroJDBC = new LivroJDBC();
        this.EmprestimoJDBC = new EmprestimoJDBC();
    }

    public int showMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("\t\t FUNCIONALIDADES ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Registrar new usuario");
        System.out.println("2. Ver todos usuarios");
        System.out.println("3. Ver um usuario");
        System.out.println("4. Atualizar usuario");
        System.out.println("5. Deletar usuario");
        System.out.println("------------------------------------------------------");
        System.out.println("6. Registrar novo livro");
        System.out.println("7. Ver todos livros");
        System.out.println("8. Ver um livro");
        System.out.println("9. Atualizar livro");
        System.out.println("10. Deletar livro");
        System.out.println("------------------------------------------------------");
        System.out.println("11. Ver Emprestimos");
        System.out.println("12. Realizar Emprestimo");
        System.out.println("13. Realizar Devolucao");
        System.out.println("14. Exit");
        return scanner.nextInt();
    }

    public void createUsuario(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamo criar um usuario!");
            System.out.println("Me de os dados");
            System.out.println("Nome: ");
            String nome = scanner.nextLine();
            System.out.println("cpf: ");
            String cpf = scanner.nextLine();
            System.out.println("Data Nascimento, formato: yyyy-MM-dd: ");
            String dt_nasc = scanner.nextLine();

            int insert = this.usuarioJDBC.insert(new Usuario(nome,cpf, dt_nasc));
            if(insert > 0) {
                System.out.println("Usuario criado!");
            }else {
                System.out.println("falha");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex) {
            System.out.println("falha de tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void createLivro(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamo criar um Livro!");
            System.out.println("Me de os dados");
            System.out.println("Titulo: ");
            String titulo = scanner.nextLine();
            System.out.println("Quantidade: ");
            int qnt = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ano Publicacao:");
            int ano_publi = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Editora: ");
            String editora = scanner.nextLine();
            System.out.println("Autor: ");
            String autor = scanner.nextLine();
            System.out.println("Genero: ");
            String genero = scanner.nextLine();

            int insert = this.LivroJDBC.insert(new Livro(titulo, qnt, ano_publi, editora, autor, genero));
            if(insert > 0) {
                System.out.println("Livro Criado!");
            }else {
                System.out.println("erro");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex) {
            System.out.println("falha de tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void showAll() {
        try {
            System.out.println("Listando Usuarios");
            List<Usuario> usuarios = this.usuarioJDBC.select();
            usuarios.forEach(System.out::println);
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void showOne() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos ver um usuario");
            System.out.println("Me de um ID de usuario");
            int id = scanner.nextInt();
            Usuario usuario = usuarioJDBC.select(id);
            scanner.nextLine(); //consume \n
            if(usuario != null) {
                System.out.println(usuario);
            }else {
                System.out.println("usuario nao encontrado");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha de tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void showAllLivros() {
        try {
            System.out.println("Listando Livros");
            List<Livro> livros = this.LivroJDBC.select();
            livros.forEach(System.out::println);
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void showOneLivro() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos ver um livro");
            System.out.println("Me de um ID de livro");
            int id = scanner.nextInt();
            Livro livro = LivroJDBC.select(id);
            scanner.nextLine(); //consume \n
            if(livro != null) {
                System.out.println(livro);
            }else {
                System.out.println("Livro n encontrado");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha de tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos atualizar um usuario");
            System.out.println("Coloque todos os valores do usuario");
            System.out.println("Id do usuario: ");
            int id = scanner.nextInt();
            scanner.nextLine(); //consume \n
            Usuario usuario = usuarioJDBC.select(id);
            if(usuario != null) {
                System.out.print("Nome["+usuario.getName()+"]:");
                String name = scanner.nextLine();
                System.out.print("cpf["+usuario.getCpf()+"]:");
                String cpf = scanner.nextLine();
                System.out.print("Data nasc formato: yyyy-MM-dd:["+usuario.getDt_nasc()+"]:");
                String dt_nasc = scanner.nextLine();
                if(!name.equals("")){
                    usuario.setName(name);
                }
                    usuario.setCpf(cpf);
                    usuario.setDt_nasc(dt_nasc);
                int result = usuarioJDBC.update(usuario);
                if(result > 0) {
                    System.out.println("Usuario Atualizado");
                }else {
                    System.out.println("falha");
                }
            }else {
                System.out.println("usuario not found");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("oh no! the input was type wrong.\n come back and try again.");
            scanner.nextLine();
            scanner.nextLine();
        }
    }
    public void updateLivro() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos atualizar um Livro");
            System.out.println("Coloque todos os valores do Livro");
            System.out.println("Id do Livro: ");
            int id = scanner.nextInt();
            scanner.nextLine(); //consume \n
            Livro livro = LivroJDBC.select(id);
            if(livro != null) {
                System.out.print("Nome["+livro.getTitulo()+"]:");
                String titulo = scanner.nextLine();
                System.out.print("Quantidade["+livro.getQnt()+"]:");
                int qnt = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ano publicacao["+livro.getAno_publicacao()+"]:");
                int ano_publi = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Editora["+livro.getEditora()+"]:");
                String editora = scanner.nextLine();
                System.out.print("Autor["+livro.getAutor()+"]:");
                String autor = scanner.nextLine();
                System.out.print("Genero["+livro.getAutor()+"]:");
                String genero = scanner.nextLine();
                if(!titulo.equals("")){
                    livro.setTitulo(titulo);
                }
                    livro.setQnt(qnt);
                    livro.setAno_publicacao(ano_publi);
                    livro.setEditora(editora);
                    livro.setAutor(autor);
                    livro.setGenero(genero);
                int result = LivroJDBC.update(livro);
                if(result > 0) {
                    System.out.println("Livro atualizado");
                }else {
                    System.out.println("Livro n atualizado");
                }
            }else {
                System.out.println("Livro n encontrdo");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos Deletar um Usuario");
            System.out.println("De um ID de Usuario");
            int id = scanner.nextInt();
            int result = usuarioJDBC.delete(id);
            scanner.nextLine(); //consume \n
            if(result > 0) {
                System.out.println("usuario deletado!");
            }else {
                System.out.println("usuario nao encontrado");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }
    public void deleteLivro() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos Deletar um Livro");
            System.out.println("De um ID de Livro");
            int id = scanner.nextInt();
            int result = LivroJDBC.delete(id);
            scanner.nextLine(); //consume \n
            if(result > 0) {
                System.out.println("Livro deletado!");
            }else {
                System.out.println("Livro nao encontrado");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void verEmprestimo() {
        try {
            System.out.println("Listando Emprestimos");
            this.EmprestimoJDBC.select();
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void fazerEmprestimo(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamo fazer um Emprestimo");
            System.out.println("Me de os dados");
            System.out.println("Id do usuario: ");
            int nome = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Id do livro: ");
            int cpf = scanner.nextInt();
            scanner.nextLine();

            int insert = this.EmprestimoJDBC.insert(new Emprestimo(nome,cpf));
            if(insert > 0) {
                System.out.println("Emprestimo realizado");
                this.EmprestimoJDBC.select();
            }else {
                System.out.println("falha");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch(InputMismatchException ex) {
            System.out.println("falha de tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void fazerDevolucao() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Vamos realizar a Devolucao");
            System.out.println("De um ID de um emprestimo ja feito");
            int id = scanner.nextInt();
            int result = EmprestimoJDBC.delete(id);
            scanner.nextLine(); //consume \n
            if(result > 0) {
                System.out.println("Devolvido!");
            }else {
                System.out.println("Emprestimo nao encontrado");
            }
            scanner.nextLine();
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }catch (InputMismatchException ex) {
            System.out.println("falha tipo");
            scanner.nextLine();
            scanner.nextLine();
        }
    }



    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
