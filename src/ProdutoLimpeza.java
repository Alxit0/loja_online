/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * A classe ProdutoLimpeza é uma subclasse do Produto.
 * Esta possui o metodo abstrato peso herdado da "classe mãe".
 *
 *
 * @author Alexandre
 * @author Bernardo
 */
public class ProdutoLimpeza extends Produto {
    private final int grauToxicidade;

    public ProdutoLimpeza(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4,
                          String diasPagueMenos, int grauToxicidade) {
        super(identificador, nome, precoUni, stockExistente, diasP3L4, diasPagueMenos);
        this.grauToxicidade = grauToxicidade;
    }

    public double peso(){
        return 0; 
    }

    @Override
    public String toString() {
        return "ProdutoLimpeza{" +
                "grauToxicidade=" + grauToxicidade +
                '}';
    }
}


