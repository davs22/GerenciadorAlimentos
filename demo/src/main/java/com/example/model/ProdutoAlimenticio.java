package com.example.model;

public class ProdutoAlimenticio {

    private String nome;
    private int anoFabricacao;
    private String classe;
    private double preco;

    public ProdutoAlimenticio() {

    }

    public ProdutoAlimenticio(String nome, int anoFabricacao, String classe, double preco) {
        this.nome = nome;
        this.anoFabricacao = anoFabricacao;
        this.classe = classe;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String exibirInformacoes() {
        return "\nAlimento: " + nome + "\nData de fabricação: " + anoFabricacao + "\nClasse: " +
                classe + "\nPreço: " + preco;
    }

    public double calcularCompra() {
        return preco;
    }

    public double calcularCompra(double desconto) {
        return preco * (1 - desconto);
    }

     public double calcularCompra(double desconto, double taxa) {
        return (preco * (1 - desconto)) * (1 + taxa);
    }

}
