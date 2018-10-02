/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 20171014040027
 */
public class Comparador {
    
    public int comparar(int a, int b){
        int res=-2;
        
        
            if(a==b){
                res=0;
            }else if(a>b){
                res = 1;
            }else{
                res = -1;
            }
        
    
        return res;
    }
}
