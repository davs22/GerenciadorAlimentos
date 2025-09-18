package com.example;

public class GerenciadorAlimentos {
    public static void main(String[] args) {
        ProdutoAlimenticio pa = new Perecivel("Maçã", 22, "Fruta", 22.0);
        pa.exibirInformacoes(); // Vai imprimir "Alimento: Maçã" no console

        ProdutoAlimenticio pg = new ProdutoAlimenticio();
        pg.calcularCompra(50.0);
    }
}