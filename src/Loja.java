import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    private final int data;
    private ArrayList<Cliente> clientes;
    private final Armazem armazem;
    private final Cliente clienteAtivo;

    public int getData() {return data;}
    public Cliente getClienteAtivo() {return clienteAtivo;}

    public Loja(int data, String ficheiroClientes, String ficheiroProdutos) {
        this.data = data;
        this.armazem = new Armazem(ficheiroProdutos);
        importarClientes(ficheiroClientes);
        this.clienteAtivo = login();
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=============== "+ clienteAtivo.getNome() + " ===============");
        while (true){
            System.out.println("Opções:");
            System.out.println("\t1 - Realizar compra");
            System.out.println("\t2 - Consultar compras");
            System.out.println("\t0 - sair");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            //String op = sc.nextLine();
            //System.out.println(op);

            if (op == 0)break;
            else if (op == 1){
                clienteAtivo.adicionamosCompra(fazerCompra());
            }else if (op == 2){
                clienteAtivo.mostrarCompras();
            }

        }
    }

    private Compra fazerCompra(){
        System.out.println(">>>>>>>>>>>>>>>>> Compra <<<<<<<<<<<<<<<<<");
        Compra temp = new Compra(clienteAtivo.getFrequencia(), data);
        Scanner sc = new Scanner(System.in);

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
                System.out.println("------------- Alimentare -------------");
                ArrayList<ProdutoAlimentar> listaTemp = armazem.getProdutosAlimentares();
                for (int i = 0; i < listaTemp.size(); i++) {
                    ProdutoAlimentar prodTemp= listaTemp.get(i);
                    System.out.println("["+ (i+1) +"] "+prodTemp.getNome()+ "--> "+prodTemp.getPrecoUni());
                }
                System.out.println("[0] Voltar");
                while (true){
                    System.out.print(">> Tecla: ");
                    int item = sc.nextInt();
                    if (item == 0)break;
                    Produto produtoEmQuestao = armazem.getProdutosAlimentares().get(item - 1);
                    System.out.print("   Quantidade de "+produtoEmQuestao.getNome()+": ");
                    int quant = sc.nextInt();
                    temp.adicionarMinivenda(produtoEmQuestao, quant);
                }
            }
            else if (op == 2){
                System.out.println("-------------- Limpeza --------------");
                ArrayList<ProdutoLimpeza> listaTemp = armazem.getProdutosLimpeza();
                for (int i = 0; i < listaTemp.size(); i++) {
                    ProdutoLimpeza prodTemp= listaTemp.get(i);
                    System.out.println("["+ (i+1) +"] "+prodTemp.getNome()+ "--> "+prodTemp.getPrecoUni());
                }
                while (true){
                    System.out.print(">> Tecla: ");
                    int item = sc.nextInt();
                    if (item == 0)break;
                    Produto produtoemQuestao = armazem.getProdutosLimpeza().get(item - 1);
                    System.out.print("   Quantidade de "+produtoemQuestao.getNome()+": ");
                    int quant = sc.nextInt();
                    temp.adicionarMinivenda(produtoemQuestao, quant);
                }
            }
            else if (op == 3){
                System.out.println("-------------- Mobiliario --------------");
                ArrayList<ProdutoMobiliario> listaTemp = armazem.getProdutosMobiliario();
                for (int i = 0; i < listaTemp.size(); i++) {
                    ProdutoMobiliario prodTemp= listaTemp.get(i);
                    System.out.println("["+ (i+1) +"] "+prodTemp.getNome()+ "--> "+prodTemp.getPrecoUni());
                }
                while (true){
                    System.out.print(">> Tecla: ");
                    int item = sc.nextInt();
                    if (item == 0)break;
                    Produto produtoemQuestao = armazem.getProdutosMobiliario().get(item - 1);
                    System.out.print("   Quantidade de "+produtoemQuestao.getNome()+": ");
                    int quant = sc.nextInt();
                    temp.adicionarMinivenda(produtoemQuestao, quant);
                }
            }
        }
        System.out.println("Total: "+temp.precoCompra()+"$");
        System.out.println(">>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<");

        return temp;
    }

    private void importarClientes(String ficheiroClientes){
        clientes = new ArrayList<>();
        clientes.add(new Cliente("alexandre@hotmail.com", "Alexand Regalado",
                "Aveiro", "916632023", "18-05-2002"));

        clientes.add(new Cliente("a", "Alexandre",
                "", "", "18--2002"));
    }

    private Cliente login(){
        Scanner sc = new Scanner(System.in);

        System.out.print("login: ");
        String emailCliente = sc.nextLine();
        Cliente temp = procurarCliente(emailCliente);

        if (temp != null){
            System.out.println("Login bem sucedido!");
            return temp;
        }else{
            System.out.println("Cliente nã encontrado.");
            System.out.print("Criar novo cliente (y/n): ");
            String op = sc.nextLine();
            if (op.equals("n")){
                return login();
            }


            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Morada: ");
            String morada = sc.nextLine();

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            System.out.print("Data de Nascimento: ");
            String dataNas = sc.nextLine();

            Cliente novo_cliente = new Cliente(emailCliente,nome,morada,telefone,dataNas);
            clientes.add(novo_cliente);
            return novo_cliente;
        }
    }

    private Cliente procurarCliente(String email){
        for(Cliente i: clientes){
            if (i.getEmail().equals(email)){
                return i;
            }
        }
        return null;
    }

}
