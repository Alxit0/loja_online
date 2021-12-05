/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
public class ProdutoMobiliario extends Produto {
    private int peso;
    private int dimensao;

    public ProdutoMobiliario(int identificador, String nome, int precoUni, int stockExistente, Promocao promucao, int peso, int dimensao) {
        super(identificador, nome, precoUni, stockExistente, promucao);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public double peso(){
        return peso;
    }
}
