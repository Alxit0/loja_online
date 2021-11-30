

public class Main {
    public static void main(String[] args) {
        System.out.println("=============== Loja Online ===============");
        Loja loja = new Loja(18, "clientes.txt", "produtos.txt");

        System.out.println(loja.getClienteAtivo());
        loja.menu();
    }
}
