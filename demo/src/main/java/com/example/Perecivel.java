package com.example;

public class Perecivel extends ProdutoAlimenticio {
    private int dataValidade;
    private String estado;

    public Perecivel(String nome, int anoFabricacao, String classe, double preco) {
        super(nome, anoFabricacao, classe, preco);
    }

    @Override
    public String exibirInformacoes() {
        String informacoesBase = super.exibirInformacoes();

        return informacoesBase + "Data de validade: " + dataValidade + "\nEstado: " + estado;
    }
}
