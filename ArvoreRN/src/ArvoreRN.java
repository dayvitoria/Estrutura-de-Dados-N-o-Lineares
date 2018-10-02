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
public class ArvoreRN {
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
        novoNo.setCor(0);
        int res = -2;
        if(raiz==null){
          raiz= novoNo;
          raiz.setCor(1);
            System.out.println("A raiz "+raiz.getValor()+" foi inserida.");
            System.out.println("A cor da raiz e "+ raiz.getCor());
        }else{
            No temp=pesquisar(n,v);
            Comparador c = new Comparador();
            //System.out.println(temp.getValor());
            res=c.comparar(temp.getValor(), novoNo.getValor());
            if(res==1){
                novoNo.setPai(temp);
                temp.setFilhoEsquerdo(novoNo);
                System.out.println("O no "+novoNo.getValor()+" foi inserido a esquerda do "+temp.getValor());
                rebalancear(novoNo, 1);
                System.out.println("O fator de balanceamento de: "+temp.getValor()+" e "+temp.getCor());
            }else{
                novoNo.setPai(temp);
                temp.setFilhoDireito(novoNo);
                System.out.println("O no "+novoNo.getValor()+" foi inserido a direita do "+temp.getValor());
                rebalancear(novoNo, 1);
                //System.out.println("O fator de balanceamento de: "+temp.getValor()+" e "+temp.getFb());
            }
        }
        
        return novoNo;
    }
    
    public No rebalancear(No n, int v){
        No pai = null;
        No avo = null;
        No tio = null;
        if(n!=null){
        
            if(v==1){
            //Caso 1
                if(n==raiz){
                    
                        System.out.println("tudo certo!");
                    
                }else{
                    if(n.getPai().getCor()==1){
                        System.out.println("tudo certo tambem!");
                    }
                }
            //Caso 2}
            if(n!=raiz){
                if(n.getPai()!=null){
                    pai = n.getPai();
                }if(n.getPai().getPai()!=null){
                    avo = n.getPai().getPai();
                }
                if(pai.getCor()==0&&avo.getCor()==1){
                        if(avo.getFilhoEsquerdo()==pai&&avo.getFilhoDireito()!=null){
                            if(avo.getFilhoDireito().getCor()==0){
			      if(avo!=raiz){
                                avo.setCor(0);
                              }
                              avo.getFilhoDireito().setCor(1);
                              pai.setCor(1);
                                rebalancear(avo, 1);
                            }
                        }else if(avo.getFilhoDireito()==pai&&avo.getFilhoEsquerdo()!=null){
                            if(avo.getFilhoEsquerdo().getCor()==0){
			       if(avo!=raiz){
                                avo.setCor(0);
                               }
                               avo.getFilhoEsquerdo().setCor(1);
                               pai.setCor(1);
                                rebalancear(avo, 1);
                            }
                        }
                        else if(avo.getPai()!=null&&avo.getPai().getCor()==0){
                            rebalancear(avo, 1);
                        }
                }      
                pai = null;
                avo = null;
            }
            //Caso 3
            if(n!=raiz){
                if(n.getPai()!=null){
                    pai = n.getPai();
                }if(n.getPai().getPai()!=null){
                    avo = n.getPai().getPai();
                }
                
                if(pai.getCor()==0&&avo.getCor()==1){
                    //Caso 3.a
                  if(avo.getFilhoEsquerdo()==pai){
                      tio = avo.getFilhoDireito();
                      if(pai.getFilhoEsquerdo()==n&&(tio==null||tio.getCor()==1)){
                        rotacaoSimplesDir(avo);
                        pai.setCor(1);
                        avo.setCor(0);
                        //n.setCor(v);
                      }
                  }
                  //Caso 3.b
                  else if(avo.getFilhoDireito()==pai){
                      tio = avo.getFilhoEsquerdo();
                      if(pai.getFilhoDireito()==n&&(tio==null||tio.getCor()==1)){
                          rotacaoSimplesEsq(avo);
                          pai.setCor(1);
                          avo.setCor(0);
                      }
                  }
                 //Caso 3.c
                  else if(avo.getFilhoDireito()==pai){
                      tio = avo.getFilhoEsquerdo();
                      if(tio.getCor()==1||tio==null&&pai.getFilhoEsquerdo()==n){
                          rotacaoDuplaEsquerda(avo);
                          pai.setCor(1);
                          avo.setCor(0);
                      }
                  }
                 //Caso 3.d
                  else if(avo.getFilhoEsquerdo()==pai){
                      tio = avo.getFilhoDireito();
                      if(tio.getCor()==1||tio==null&&pai.getFilhoDireito()==n){
                          rotacaoDuplaDireita(avo);
                          pai.setCor(1);
                          avo.setCor(0);
                      }
                  }
                }
            }
        }
                
    }
       
        return n;
}
    
    
    public void remover(int v){
        No aRemover = pesquisar(raiz, v);
        No aRebalancear = aRemover;
        Comparador c = new Comparador();
        if(aRemover!=null && aRemover.getValor()==v){
            //Nó folha
            if(aRemover.getFilhoEsquerdo()==null && aRemover.getFilhoDireito()==null){
                if(aRemover==raiz){
                    setRaiz(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(null);
                        System.out.println("jdkasjdsakjdkas");
                        rebalancearRemocao(aRemover);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(null);
                        //rebalancearRemocao(aRemover);
                    }
                }
            }
            //Nó com filho esquerdo
            else if(aRemover.getFilhoEsquerdo()!=null && aRemover.getFilhoDireito()==null){
                if(aRemover==raiz){
                    setRaiz(aRemover.getFilhoEsquerdo());
                    //aRemover.getFilhoEsquerdo().setFb(0);
                    aRemover.getFilhoEsquerdo().setPai(null);
                    aRemover.setFilhoEsquerdo(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(aRemover.getFilhoEsquerdo());
                        aRemover.getFilhoEsquerdo().setPai(aRemover.getPai());
                        //rebalancearRemocao(aRemover);
                        aRemover.setFilhoEsquerdo(null);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(aRemover.getFilhoEsquerdo());
                        aRemover.getFilhoEsquerdo().setPai(aRemover.getPai());
                        //rebalancearRemocao(aRemover);
                        aRemover.setFilhoEsquerdo(null);
                    }
                }
            }
            //Nó com filho direito
            else if(aRemover.getFilhoEsquerdo()==null && aRemover.getFilhoDireito()!=null){
                if(aRemover==raiz){
                    setRaiz(aRemover.getFilhoDireito());
                    //aRemover.getFilhoDireito().setFb(0);
                    aRemover.getFilhoDireito().setPai(null);
                    aRemover.setFilhoDireito(null);
                }else{
                    if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==-1){
                        aRemover.getPai().setFilhoEsquerdo(aRemover.getFilhoDireito());
                        aRemover.getFilhoDireito().setPai(aRemover.getPai());
                        //rebalancearRemocao(aRemover);
                        aRemover.setFilhoDireito(null);
                    }else if(c.comparar(aRemover.getValor(), aRemover.getPai().getValor())==1){
                        aRemover.getPai().setFilhoDireito(aRemover.getFilhoDireito());
                        aRemover.getFilhoDireito().setPai(aRemover.getPai());
                        //rebalancearRemocao(aRemover);
                        aRemover.setFilhoDireito(null);
                    }
                }
            }
            //Nó com dois filhos
            else if(aRemover.getFilhoEsquerdo()!=null && aRemover.getFilhoDireito()!=null){
                    No suc = sucessor(aRemover.getFilhoDireito());
                    No temp = suc;
                    //rebalancearRemocao(suc);
                    remover(suc.getValor());
                    aRemover.setValor(temp.getValor());
            }
        }
    }
    
    public No rebalancearRemocao(No n){
        No suc = null;
        if(n!=null){
            if(sucessor(n.getFilhoDireito())!=null){
                suc = sucessor(n);
            }
            //Caso 1
            if(n.getCor()==0&&suc.getCor()==0){
                System.out.println("Tudo certo!");
            }
            //Caso 2
            else if(n.getCor()==1&&suc.getCor()==0){
                suc.setCor(1);
            }
            //Caso 3.1
  /*          else if(n.getCor()==1&&suc.getCor()==1){
                No pai_suc = suc.getPai();
                No irmao_suc = null;
                if(pai_suc.getFilhoEsquerdo()==suc){
                    if(pai_suc.getCor()==1 && pai_suc.getFilhoDireito().getCor()==0){
                        suc.setDuploNegro(true);
                        rotacaoSimplesEsq(n);
                        pai_suc.getFilhoDireito().setCor(1);
                        pai_suc.setCor(0);
                    }
                }else if(pai_suc.getFilhoDireito()==suc){
                    if(pai_suc.getCor()==1 && pai_suc.getFilhoEsquerdo().getCor()==0){
                        suc.setDuploNegro(true);
                        rotacaoSimplesEsq(n);
                        pai_suc.getFilhoEsquerdo().setCor(1);
                        pai_suc.setCor(0);
                    }
                }
            //Caso 3.2a    
                else if(pai_suc.getFilhoEsquerdo()==suc){
                  irmao_suc = pai_suc.getFilhoDireito();
                    if(irmao_suc.getCor()==1&&pai_suc.getCor()==1){
                        if(irmao_suc.getFilhoEsquerdo()!=null&&irmao_suc.getFilhoDireito()!=null){
                            if(irmao_suc.getFilhoEsquerdo().getCor()==1 && irmao_suc.getFilhoDireito().getCor()==1){
                                irmao_suc.setCor(0);
                            }
                        }
                    }
                    //Caso 3.2b
                    else if(irmao_suc.getCor()==1&&pai_suc.getCor()==0){
                        if(irmao_suc.getFilhoEsquerdo()!=null&&irmao_suc.getFilhoDireito()!=null){
                            if(irmao_suc.getFilhoEsquerdo().getCor()==1 && irmao_suc.getFilhoDireito().getCor()==1){
                                irmao_suc.setCor(0);
                                pai_suc.setCor(1);
                            }
                        }
                    }
                }
                //Caso 3.2a
                 else if(pai_suc.getFilhoDireito()==suc){
                  irmao_suc = pai_suc.getFilhoEsquerdo();
                    if(irmao_suc.getCor()==1&&pai_suc.getCor()==1){
                        if(irmao_suc.getFilhoEsquerdo()!=null&&irmao_suc.getFilhoDireito()!=null){
                            if(irmao_suc.getFilhoEsquerdo().getCor()==1 && irmao_suc.getFilhoDireito().getCor()==1){
                                irmao_suc.setCor(0);
                            }
                        }
                    }
                    //Caso 3.2b
                     else if(irmao_suc.getCor()==1&&pai_suc.getCor()==0){
                        if(irmao_suc.getFilhoEsquerdo()!=null&&irmao_suc.getFilhoDireito()!=null){
                            if(irmao_suc.getFilhoEsquerdo().getCor()==1 && irmao_suc.getFilhoDireito().getCor()==1){
                                irmao_suc.setCor(0);
                                pai_suc.setCor(1);
                            }
                        }
                    }
                }
                //Caso 3.3
                if(pai_suc.getFilhoEsquerdo()==suc){
                    irmao_suc = pai_suc.getFilhoDireito();
                    if(irmao_suc.getCor()==1&&(pai_suc.getCor()==1||pai_suc.getCor()==0)){
                        if(irmao_suc.getFilhoEsquerdo().getCor()==0&&irmao_suc.getFilhoDireito().getCor()==1){
                            irmao_suc.setCor(0);
                            irmao_suc.getFilhoEsquerdo().setCor(1);
                            rotacaoSimplesDir(irmao_suc);
                        }
                    }
                }
                
            }*/
            
            
        }
        return n;
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
        
        
        
        
        //int fb_b = n.getFb();
        //int fb_a = subArvoreDir.getFb();
        int fb_novo_a, fb_novo_b;
        //fb_novo_b = fb_b + 1 - Math.min(fb_a, 0);
//        fb_novo_a = fb_a + 1 + Math.max(fb_novo_b, 0);
        //n.setFb(fb_novo_b);
        //subArvoreDir.setFb(fb_novo_a);
        
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
        
        
        
        
        //int fb_b = n.getFb();
        //int fb_a = subArvoreEsq.getFb();
        //int fb_novo_a, fb_novo_b;
        //fb_novo_b = fb_b - 1 - Math.max(fb_a, 0);
        //fb_novo_a = fb_a - 1 + Math.min(fb_novo_b, 0);
        //n.setFb(fb_novo_b);
        //subArvoreEsq.setFb(fb_novo_a);
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
            arvore[profundidade((No) v.get(i))][i] = k.getValor()+"["+k.getCor()+"]";
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
