public class PagueMenos extends Promocao{
    @Override
    public int custoFinal(int precoBase, int quantidade) {
        int percentagemDiscontar = Math.min(5 * quantidade, 50);

        return (precoBase * quantidade) * (100 - percentagemDiscontar) / 100;
    }
}
