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
