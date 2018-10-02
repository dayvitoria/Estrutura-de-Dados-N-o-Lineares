
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dayan
 */
public class ArvoreAVL {
    private No raiz = null;
    private int temp = -2;
    private No remover;
    private Vector v = new Vector();

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
    
    
    
    public No pesquisar(No n, int key){
        
        if(n!=null){
            Comparador c =new Comparador();
            temp=c.comparar(n.getValor(), key);
            
            //System.out.println(temp);
            if(temp==0){
                return n;
            }else if(temp==1){
                if(n.getFilhoEsquerdo()!=null){
                    remover = n.getFilhoEsquerdo();
                    //System.out.println(n.getFilhoEsquerdo().getValor());
                    return pesquisar(n.getFilhoEsquerdo(),key);
                    
                }else{
                    remover = n.getFilhoDireito();
                    return n;
                }
            }else if(temp==-1){
                if(n.getFilhoDireito()!=null){
                    
                    return pesquisar(n.getFilhoDireito(),key);
                }else{
                    return n;
                }  
            }
                
        }
    
        return null;
    }
    
    public No inserir(No n, int v){
        No novoNo = new No(v);
        novoNo.setFb(0);
        int res = -2;
        if(raiz==null){
          raiz= novoNo;
          //raiz.setFb(v);
            System.out.println("A raiz "+raiz.getValor()+" foi inserida.");
        }else{
            No temp=pesquisar(n,v);
            Comparador c = new Comparador();
            //System.out.println(temp.getValor());
            res=c.comparar(temp.getValor(), novoNo.getValor());
            if(res==1){
                novoNo.setPai(temp);
                temp.setFilhoEsquerdo(novoNo);
                System.out.println("O no "+novoNo.getValor()+" foi inserido a esquerda do "+temp.getValor());
                atualizaFB(temp, 1, true);
                System.out.println("O fator de balanceamento de: "+temp.getValor()+" e "+temp.getFb());
            }else{
                novoNo.setPai(temp);
                temp.setFilhoDireito(novoNo);
                System.out.println("O no "+novoNo.getValor()+" foi inserido a direita do "+temp.getValor());
                atualizaFB(temp, -1, true);
                System.out.println("O fator de balanceamento de: "+temp.getValor()+" e "+temp.getFb());
            }
        }
        
        return novoNo;
    }
    
    public void atualizaFB(No n, int v, boolean op){
        int fb_antigo = n.getFb();
        n.setFb(fb_antigo + v);
        
        if (n.getFb()== 2) {
            if (n.getFilhoEsquerdo().getFb()< 0) {
                rotacaoDuplaDireita(n);
            } else {
                rotacaoSimplesDir(n);
            }
        } else if (n.getFb()== -2) {
            if (n.getFilhoDireito().getFb()> 0) {
                //System.out.println(n.getValor()+ " " +  n.getFb());
                rotacaoDuplaEsquerda(n);
            } else {
                rotacaoSimplesEsq(n);
            }
        } else {
        
//        mostrar();
        
            if (raiz.getValor()!=n.getValor() && ((op && n.getFb() != 0) || (!op && n.getFb()== 0))) {
                No pai = n.getPai();
                System.out.println(n.getValor());
                if (n.getValor()> pai.getValor()) {
                    atualizaFB(pai, op ? -1 : 1, op);
                } else {
                    if(op==true){
                        atualizaFB(pai, 1, op);
                    }else{
                        atualizaFB(pai, -1, op);
                    }
                    
                }
            }
        }
    }
    
    
    public void remover(int v){
        No aRemover = pesquisar(raiz, v);
        Comparador c = new Comparador();
        if(aRemover!=null && aRemover.getValor()==v){
            //Nó folha
            if(aRemover.getFilhoEsquerdo()==null && aRemover.getFilhoDireito()==null){
                if(aRemover==raiz){
                    setRaiz(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(null);
                        atualizaFB(aRemover.getPai(), -1, false);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(null);
                        atualizaFB(aRemover.getPai(), 1, false);
                    }
                }
            }
            //Nó com filho esquerdo
            else if(aRemover.getFilhoEsquerdo()!=null && aRemover.getFilhoDireito()==null){
                if(aRemover==raiz){
                    setRaiz(aRemover.getFilhoEsquerdo());
                    aRemover.getFilhoEsquerdo().setFb(0);
                    aRemover.getFilhoEsquerdo().setPai(null);
                    aRemover.setFilhoEsquerdo(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(aRemover.getFilhoEsquerdo());
                        aRemover.getFilhoEsquerdo().setPai(aRemover.getPai());
                        atualizaFB(aRemover.getPai(), -1, false);
                        aRemover.setFilhoEsquerdo(null);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(aRemover.getFilhoEsquerdo());
                        aRemover.getFilhoEsquerdo().setPai(aRemover.getPai());
                        atualizaFB(aRemover.getPai(), 1, false);
                        aRemover.setFilhoEsquerdo(null);
                    }
                }
            }
            //Nó com filho direito
            else if(aRemover.getFilhoEsquerdo()==null && aRemover.getFilhoDireito()!=null){
                if(aRemover==raiz){
                    setRaiz(aRemover.getFilhoDireito());
                    aRemover.getFilhoDireito().setFb(0);
                    aRemover.getFilhoDireito().setPai(null);
                    aRemover.setFilhoDireito(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(aRemover.getFilhoDireito());
                        aRemover.getFilhoDireito().setPai(aRemover.getPai());
                        atualizaFB(aRemover.getPai(), -1, false);
                        aRemover.setFilhoDireito(null);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(aRemover.getFilhoDireito());
                        aRemover.getFilhoDireito().setPai(aRemover.getPai());
                        atualizaFB(aRemover.getPai(), 1, false);
                        aRemover.setFilhoDireito(null);
                    }
                }
            }
            //Nó com dois filhos
            else if(aRemover.getFilhoEsquerdo()!=null && aRemover.getFilhoDireito()!=null){
                    No suc = sucessor(aRemover.getFilhoDireito());
                    No temp = suc;
                    remover(suc.getValor());
                    aRemover.setValor(temp.getValor());
            }
        }
    }
    
    public void rotacaoSimplesEsq(No n){
        No subArvoreDir = n.getFilhoDireito();
        No subArvoreEsq = n.getFilhoDireito().getFilhoEsquerdo();
        No pai = n.getPai();
        
        if(subArvoreEsq!=null){
            subArvoreEsq.setPai(n);
        }
        
        if(pai==null){
            setRaiz(subArvoreDir);
        }else if(n.getValor()>pai.getValor()){
            pai.setFilhoDireito(subArvoreDir);
        }else{
            pai.setFilhoEsquerdo(subArvoreDir);
        }
        subArvoreDir.setPai(pai);
        n.setPai(subArvoreDir);
        n.setFilhoDireito(subArvoreDir.getFilhoEsquerdo());
        subArvoreDir.setFilhoEsquerdo(n);
        
        
        
        
        int fb_b = n.getFb();
        int fb_a = subArvoreDir.getFb();
        int fb_novo_a, fb_novo_b;
        fb_novo_b = fb_b + 1 - Math.min(fb_a, 0);
        fb_novo_a = fb_a + 1 + Math.max(fb_novo_b, 0);
        n.setFb(fb_novo_b);
        subArvoreDir.setFb(fb_novo_a);
        
    }
    
    public void rotacaoSimplesDir(No n){
        No subArvoreEsq = n.getFilhoEsquerdo();
        No subArvoreDirDaEsq = n.getFilhoEsquerdo().getFilhoDireito();
        No pai = n.getPai();
        
        if(subArvoreDirDaEsq!=null){
            subArvoreDirDaEsq.setPai(n);
            n.setFilhoEsquerdo(subArvoreDirDaEsq);
            subArvoreEsq.setFilhoDireito(n);
            n.setPai(subArvoreEsq);
        }
        
        if(pai==null){
            setRaiz(subArvoreEsq);
        }else if(n.getValor()>pai.getValor()){
            pai.setFilhoDireito(subArvoreEsq);
        }else{
            pai.setFilhoEsquerdo(subArvoreEsq);
        }
        subArvoreEsq.setPai(pai);
        n.setPai(subArvoreEsq);
        n.setFilhoEsquerdo(subArvoreEsq.getFilhoDireito());
        subArvoreEsq.setFilhoDireito(n);
        
        
        
        
        int fb_b = n.getFb();
        int fb_a = subArvoreEsq.getFb();
        int fb_novo_a, fb_novo_b;
        fb_novo_b = fb_b - 1 - Math.max(fb_a, 0);
        fb_novo_a = fb_a - 1 + Math.min(fb_novo_b, 0);
        n.setFb(fb_novo_b);
        subArvoreEsq.setFb(fb_novo_a);
    }
    
    public void rotacaoDuplaEsquerda(No n){
        rotacaoSimplesDir(n.getFilhoDireito());
        rotacaoSimplesEsq(n);
    }
    
    public void rotacaoDuplaDireita(No n){
        rotacaoSimplesEsq(n.getFilhoEsquerdo());
        rotacaoSimplesDir(n);
    }
    
    public No sucessor(No n){
        if(n.getFilhoEsquerdo()!=null){
            return sucessor(n.getFilhoEsquerdo());
        }else{
            return n;
        }
    }
    
    public void percorrer(No n){
            //v = new Vector();
        
            if(n.getFilhoEsquerdo()!=null){
                percorrer(n.getFilhoEsquerdo());
            }
                v.add(n);
            if(n.getFilhoDireito()!=null){
                percorrer(n.getFilhoDireito());
            }
    }
    
    public int profundidade(No n){
        int i = 0;
        while(n.getPai()!=null){
            n = n.getPai();
            i++;
        }
        return i;
    }
    
    public int altura(No n){
        
        if ( n==null || (n.getFilhoEsquerdo()==null && n.getFilhoDireito()==null)){
            return 0;
        }
        else{
            int h=0;
            h = Math.max(altura(n.getFilhoEsquerdo()), altura(n.getFilhoDireito()));
            return 1+ h;
        }       
        
    }
    
    public void mostrar(){
        percorrer(getRaiz());
        Object arvore[][] = new Object[altura(getRaiz())+1][v.size()];
        for (int i = 0; i < v.size(); i++) {
            No k = (No) v.get(i);
            arvore[profundidade((No) v.get(i))][i] = k.getValor()+"["+k.getFb()+"]";
        }
        for (int i = 0; i < altura(getRaiz())+1; i++) {
            for(int j = 0;j<v.size();j++){
                if(arvore[i][j]==null){
                    System.out.print("\t");
                }else
                System.out.print("\t"+arvore[i][j]);
            }
                System.out.println("");
        }
        
            v.clear();
    }
}
