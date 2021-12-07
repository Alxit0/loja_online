/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * A classe ProdutoAlimentar é uma subclasse do Produto.
 * Esta possui o metodo abstrato peso herdado da "classe mãe".
 * @author Alexandre
 * @author Bernardo
 */
public class ProdutoAlimentar extends Produto {
    private int numCalorias;
    private int percentagemGordura;

    public ProdutoAlimentar(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4,
                            String diasPagueMenos, int numCalorias, int percentagemGordura) {

        super(identificador, nome, precoUni, stockExistente, diasP3L4, diasPagueMenos);
        this.numCalorias = numCalorias;
        this.percentagemGordura = percentagemGordura;
    }

    public double peso(){
        return 0; 
    }

}
