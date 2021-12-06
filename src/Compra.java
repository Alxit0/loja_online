import javax.swing.plaf.SpinnerUI;
import java.util.ArrayList;

public class Compra {
    private final String frequencia;
    private ArrayList<MiniVenda> miniVendas;
    private final String data;
    private final int dia;

    public Compra(String frequencia, String data) {
        this.frequencia = frequencia;
        this.miniVendas = new ArrayList<>();
        this.data = data;
        this.dia = Integer.parseInt(data.split("-")[0]);
    }

    public int precoCompra(){
        int precoFinal = 0;

        for(MiniVenda i: miniVendas){
            precoFinal += i.custoMinivenda(dia);
        }

        if (frequencia.equals("Frequente") && precoFinal < 40)
            precoFinal += 15;
        else if (frequencia.equals("Regular"))
            precoFinal += 20;

        return precoFinal;
    }

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
