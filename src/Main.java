

public class Main {
    public static void main(String[] args) {
        System.out.println("=============== Loja Online ===============");
        Loja loja = new Loja("09-12-2021", "clientes.txt", "src/ficheiroProdutos");

        // System.out.println(loja.getClienteAtivo());
        loja.menu();
    }
}
