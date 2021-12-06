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
    private int quantidade;

    public Produto getProduto() {return produto;}
    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public MiniVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int custoMinivenda(int dia){
        return produto.custoComQuantidade(dia, quantidade);
    }

    public String versaoTalao(int dia) {
        return produto.getNome()+" ------ "+ quantidade + " unidade(s) "
                + custoMinivenda(dia) + "$ " + produto.promo(dia);
    }
}
