public class teste {
    public static void main(String[] args) {
        // int identificador, String nome, int precoUni, int stockExistente, Promocao promucao, int numCalorias, int percentagemGordura
        // int identificador, String nome, int precoUni, int stockExistente, Promocao promucao, int peso, int dimensao
        MiniVenda a = new MiniVenda(new ProdutoMobiliario(0, "",
                10, 100,
                "0!10", "20!30",10,0), 10);

        System.out.println(a.custoMinivenda(21));
    }
}
