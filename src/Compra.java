import java.util.ArrayList;

public class Compra {
    private final String frequencia;
    private ArrayList<MiniVenda> miniVendas;

    public Compra(String frequencia) {
        this.frequencia = frequencia;
        this.miniVendas = new ArrayList<>();
    }

    public int precoCompra(){
        int precoFinal = 0;

        for(MiniVenda i: miniVendas){
            precoFinal += i.custoMinivenda();
        }

        if (frequencia.equals("Frequente") && precoFinal < 40)
            precoFinal += 15;
        else if (frequencia.equals("Regular"))
            precoFinal += 20;

        return precoFinal;
    }
}
