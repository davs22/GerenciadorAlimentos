package com.example;

public class Perecivel extends ProdutoAlimenticio {
    private int dataValidade; // YYYYMMDD
    private String estado;    // Ex: "bom", "estragado"

    public Perecivel(String nome, int anoFabricacao, String classe, double preco, int dataValidade, String estado) {
        super(nome, anoFabricacao, classe, preco);
        this.dataValidade = dataValidade;
        this.estado = estado;
    }

    // Getters e setters omitidos por brevidade...

    @Override
    public String exibirInformacoes() {
        String infoBase = super.exibirInformacoes();
        return infoBase + "\nData de validade: " + dataValidade + "\nEstado: " + estado;
    }

    // Sobrescrevendo o método padrão, considerando estado
    @Override
    public double calcularCompra() {
        if ("estragado".equalsIgnoreCase(estado)) {
            System.out.println("Produto estragado! Não pode ser vendido.");
            return 0;
        }
        return super.calcularCompra(); // chama versão da superclasse
    }

    // ✅ SOBRECARGA 1: calcularCompra(desconto), mas com ajuste extra se o estado for "quase vencendo"
    @Override
    public double calcularCompra(double desconto) {
        double precoFinal = super.calcularCompra(desconto);

        // Se estiver perto da data de validade, aplica mais 5% de desconto
        if ("quase vencendo".equalsIgnoreCase(estado)) {
            precoFinal *= 0.95;
        }

        return precoFinal;
    }

    // ✅ SOBRECARGA 2: calcularCompra(desconto, taxa), com lógica diferente para perecíveis
    public double calcularCompra(double desconto, double taxa, boolean aplicarMultaPorEstado) {
        double precoFinal = super.calcularCompra(desconto, taxa);

        // Se o estado for "danificado" e aplicarMultaPorEstado for true, adiciona taxa extra de 10%
        if (aplicarMultaPorEstado && "danificado".equalsIgnoreCase(estado)) {
            precoFinal *= 1.10;
        }

        return precoFinal;
    }

    public int getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(int dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
