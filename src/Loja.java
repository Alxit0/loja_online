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

    }

    private void importarClientes(String ficheiroClientes){
        clientes = new ArrayList<>();
        clientes.add(new Cliente("alexa@ola", "Alex",
                "Aveiro", "3284294", "18-05-2002"));
    }

    private void importarProdutos(String ficheiroProdutos){
         this.produtos = new ArrayList<>();
    }

    private Cliente login(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String emailCliente = sc.nextLine();
        Cliente temp = procurarCliente(emailCliente);

        if (temp != null){
            System.out.println("Login bem sucedido!!");
            return temp;
        }else{
            System.out.println("Cliente nao existe");
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
