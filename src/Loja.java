import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A classe loja terá como finalidade a conecção dos ficheiro(que terá a listagem dos produtos) e do ficheiro (onde vai ser guardado os dados dos clientes e as suas compras) com o resto do programa
 *
 * @author Bernardo
 * @author Alexandre
 */
public class Loja {
    private final String data;
    private ArrayList<Cliente> clientes;
    private Armazem armazem;
    private final Cliente clienteAtivo;


    public Loja(String data, String ficheiroClientes, String ficheiroProdutos) {
        this.data = data;
        importarArmazem(ficheiroProdutos);
        importarClientes(ficheiroClientes);
        this.clienteAtivo = login();
    }

    /**
     * Esta função irá criar o menu do cliente onde será pedido ao mesmo que tipo de ação quer efetuar na loja.
     * A ação irá ser escolhida conforme o número que o utilizador colocar na consola, podendo esta ação ser "Realizar compra"[1], "Consultar histórico de compras"[2] ou "sair da loja"[0];
     */
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


    public void exit(String clientesFile, String produtosFile){
        guardarEmFicheiro(clientesFile);
        guardarEmFicheiro(produtosFile);
    }

    /**
     * O método em causa irá guardar os dados dos clientes e dos produtos nos respetivos ficheiros.
     * @param nomeFicherio Ficheiro onde se irá guardar os dados que poderá tomar a forma do ficheiroClientes ou do ficheiroProdutos.
     */
    private void guardarEmFicheiro(String nomeFicherio){
        File f = new File(nomeFicherio+".dat");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if (nomeFicherio.equals("ficheiroClientes"))
                oos.writeObject(clientes);
            else
                oos.writeObject(armazem);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: file not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void importarArmazem(String ficheiroProdutos){
        try {
            File f = new File(ficheiroProdutos+".dat");

            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);


            this.armazem = (Armazem) ois.readObject();

            ois.close();
            fis.close();
        }catch (Exception a){
            this.armazem = new Armazem(ficheiroProdutos);
        }

    }

    @SuppressWarnings("unchecked")
    private void importarClientes(String ficheiroClientes){
        try{
            File f = new File(ficheiroClientes+".dat");

            FileInputStream fis = new FileInputStream(f);
            System.out.println("Object");
            ObjectInputStream ois = new ObjectInputStream(fis);

            clientes = (ArrayList<Cliente>) ois.readObject();

        } catch (Exception a) {
            importarClientesTexto(ficheiroClientes);
        }
    }

    /**
     * O método importarClientesTexto é constituido por uma série de processos que irão "partir" o texto no ficheiro linha a linha,nestes processos de parsing iremos obter os dados do cliente.
     * Aqui também definimos a regularidade do cliente, decidimos portanto que os clientes que possuem mais que 2 compras serão clientes regulares.
     *
     * @param ficheiroClientes Ficheiro onde se guarda os dados dos clientes.
     */
    private void importarClientesTexto(String ficheiroClientes){
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
                            compraTemp.adicionarMinivenda(armazem.getProdutosMobiliario().get(index), quant);
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

    /**
     * O método login, como o nome indica irá pedira ao utilizador para fazer o login, se o cliente não tiver na base de dados pedirá-se ao mesmo se irá querer criar um "novo cliente".
     * Ao criar um novo cliente, os dados do mesmo serão guardados na base de dados.
     *
     */
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

    /**
     * Este método é usado no método do login e basicamente iráprocurar se o cliente existe.
     *
     */
    private Cliente procurarCliente(String email){
        for(Cliente i: clientes){
            if (i.getEmail().equals(email)){
                return i;
            }
        }
        return null;
    }
}
