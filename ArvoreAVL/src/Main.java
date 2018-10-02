/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dayan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArvoreAVL a = new ArvoreAVL();
        a.inserir(null, 4);
        a.inserir(a.getRaiz(), 3);
        a.inserir(a.getRaiz(), 5);
        a.inserir(a.getRaiz(), 2);
        a.inserir(a.getRaiz(), 1);
        //a.mostrar();
        //a.inserir(a.getRaiz(), 3);
        //a.inserir(a.getRaiz(), 4);
        //a.inserir(a.getRaiz(), 5);
        //a.inserir(a.getRaiz(), 6);
        //a.inserir(a.getRaiz(), 7);
        
        //System.out.println(a.getRaiz().getValor());
        a.mostrar();
        a.remover(2);
        a.mostrar();
     /*   a.inserir(null, 40);
        //a.remover(6);
        //System.out.println(a.getRaiz());
        //a.inserir(a.getRaiz(), 10);
        a.inserir(a.getRaiz(), 30);
        a.inserir(a.getRaiz(), 35);
        a.inserir(a.getRaiz(), 10);
        a.inserir(a.getRaiz(), 12);
        a.inserir(a.getRaiz(), 5);
        a.inserir(a.getRaiz(), 60);
        a.inserir(a.getRaiz(), 70);
        a.mostrar();
        a.inserir(a.getRaiz(), 3);
        a.mostrar();
        a.inserir(a.getRaiz(), 33);
        a.mostrar();
        a.inserir(a.getRaiz(), 11);
        a.mostrar();
        a.inserir(a.getRaiz(), 9);
        a.mostrar();*/
        //System.out.println(a.altura(a.getRaiz()));
        //System.out.println(a.getRaiz().getValor());
        //a.mostrar();
        //System.out.println(a.altura(a.getRaiz()));
        
    }
    
}
