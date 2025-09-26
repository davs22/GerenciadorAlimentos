package com.example.model;

public class Perecivel extends ProdutoAlimenticio {
    private int dataValidade; // YYYYMMDD
    private String estado; // Ex: "bom", "estragado"

    public Perecivel() {
        super();
    }

    public Perecivel(String nome, int anoFabricacao, String classe, double preco) {
        this(nome, anoFabricacao, classe, preco, 2025, "Perfeito");
    }

    public Perecivel(String nome, int anoFabricacao, String classe, double preco, int dataValidade, String estado) {
        super(nome, anoFabricacao, classe, preco);
        this.dataValidade = dataValidade;
        this.estado = estado;
    }

    @Override
    public String exibirInformacoes() {
        String informacoesBase = super.exibirInformacoes();
        return informacoesBase + "\nData de validade: " + dataValidade + "\nEstado: " + estado;
    }

    public double calcularCompra(double desconto, boolean vencimentoProximo) {
        double precoFinal = super.calcularCompra(desconto);

        if ("quase vencendo".equalsIgnoreCase(estado)) {
            precoFinal *= 0.95;
        }

        return precoFinal;
    }

    public double calcularCompra(double desconto, double taxa, boolean aplicarMultaPorEstado) {
        double precoFinal = super.calcularCompra(desconto, taxa);

        if (aplicarMultaPorEstado && "danificado".equalsIgnoreCase(estado)) {
            precoFinal *= 1.10;
        }

        return precoFinal;
    }
}
