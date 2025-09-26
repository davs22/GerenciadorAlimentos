package com.example.model;

public class naoPerecivel extends ProdutoAlimenticio {
    private String metodoConservacao;
    private String tamanhoProduto;
    private Boolean embalagemPerfeita;

    public naoPerecivel() {
        super();
    }

    public naoPerecivel(String nome, int anoFabricacao, String classe, double preco) {
        this(nome, anoFabricacao, classe, preco, "Frezzer", "Medio", true);
    }

    public naoPerecivel(String nome, int anoFabricacao, String classe, double preco, String metodoConservacao,
            String tamanhoProduto, Boolean embalagemPerfeita) {
        super(nome, anoFabricacao, classe, preco);
        this.metodoConservacao = metodoConservacao;
        this.tamanhoProduto = tamanhoProduto;
        this.embalagemPerfeita = embalagemPerfeita;
    }

    @Override
    public String exibirInformacoes() {
        String informacoesBase = super.exibirInformacoes();
        return informacoesBase + "\nMetódo de conservação: " + metodoConservacao + "\nTamanho do produto: "
                + tamanhoProduto + "\nEmbalagem perfeita: " + embalagemPerfeita;
    }

    public double calcularCompra(double desconto, boolean descontoPorEmbalagem) {
        double precoFinal = super.calcularCompra(desconto);

        if (embalagemPerfeita == false) {
            precoFinal *= 0.95;
        }
        return precoFinal;
    }

    public double calcularCompra(boolean taxaTamanho) {
        double precoFinal = super.calcularCompra();

        if (taxaTamanho && "grande".equalsIgnoreCase(tamanhoProduto)) {
            precoFinal *= 1.20;
        }

        return precoFinal;

    }

}
