import java.io.*;
import java.util.ArrayList;

/**
 * Esta classe servirá, como o nome indica, um armazém que tem 3 atributos,
 * Estes atributos são os 3 arraylists dos diferentes tipos de produtos.
 *
 * @author Alexandre
 * @author Bernardo
 */
public class Armazem implements Serializable{
    private final ArrayList<ProdutoAlimentar> produtosAlimentares;
    private final ArrayList<ProdutoLimpeza> produtosLimpeza;
    private final ArrayList<ProdutoMobiliario> produtosMobiliario;

    public ArrayList<ProdutoAlimentar> getProdutosAlimentares() {return produtosAlimentares;}
    public ArrayList<ProdutoLimpeza> getProdutosLimpeza() {return produtosLimpeza;}
    public ArrayList<ProdutoMobiliario> getProdutosMobiliario() {return produtosMobiliario;}

    /**
     * Aqui forma-se a ligação entre o ficheiro dos produtos com a funcionalidade da classe Armazem.
     * Basicamente no ficheiro com a listagem dos produtos cala linha começa com o nome do tipo de produto(ex:"Alimentar) a partir daí as restantes parselas(divididas por ";") são os atributos da do produto(sejam eles alimentares,de limpeza ou de mobiliário.
     * @param ficheiroProdutos nome do ficheiro que contemos produtos
     */
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
