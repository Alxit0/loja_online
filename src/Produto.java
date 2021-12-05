/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
abstract class Produto {
    private final int identificador;
    private final String nome;
    private final int precoUni;
    private final int stockExistente;
    private final Promocao promocao;

    public int getPrecouni() {return precoUni;}
    public String getNome() {return nome;}

    public Produto(int identificador, String nome, int precoUni, int stockExistente, Promocao promucao){
        this.identificador=identificador;
        this.nome=nome;
        this.precoUni=precoUni;
        this.stockExistente=stockExistente;
        this.promocao = promucao;
    }

    public abstract double peso();

    public int custoComQuantidade(int quantidade){
        int temp;
        if (peso() > 15)
            temp = 10;
        else temp = 0;

        return promocao.custoFinal(precoUni, quantidade) + temp * quantidade;
    }
}
    
