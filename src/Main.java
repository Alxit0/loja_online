

public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja(18, "clientes.txt", "produtos.txt");

        System.out.println(loja.getClienteAtivo());
        loja.menu();
    }
}
