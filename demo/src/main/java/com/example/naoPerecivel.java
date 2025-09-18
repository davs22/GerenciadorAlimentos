package com.example;

public class naoPerecivel extends ProdutoAlimenticio {
    private String metodoConservacao;
    private String tipoEmbalagem;

    public naoPerecivel(String nome, int anoFabricacao, String classe, double preco) {
        super(nome, anoFabricacao, classe, preco);
    }

    @Override
    public String exibirInformacoes() {
        String informacoesBase = super.exibirInformacoes();

        return informacoesBase + "Metódo de conservação: " + metodoConservacao + "\nTipo de embalagem: " + tipoEmbalagem;
    }
}
