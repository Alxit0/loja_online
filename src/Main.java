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
            return pedirData();
        }

        int mes = Integer.parseInt(data.split("/")[1]);
        int dia = Integer.parseInt(data.split("/")[0]);
        int ano = Integer.parseInt(data.split("/")[2]);
        int eBisexto = 0;

        if (ano%4==0 && (ano%100 != 0 || ano%400 == 0)) eBisexto = 1;

        mes = Math.max(1, Math.min(12, mes));

        if (mes == 2){
            dia = Math.max(1, Math.min(28 + eBisexto, dia));
        }else if ((mes%2==1 && mes < 8) || (mes%2==0 && mes > 7)){
            dia = Math.max(1, Math.min(31, dia));
        }else dia = Math.max(1, Math.min(30, dia));
        String novaData = dia+"/"+mes+"/"+ano;

        if (!novaData.equals(data)) System.out.println("Data invalida! Mudado para: "+novaData);

        return dia+"/"+mes+"/"+ano;
    }
}
