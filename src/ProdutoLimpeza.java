/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
public class ProdutoLimpeza extends Produto {
    private int grauToxicidade;

    public ProdutoLimpeza(int identificador, String nome, int precoUni, int stockExistente, Promocao promucao, int grauToxicidade) {
        super(identificador, nome, precoUni, stockExistente, promucao);
        this.grauToxicidade = grauToxicidade;
    }

    public double peso(){
        return 0; 
    }
    
}


