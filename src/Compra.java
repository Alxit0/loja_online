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
        this.dia = Integer.parseInt(data.split("/")[0]);
    }

    /**
     * Funcao que quando chamada itera por uma lista de MiniVenda e calcula o seu valor final ja com os descontos
     * Sem as taxas de transporte
     *
     * @return preco final da compra
     */
    public int precoCompra(){
        int precoFinal = 0;

        for(MiniVenda i: miniVendas){
            precoFinal += i.custoMinivenda(dia);
        }

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

    /**
     * Calculo do valor de transporte mediante a frequencia do cliente, o tipo de produto (peso) e o valor total da compra.
     *
     * @param precoFinal valor final do preco para calcularmos o valor de transporte.
     * @return preco que o transporte custa.
     */
    public int precoTransporte(int precoFinal){
        int temp = 0;
        for(MiniVenda i: miniVendas){
            if (i.getProduto().peso() > 15){
                temp += 10 * i.getQuantidade();
            }
        }


        if (frequencia.equals("regular") && precoFinal < 40)
            return  15 + temp;
        else if (frequencia.equals("normal"))
            return  20 + temp;
        return temp;
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
        int transporte = precoTransporte(precoCompra());
        resp.append("Transporte ------ ").append(transporte).append("$");
        resp.append("\nTotal ----- ").append(precoCompra()+transporte).append("$");
        return "Compra no dia "+ data +":\n\t"+resp+'\n';

    }
}
