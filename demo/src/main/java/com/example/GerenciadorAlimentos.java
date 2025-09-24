package com.example;

public class GerenciadorAlimentos {
    public static void main(String[] args) {
        ProdutoAlimenticio pa = new Perecivel("Maçã", 2022, "Fruta", 22.0, 20250920, "bom");
        pa.exibirInformacoes(); // Vai imprimir "Alimento: Maçã" no console

        ProdutoAlimenticio pg = new ProdutoAlimenticio();
        pg.calcularCompra(50.0);
    }
}