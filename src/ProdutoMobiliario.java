/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *  A classe ProdutoMobiliario é uma subclasse do Produto.
 * Esta possui o metodo abstrato peso herdado da "classe mãe".
 *
 * @author Alexandre
 * @author Bernardo
 */
public class ProdutoMobiliario extends Produto {
    private final int peso;
    private final int dimensao;

    public ProdutoMobiliario(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4,
                             String diasPagueMenos, int peso, int dimensao) {
        super(identificador, nome, precoUni, stockExistente, diasP3L4, diasPagueMenos);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public double peso(){
        return peso;
    }

    @Override
    public String toString() {
        return "ProdutoMobiliario{" +
                "peso=" + peso +
                ", dimensao=" + dimensao +
                '}';
    }
}
