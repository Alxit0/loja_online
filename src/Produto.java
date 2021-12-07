/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;

/**
 * A classe Produto irá representar o produto que irá ser comprado pelo cliente.
 * Esta terá uma variedade de métodos grande tendo cada uma a sua funcionalidade.
 *
 *
 *
 * @author Alexandre
 * @author Bernardo
 */
abstract class Produto implements Serializable {
    private final int identificador;
    private final String nome;
    private final int precoUni;
    private int stockExistente;

    private final String diasP3L4;
    private final String diasPagueMenos;

    public int getPrecoUni() {return precoUni;}
    public String getNome() {return nome;}
    public int getStockExistente() {return stockExistente;}
    public int getIdentificador() {return identificador;}

    public void setStockExistente(int stockExistente) {this.stockExistente = stockExistente;}

    public Produto(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4, String diasPagueMenos){
        this.identificador=identificador;
        this.nome=nome;
        this.precoUni=precoUni;
        this.stockExistente=stockExistente;
        this.diasP3L4 = diasP3L4;
        this.diasPagueMenos = diasPagueMenos;
    }

    /**
     * Método abstrato que servirá para obter o peso do produto mobiliário.
     *
     */
    public abstract double peso();

    /**
     * Método que irá ser usado na classe minivenda para calcular o custo da quantidade de um certo produto.
     * Para além do que já foi enunciado a mesma irá ver se o produto terá peso superior a 15(só os mobiliários têm um valor diferente de 0) se isto for verdade ,então o balor da minivenda terá um acréscimo de valor de 10.
     */
    public int custoComQuantidade(int dia, int quantidade){
        int temp;
        if (peso() > 15)
            temp = 10;
        else temp = 0;

        Promocao promoTemp = obterPromocao(dia);
        if (promoTemp == null)return (precoUni + temp) * quantidade;

        return promoTemp.custoFinal(precoUni, quantidade) + temp * quantidade;
    }

    /**
     *  Função que tem como unica funcionalidade a impressão no talão do tipo de promoção que havia no dia da compra,ou se não há promoção.
     *
     *
     */
    public String promo(int dia){
        Promocao temp = obterPromocao(dia);
        if (temp != null)return temp.tag();
        else return "";
    }

    /**
     * Este método irá verificar se o produto está em promoção ou não,se estiver irá verificar o tipo da promoção.
     * @param diaAtual Dia da compra.
     *
     */
    private Promocao obterPromocao(int diaAtual){
        if (diaEsta(diasP3L4, diaAtual)){
            return new P3L4();
        }else if (diaEsta(diasPagueMenos, diaAtual)){
            return new PagueMenos();
        }else
            return null;
    }

    /**
     * O método vai dividir a string diasDePromo(poderá ser diasP3L4 ou da promoção diasPagueMenos) que está composta pelo diainicio!diafinal,de forma a verificar se o dia da compra(diaAual) está entre os dias de promoção.
     *
     */
    private boolean diaEsta(String diasDePromo, int diaAtual){
        // ...;0!10;...
        String[] temp = diasDePromo.split("!");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);

        return a <= diaAtual && diaAtual <= b;
    }
}
    
