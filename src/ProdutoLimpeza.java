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

    public ProdutoLimpeza(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4,
                          String diasPagueMenos, int grauToxicidade) {
        super(identificador, nome, precoUni, stockExistente, diasP3L4, diasPagueMenos);
        this.grauToxicidade = grauToxicidade;
    }

    public double peso(){
        return 0; 
    }
    
}


