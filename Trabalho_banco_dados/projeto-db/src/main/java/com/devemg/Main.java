package com.devemg;

public class Main {
    public static void main(String[] args) {
        HandlerUsuarios handlerProducts = new HandlerUsuarios();
        int option;
        do {
            option = handlerProducts.showMenu();
            switch (option) {
                case 1 -> // create product
                        handlerProducts.createUsuario();
                case 2 -> //list products
                        handlerProducts.showAll();
                case 3 -> //see single product
                        handlerProducts.showOne();
                case 4 -> //update product
                        handlerProducts.update();
                case 5 -> //delete product
                        handlerProducts.delete();
                case 6 -> // create product
                        handlerProducts.createLivro();
                case 7 -> //list products
                        handlerProducts.showAllLivros();
                case 8 -> //see single product
                        handlerProducts.showOneLivro();
                case 9 -> //update product
                        handlerProducts.updateLivro();
                case 10 -> //delete product
                        handlerProducts.deleteLivro();
                case 11 -> //delete product
                        handlerProducts.verEmprestimo();
                case 12 -> //delete product
                        handlerProducts.fazerEmprestimo();
                case 13-> //delete product
                        handlerProducts.fazerDevolucao();
                case 14 -> //exit
                        System.out.println("bye!");
                default -> System.out.println("Option " + option + " not found");
            }
        } while (option!=14);
    }


}
