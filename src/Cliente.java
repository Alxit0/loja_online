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

    public Cliente(String email, String nome, String morada, String telefone, String dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.frequencia = "normal";
        this.compras = new ArrayList<>();
    }

    public void adicionamosCompra(Compra compra){
        compras.add(compra);
    }

    public void mostrarCompras(){
        System.out.println(">> Mostrar compras feitas");
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
