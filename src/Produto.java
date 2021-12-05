/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 3marb
 */
abstract class Produto {
    private int identificador;
    private String nome;
    private int precouni;
    private int stockexistente;
    public abstract double peso();
    public Produto(int identificador,String nome,int precouni,int stockexistente){
        this.identificador=identificador;
        this.nome=nome;
        this.precouni=precouni;
        this.stockexistente=stockexistente;
    }
}
    
