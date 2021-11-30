import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    private final int data;
    private ArrayList<Cliente> clientes;
    private ArrayList<Produto> produtos;
    private final Cliente clienteAtivo;

    public int getData() {return data;}
    public Cliente getClienteAtivo() {return clienteAtivo;}

    public Loja(int data, String ficheiroClientes, String ficheiroProdutos) {
        this.data = data;
        importarClientes(ficheiroClientes);
        importarProdutos(ficheiroProdutos);
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
                clienteAtivo.fazerCompra();
            }else if (op == 2){
                clienteAtivo.mostrarCompras();
            }

        }
    }

    private void importarClientes(String ficheiroClientes){
        clientes = new ArrayList<>();
        clientes.add(new Cliente("alex@ola", "Alex",
                "Aveiro", "3284294", "18-05-2002"));

        clientes.add(new Cliente("a", "",
                "", "", "18--2002"));
    }

    private void importarProdutos(String ficheiroProdutos){
         this.produtos = new ArrayList<>();
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
