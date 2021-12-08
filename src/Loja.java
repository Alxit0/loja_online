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
            //System.out.println(op);

            if (op == 0)break;
            else if (op == 1){
                clienteAtivo.adicionamosCompra(sc, armazem, data, clienteAtivo);
            }else if (op == 2){
                clienteAtivo.mostrarCompras();
            }

        }
    }

    /**
     * Metodo chamado no final do program para guardar a base de daos em ficherios de objetos.
     *
     * @param clientesFile nome do ficehiro onde esta guardados os dados dos clientes
     * @param produtosFile nome do ficehiro onde esta guardados os dados dos produtos
     */
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

    /**
     * Metodo para importar os produtos de um ficehiro.
     * Primeiramente é feita a busca por um ficheiro de objetos.
     * Caso esta falhe, é entao feito o parsing de um ficheiro de texto com a informacao dos produtos.
     *
     * @param ficheiroProdutos nome do ficehiro onde esta guardados os dados dos produtos
     */
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

    /**
     * Metodo para importar os clientes de um ficehiro.
     * Primeiramente é feita a busca por um ficheiro de objetos.
     * Caso esta falhe, é entao feito o parsing de um ficheiro de texto com a informacao dos clientes.
     *
     * @param ficheiroClientes nome do ficehiro onde esta guardados os dados dos clientes
     */
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
     * O método importarClientesTexto é constituido por uma série de processos que irão "partir" o texto no ficheiro
     * linha a linha,nestes processos de parsing iremos obter os dados do cliente.
     * Aqui também definimos a regularidade do cliente onde os clientes que possuem mais que 2 compras serão clientes regulares.
     *
     * @param ficheiroClientes Ficheiro onde se guarda os dados dos clientes.
     */
    private void importarClientesTexto(String ficheiroClientes){
        /*
            Forma de Cliente:
            ==> email ; nome ; morada ; telefone ; data de nascimento; compras

            Forma de compras:
            ==> compra & compra & compra & compra $ ...

            Forma de compra:
            ==> dataCompra % minivenda @ minivemda @ minivenda @ ...

            Forma de minivenda:
            ==> posicaoArmazem ! quantidade

            Forma da posicaoArmazem:
            ==> Digito de tipo de Produto + index na lista correspondente
                Nota: 012 -> lista 0, posicao 12
                      21  -> lista 2, posicao 1
         */
        clientes = new ArrayList<>(); // inicializar clientes

        File f = new File(ficheiroClientes);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null){
                String[] temp = line.split(";");
                //############################### email,   nome,    morada, telefone, dataNascimento
                Cliente clienteTemp = new Cliente(temp[0], temp[1], temp[2], temp[3], temp[4]);

                // iniciar array de compras
                ArrayList<Compra> compras = new ArrayList<>();
                clienteTemp.setFrequencia("normal"); // porque atualmente ele tem 0 compras

                // iterar pelas Compras
                for (String compra: temp[5].split("&")) {

                    Compra compraTemp = new Compra(clienteTemp.getFrequencia(), compra.split("%")[0]);

                    // iterar miniVendas
                    for(String miniVenda: compra.split("%")[1].split("@")){
                        // Forma de minivenda: posicaoArmazem ! quantidade

                        int quant = Integer.parseInt(miniVenda.split("!")[1]);

                        int id = Integer.parseInt(miniVenda.split("!")[0]);
                        int power = (int) Math.pow(10, (int) Math.log10(id)); // Obter o 'tamanho do numero'
                        int pointer = id / power; // obter o primeiro digito (lista pointer)
                        int index = id % power; // obter o tamanho resto do numero

                        if (pointer == 0){
                            compraTemp.adicionarMinivenda(armazem.getProdutosAlimentares().get(index), quant);
                        }else if (pointer == 1)
                            compraTemp.adicionarMinivenda(armazem.getProdutosLimpeza().get(index), quant);
                        else
                            compraTemp.adicionarMinivenda(armazem.getProdutosMobiliario().get(index), quant);
                    }
                    compras.add(compraTemp); // adicionar a compra

                    // atualizar a frequencia do cliente em questao
                    if (compras.size() > 2){
                        clienteTemp.setFrequencia("regular");
                    }
                }
                clienteTemp.setCompras(compras);
                clientes.add(clienteTemp); // adicionar clinete á base de dados
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
     * @return um cliente, seja um ja existente caso o mesmo exista na base de dados, ou um novo cliente caso contrario.
     */
    private Cliente login(){
        Scanner sc = new Scanner(System.in);

        System.out.print("login: ");
        String emailCliente = sc.nextLine();

        // procurar cliente an base de dados
        for(Cliente i: clientes){
            if (i.getEmail().equals(emailCliente)){
                // caso o encontre devolver o cliente
                return i;
            }
        }

        // caso nao seja encontrado um novo clinete criar um
        System.out.println("Cliente nã encontrado.");
        System.out.print("Criar novo cliente (y/n): ");
        String op = sc.nextLine();

        // caso o utilizador se tenha apenas enganado a escrever o email
        if (op.equals("n")){
            return login();
        }

        // Cliente --> email, nome, morada, telefone, dataNascimento
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Morada: ");
        String morada = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Data de Nascimento: ");
        String dataNas = sc.nextLine();

        Cliente novo_cliente = new Cliente(emailCliente,nome,morada,telefone,dataNas);

        // atualizar a base de dados
        clientes.add(novo_cliente);
        return novo_cliente;
    }

}
