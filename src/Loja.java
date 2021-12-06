import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    private final String data;
    private ArrayList<Cliente> clientes;
    private final Armazem armazem;
    private final Cliente clienteAtivo;

    public String getData() {return data;}
    public Cliente getClienteAtivo() {return clienteAtivo;}

    public Loja(String data, String ficheiroClientes, String ficheiroProdutos) {
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
                clienteAtivo.adicionamosCompra(sc, armazem, data, clienteAtivo);
            }else if (op == 2){
                clienteAtivo.mostrarCompras();
            }

        }
    }

    private void importarClientes(String ficheiroClientes){
        clientes = new ArrayList<>();
        File f = new File(ficheiroClientes);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null){
                String[] temp = line.split(";");
                Cliente clienteTemp = new Cliente(temp[0], temp[1], temp[2], temp[3], temp[4]);

                // iniciar array de compras
                ArrayList<Compra> compras = new ArrayList<>();
                clienteTemp.setFrequencia("");
                for (String j: temp[5].split("&")) {

                    Compra compraTemp = new Compra(clienteTemp.getFrequencia(), j.split("%")[0]);

                    // iterar miniVendas
                    for(String i: j.split("%")[1].split("@")){
                        int quant = Integer.parseInt(i.split("!")[0]);

                        int id = Integer.parseInt(i.split("!")[0]);
                        int power = (int) Math.pow(10, (int) Math.log10(id));
                        int pointer = id / power;
                        int index = id % power;

                        if (pointer == 0){
                            compraTemp.adicionarMinivenda(armazem.getProdutosAlimentares().get(index), quant);
                        }else if (pointer == 1)
                            compraTemp.adicionarMinivenda(armazem.getProdutosLimpeza().get(index), quant);
                        else
                            compraTemp.adicionarMinivenda(armazem.getProdutosLimpeza().get(index), quant);
                    }
                    compras.add(compraTemp);

                    if (compras.size() > 2){
                        clienteTemp.setFrequencia("regular");
                    }
                }
                clienteTemp.setCompras(compras);
                clientes.add(clienteTemp);

            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
