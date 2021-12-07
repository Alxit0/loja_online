import java.io.Serializable;

public abstract class Promocao implements Serializable {

    public abstract int custoFinal(int precoBase, int quantidade);
    public abstract String tag();
}
