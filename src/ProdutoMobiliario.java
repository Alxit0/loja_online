/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
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
}
