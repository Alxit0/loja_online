import java.util.Scanner;

/**
 * Classe main onde o programa inicia
 *
 * @author Alexandre
 * @author Bernardo
 */
public class Main {
    private static final String FICHEIRO_PRODUTOS = "ficheiroProdutos";
    private static final String FICHEIRO_CLIENTES = "ficheiroClientes";

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    /**
     * Chamada de funcoes do programa
     *
     */
    private void run(){
        String data = pedirData();
        System.out.println("=============== Loja Online ===============");
        Loja loja = new Loja(data, FICHEIRO_CLIENTES, FICHEIRO_PRODUTOS);

        // System.out.println(loja.getClienteAtivo());
        loja.menu();

        loja.exit(FICHEIRO_CLIENTES, FICHEIRO_PRODUTOS);
        System.out.println("================ Terminado ================");
    }

    /**
     * Funcao para pedir a data ao autilizador
     *
     * @return string com a data do dia em que o utiliza o programa
     */
    private String pedirData(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Data atual (dd/mm/aaaa): ");
        String data = sc.nextLine();

        if(data.split("/").length != 3){
            System.out.println("Formato invalido");
        }

        return data;
    }
}
