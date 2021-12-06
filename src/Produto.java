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

    private final String diasP3L4;
    private final String diasPagueMenos;

    public int getPrecoUni() {return precoUni;}
    public String getNome() {return nome;}

    public Produto(int identificador, String nome, int precoUni, int stockExistente, String diasP3L4, String diasPagueMenos){
        this.identificador=identificador;
        this.nome=nome;
        this.precoUni=precoUni;
        this.stockExistente=stockExistente;
        this.diasP3L4 = diasP3L4;
        this.diasPagueMenos = diasPagueMenos;
    }

    public abstract double peso();

    public int custoComQuantidade(int dia, int quantidade){
        int temp;
        if (peso() > 15)
            temp = 10;
        else temp = 0;

        Promocao promoTemp = obterPromocao(dia);
        if (promoTemp == null)return (precoUni + temp) * quantidade;

        return promoTemp.custoFinal(precoUni, quantidade) + temp * quantidade;
    }

    public String promo(int dia){
        Promocao temp = obterPromocao(dia);
        if (temp != null)return temp.tag();
        else return "";
    }

    private Promocao obterPromocao(int diaAtual){
        if (diaEsta(diasP3L4, diaAtual)){
            return new P3L4();
        }else if (diaEsta(diasPagueMenos, diaAtual)){
            return new PagueMenos();
        }else
            return null;
    }

    private boolean diaEsta(String diasDePromo, int diaAtual){
        // ...;0!10;...
        String[] temp = diasDePromo.split("!");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);

        return a <= diaAtual && diaAtual <= b;
    }
}
    
