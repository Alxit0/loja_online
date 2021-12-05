/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
public abstract class ProdutoAlimentar extends Produto {
    private int numCalorias;
    private int percentagemGordura;
    public ProdutoAlimentar(int identificador,String nome,int precouni,int stockexistente,int numCalorias,int percentagemGordura){
        super(identificador,nome,precouni,stockexistente);
        
    }
    public double peso(){
        return 0; 
    }

}
