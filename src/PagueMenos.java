/**
 * A classe PagueMenos é uma subclasse de Promocao,sendo esta uma classe abstrata a sua subclasse irá dar override aos métodos abstratos.
 * Esta classe irá representar a promoção pague menos que consiste no decréscimo do custo por cada unidade adicional podendo atingir o desconto máximo(50%).
 *
 *
 * @author Alexandre
 * @author Bernardo
 */
public class PagueMenos extends Promocao{
    @Override
    public int custoFinal(int precoBase, int quantidade) {
        int percentagemDiscontar = Math.min(5 * quantidade, 50);

        return (precoBase * quantidade) * (100 - percentagemDiscontar) / 100;
    }

    @Override
    public String tag() {
        return "(Pague Menos)";
    }
}
