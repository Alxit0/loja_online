import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe compra onde é guardada a informacao de uma compra que um cliente tenha feito.
 * Assim como a sua representação e calculos do seu valor.
 *
 * @author Alexandre
 * @author Bernardo
 */
public class Compra implements Serializable {
    private final String frequencia;
    private final ArrayList<MiniVenda> miniVendas;
    private final String data;
    private final int dia;

    public Compra(String frequencia, String data) {
        this.frequencia = frequencia;
        this.miniVendas = new ArrayList<>();
        this.data = data;
        this.dia = Integer.parseInt(data.split("-")[0]);
    }

    /**
     * Funcao que quando chamada itera por uma lista de MiniVenda e calcula o seu valor final ja com os descontos
     *
     * @return preco final da compra
     */
    public int precoCompra(){
        int precoFinal = 0;

        for(MiniVenda i: miniVendas){
            precoFinal += i.custoMinivenda(dia);
        }

        if (frequencia.equals("normal") && precoFinal < 40)
            precoFinal += 15;
        else if (frequencia.equals("regular"))
            precoFinal += 20;

        return precoFinal;
    }

    /**
     * Metodo para adicionar MiniVendas á compra.
     *
     * @param prod produto que se pretende adicionar á compra
     * @param quant qunatidade do produto que se pretende adicionar á compra
     */
    public void adicionarMinivenda(Produto prod, int quant){
        boolean chek = true;
        for(MiniVenda i: miniVendas){
            if (i.getProduto().getIdentificador() == prod.getIdentificador()) {
                i.setQuantidade(i.getQuantidade() + quant);
                chek = false;
            }
        }
        if (chek) miniVendas.add(new MiniVenda(prod, quant));
    }

    @Override
    public String toString() {
        /*
        Compra no dia xx:
        Prodtuto [quntidade] --- precFinal (P3L4)

        Prodtuto ----- quntidade
        Prodtuto ----- quntidade
        Prodtuto ----- quntidade
        ...

         */

        StringBuilder resp = new StringBuilder();
        for (MiniVenda i: miniVendas){
            resp.append(i.versaoTalao(dia)).append("\n\t");
        }
        return "Compra no dia "+ data +":\n\t"+resp;

    }
}
