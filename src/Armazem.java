import java.io.*;
import java.util.ArrayList;

public class Armazem implements Serializable{
    private final ArrayList<ProdutoAlimentar> produtosAlimentares;
    private final ArrayList<ProdutoLimpeza> produtosLimpeza;
    private final ArrayList<ProdutoMobiliario> produtosMobiliario;

    public ArrayList<ProdutoAlimentar> getProdutosAlimentares() {return produtosAlimentares;}
    public ArrayList<ProdutoLimpeza> getProdutosLimpeza() {return produtosLimpeza;}
    public ArrayList<ProdutoMobiliario> getProdutosMobiliario() {return produtosMobiliario;}

    public Armazem(String ficheiroProdutos) {
        produtosAlimentares = new ArrayList<>();
        produtosLimpeza = new ArrayList<>();
        produtosMobiliario = new ArrayList<>();

        File f = new File(ficheiroProdutos);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null){
                String[] temp = line.split(";");
                // int identificador, String nome, int precoUni, int stockExistente,
                int id = Integer.parseInt(temp[1]);
                int preUni = Integer.parseInt(temp[3]);
                int stock = Integer.parseInt(temp[4]);

                switch (temp[0]) {
                    case "Alimentar" -> {
                        //Promocao promucao, int numCalorias, int percentagemGordura
                        int calo = Integer.parseInt(temp[7]);
                        int percGord = Integer.parseInt(temp[8]);
                        produtosAlimentares.add(new ProdutoAlimentar(id, temp[2], preUni, stock, temp[5], temp[6],
                                calo, percGord));
                    }
                    case "Limpeza" -> {
                        int grauTox = Integer.parseInt(temp[7]);
                        produtosLimpeza.add(new ProdutoLimpeza(id, temp[2], preUni, stock, temp[5], temp[6], grauTox));
                    }
                    case "Mobiliário" -> {
                        int peso = Integer.parseInt(temp[7]);
                        int dim = Integer.parseInt(temp[8]);
                        produtosMobiliario.add(new ProdutoMobiliario(id, temp[2], preUni, stock, temp[5], temp[6], peso, dim));
                    }
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
