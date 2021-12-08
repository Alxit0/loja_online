/**
 * A classe P3L4 é uma subclasse de Promocao, sendo esta uma classe abstrata a sua subclasse irá dar override aos métodos abstratos.
 * Esta classe representa as promoções "pague 3 leve 4".
 *
 * @author Alexandre
 * @author Bernardo
 */
public class P3L4 extends Promocao{
    @Override
    public int custoFinal(int precoBase, int quantidade) {
        int qunatidadeDiscontar = quantidade / 4;
        return (quantidade - qunatidadeDiscontar) * precoBase;
    }

    @Override
    public String tag() {
        return "(P3L4)";
    }
}
