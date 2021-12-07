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
     * @param precoBase representa o custo do produto sem desconto
     *
     *
     */
    public abstract int custoFinal(int precoBase, int quantidade);

    /**
     * O método tag irá dar return ao nome da promoção;
     *
     */
    public abstract String tag();
}
