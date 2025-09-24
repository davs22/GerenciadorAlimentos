package com.example;

public class GerenciadorAlimentos {
    public static void main(String[] args) {
        // ---------- PERECÍVEIS ----------
        ProdutoAlimenticio maca = new Perecivel("Maçã", 2022, "Fruta", 22.0, 20250920, "bom");
        ProdutoAlimenticio leite = new Perecivel("Leite", 2023, "Laticínio", 5.50, 20250925, "quase vencendo");

        // ---------- NÃO PERECÍVEIS ----------
        ProdutoAlimenticio arroz = new naoPerecivel("Arroz", 2019, "Grão", 4.80);
        ProdutoAlimenticio feijaoEnlatado = new naoPerecivel("Feijão Enlatado", 2020, "Enlatado", 7.50);

        // ---------- EXIBIR INFORMAÇÕES ----------
        System.out.println("===== PRODUTOS PERECÍVEIS =====");
        System.out.println(maca.exibirInformacoes());
        System.out.println(leite.exibirInformacoes());

        System.out.println("\n===== PRODUTOS NÃO PERECÍVEIS =====");
        System.out.println(arroz.exibirInformacoes());
        System.out.println(feijaoEnlatado.exibirInformacoes());

        // ---------- TESTE DE ENCAPSULAMENTO ----------
        System.out.println("\n===== TESTANDO ENCAPSULAMENTO =====");
        Perecivel carne = new Perecivel("Carne", 2024, "Açougue", 25.00, 20250910, "danificado");
        carne.setPreco(30.00); // alterando com setter
        System.out.println("Novo preço da carne: " + carne.getPreco());

        naoPerecivel macarrao = new naoPerecivel("Macarrão", 2021, "Massa", 3.00);
        macarrao.setNome("Macarrão Espaguete");
        System.out.println("Novo nome do macarrão: " + macarrao.getNome());

        // ---------- CÁLCULOS ----------
        System.out.println("\n===== CÁLCULOS =====");
        // preço normal
        System.out.println("Compra maçã (sem desconto): " + maca.calcularCompra());

        // desconto de 20%
        System.out.println("Compra leite (20% desconto): " + leite.calcularCompra(0.20));

        // desconto + taxa
        System.out.println("Compra arroz (10% desconto + 5% taxa): " + arroz.calcularCompra(0.10, 0.05));

        // penalidade em estragado/danificado
        System.out.println("Compra carne danificada (10% desconto, 5% taxa, aplicar multa): "
                + carne.calcularCompra(0.10, 0.05, true));
    }
}
