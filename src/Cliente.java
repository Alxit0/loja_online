import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
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

                    while (produtoEmQuestao.getStockExistente() < quant){
                        System.out.println("   >> Stock insuficiente");
                        System.out.print("   Quantidade de "+produtoEmQuestao.getNome()+": ");
                        quant = sc.nextInt();
                    }
                    produtoEmQuestao.setStockExistente(produtoEmQuestao.getStockExistente() - quant);

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
                    Produto produtoEmQuestao = armazem.getProdutosLimpeza().get(item - 1);
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
                    Produto produtoEmQuestao = armazem.getProdutosMobiliario().get(item - 1);
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
            }
        }
        System.out.println("Total: "+temp.precoCompra()+"$");
        System.out.println(">>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<");

        compras.add(temp);

        if (compras.size() > 2){
            this.frequencia = "regular";
        }
    }

    public void mostrarCompras(){
        System.out.println(">>>>>>>>>> Historico de Compras <<<<<<<<<<");
        for (Compra i: compras){
            System.out.println(i);
        }
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
