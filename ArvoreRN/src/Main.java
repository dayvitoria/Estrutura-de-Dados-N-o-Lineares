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
        // TODO code application logic here
        ArvoreRN a = new ArvoreRN();
        a.inserir(null, 10);
        a.inserir(a.getRaiz(), 20);
        a.inserir(a.getRaiz(), 30); 
        a.inserir(a.getRaiz(), 40);
        a.inserir(a.getRaiz(), 50);
        //a.inserir(a.getRaiz(), 40);
       
        a.inserir(a.getRaiz(), 60);
        a.inserir(a.getRaiz(), 70);
        a.inserir(a.getRaiz(), 80);
        //;
        

        a.mostrar();
        //a.remover(50);
        //a.remover(10);
        //System.out.println("------------------------------");
        //a.mostrar();
    }
    
}
