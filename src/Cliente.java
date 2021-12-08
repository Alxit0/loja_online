import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A classe Cliente, possui atributos que discrevem/irão representar os dados necessários para o cliente/utilizador efetuar o seu login.
 * Esta classe possui vários métodos que são muito importantes para o funcionamento do programa,entre eles o adicionar compra e mostrar compra.
 *
 *
 * @author Alexandre
 * @author Bernardo
 */
public class Cliente implements Serializable {
    private final String email;
    private final String nome;
    private final String morada;
    private final String telefone;
    private final String dataNascimento;
    private String frequencia;
    private ArrayList<Compra> compras;

    public String getEmail() {return email;}
    public String getNome() {return nome;}
    public String getFrequencia() {return frequencia;}

    public void setCompras(ArrayList<Compra> compras) {this.compras = compras;}
    public void setFrequencia(String frequencia) {this.frequencia = frequencia;}

    public Cliente(String email, String nome, String morada, String telefone, String dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.frequencia = "normal";
        this.compras = new ArrayList<>();
    }

    /**
     * Este método tem como função adicionar a compra efetuada(pelo cliente) a um arraylist de compras.
     * É pedido ao utilizador que tipo de produto quer e o mesmo terá de escolher entre os 3 tipos,o utilizador irá colocar um numero entre 0 e 3.
     * O número 1 irá abrir a listagem dos produtos alimentares, o 2 irá abrir a listagem dos produtos de limpeza, o 3 irá abrir a listagem dos produtos de limpeza e o 0 irá voltar ao menu do cliente.
     *
     *
     * @param sc Pertence á classe Scanner que tem como finalidade facilitar a entrada de dados na consola, neste caso irá representar 4 valores(0 a 3) que irão ser tomados pela variável op.
     * @param armazem Pertence á classe armazem e servirá para se poder obter os produtos que estão guardados nos arraylist´s de produtos,sejam eles alimentares,limpeza ou de mobiliário.
     * @param data Este parâmetro corresponde á data que a compra é feita
     * @param clienteAtivo Corresponde ao cliente que está a utilizar o programa
     */
    public void adicionamosCompra(Scanner sc, Armazem armazem, String data, Cliente clienteAtivo){
        System.out.println(">>>>>>>>>>>>>>>>> Compra <<<<<<<<<<<<<<<<<");
        Compra temp = new Compra(clienteAtivo.getFrequencia(), data);

        while (true){
            System.out.println("-------------- Catalago --------------");
            /*
            Tipo de produtos:
                Alimetares -> 1
                Limpeza -> 2
                Mobiliario -> 3
             opcao:
             */
            System.out.println("Tipo de produtos:");
            System.out.println("\t1 > Alimetares\n\t2 > Limpeza\n\t3 > Mobiliario\n\t0 > Sair");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            if (op == 0)break;
            else if (op == 1){
                System.out.println("------------- Alimentar -------------");
                menuProdutos(temp, sc, armazem.getProdutosAlimentares());
            }
            else if (op == 2){
                System.out.println("-------------- Limpeza --------------");
                menuProdutos(temp, sc, armazem.getProdutosLimpeza());
            }
            else if (op == 3){
                System.out.println("-------------- Mobiliario --------------");
                menuProdutos(temp, sc, armazem.getProdutosMobiliario());
            }
        }
        System.out.println("Total: "+temp.precoCompra()+"$");
        System.out.println(">>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<");

        compras.add(temp);

        if (compras.size() > 2){
            this.frequencia = "regular";
        }
    }

    /**
     * O método mostrarCompras simplesmente tem como função mostrar o histórico de compras do cliente.
     */
    public void mostrarCompras(){
        System.out.println(">>>>>>>>>> Historico de Compras <<<<<<<<<<");
        for (Compra i: compras){
            System.out.println(i);
        }
    }

    /**
     * Sub menu onde é passado um ArrayList com os produtos que podem ser adicionados ao carrinho
     *
     * @param temp compra temporaria na qual vamos submeter os produtos e as respetivas qunatidades
     * @param sc scaner para ler o input do utilizador
     * @param produtos lista de produtos que serao visiveis ao utilizador.
     */
    private void menuProdutos(Compra temp, Scanner sc, ArrayList<Produto> produtos){
        for (int i = 0; i < produtos.size(); i++) {
            Produto prodTemp= produtos.get(i);
            if (prodTemp.getStockExistente() == 0)
                System.out.print("(Fora de stock)");
            System.out.println("["+ (i+1) +"] "+prodTemp.getNome()+ "--> "+prodTemp.getPrecoUni());
        }
        System.out.println("[0] Voltar");
        while (true){
            System.out.print(">> Tecla: ");
            int item = sc.nextInt();
            if (item == 0)break;
            Produto produtoEmQuestao = produtos.get(item - 1);
            pedirQuantidade(temp, sc, produtoEmQuestao);
        }
    }

    /**
     * Este método irá pedir a quantidade do produto escolhido pelo utilizador que quer comprar.
     * Para além disso o mesmo irá verificar se o produto terá stock suficiente, se não o tiver será pedido a quantidade outra vez.
     *
     * @param temp Este parâmetro representa a compra que está a ser efetuada
     * @param sc Pertence á classe Scanner que tem como finalidade facilitar a entrada de dados na consola, neste caso irá representar o valor da quantidade
     * @param produtoEmQuestao Representa o produto ao qual é pedida a quantidade do mesmo
     */
    private void pedirQuantidade(Compra temp, Scanner sc, Produto produtoEmQuestao){
        System.out.print("   Quantidade de "+produtoEmQuestao.getNome()+": ");
        int quant = sc.nextInt();

        while (produtoEmQuestao.getStockExistente() < quant){
            System.out.println("   >> Stock insuficiente");
            System.out.print("   Quantidade de "+produtoEmQuestao.getNome()+": ");
            quant = sc.nextInt();
        }
        produtoEmQuestao.setStockExistente(produtoEmQuestao.getStockExistente() - quant);

        temp.adicionarMinivenda(produtoEmQuestao, quant);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", frequencia='" + frequencia + '\'' +
                '}';
    }
}
