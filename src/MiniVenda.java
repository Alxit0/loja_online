/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
public class MiniVenda {
    private final Produto produto;
    private final int quantidade;

    public MiniVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int custoMinivenda(){
        return produto.custoComQuantidade(quantidade);
    }
}
