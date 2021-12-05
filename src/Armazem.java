import java.io.*;
import java.util.ArrayList;

public class Armazem {
    private ArrayList<ProdutoAlimentar> produtosAlimentares;
    private ArrayList<ProdutoLimpeza> produtosLimpeza;
    private ArrayList<ProdutoMobiliario> produtosMobiliario;

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
                if (temp[0].equals("Alimentar")){
                    // int identificador, String nome, int precoUni, int stockExistente, Promocao promucao,
                    // int numCalorias, int percentagemGordura
                    int id = Integer.parseInt(temp[1]);
                    int preUni = Integer.parseInt(temp[3]);
                    int stock = Integer.parseInt(temp[4]);
                    int calo = Integer.parseInt(temp[5]);
                    int percGord = Integer.parseInt(temp[6]);

                    produtosAlimentares.add(new ProdutoAlimentar(id, temp[2], preUni, stock,new P3L4() ,calo, percGord));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
