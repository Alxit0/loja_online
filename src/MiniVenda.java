/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;

/**
 * Esta classe é usada para calcular o valor que o cliente terá de pagar pela quantidade de um certo produto,daí se chamar "MiniVenda".
 * @author Bernardo
 * @author Alexandre
 */
public class MiniVenda implements Serializable {
    private final Produto produto;
    private int quantidade;

    public Produto getProduto() {return produto;}
    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    public MiniVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Método que dá return ao valor que terá de ser pago pela quantidade de um certo produto.
     *
     * @param dia Este corresponde ao dia em que se realiza a compra.
     * @return valor da minivenda ja com as promucoes referentes ao dia aplicaveis
     */
    public int custoMinivenda(int dia){
        return produto.custoComQuantidade(dia, quantidade);
    }

    /**
     * Este método imprime um mini-talão da compra do produto.
     *
     * @param dia Este corresponde ao dia em que se realiza a compra.
     * @return Representacao de uma minivenda no talao
     */
    public String versaoTalao(int dia) {
        return produto.getNome()+" ------ "+ quantidade + " unidade(s) "
                + custoMinivenda(dia) + "$ " + produto.promo(dia);
    }
}
