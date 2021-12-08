import java.io.Serializable;

/**
 * A classe Promocao irá representar as promoções temporárias que a impresa irá fazer.
 * Cada promoção irá ser associada a um único produto.
 * Esta classe possui dois métodos abstratos que são o custoFinal e a tag,estes irão diferir conforme o tipo de promoção.
 *
 * @author Alexandre
 * @author Bernardo
 */
public abstract class Promocao implements Serializable {

    /**
     * O método custoFinal calcula o custo do produto já descontado.
     * @param precoBase representa o custo do produto sem desconto.
     * @param quantidade representa a qunatidade de um produto.
     *
     * @return valor da qunatidade de um produto com o disconto.
     */
    public abstract int custoFinal(int precoBase, int quantidade);

    /**
     * O método "tag" irá dar return ao nome da promoção.
     *
     * @return string que nos diz o tipo de promucao aplicavel.
     */
    public abstract String tag();
}
