package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.model.Perecivel;
import com.example.model.ProdutoAlimenticio;
import com.example.model.naoPerecivel;

public class GerenciadorAlimentos {

    /**
     * MÉTODO AUXILIAR
     * Lê a próxima linha do scanner, substitui vírgula por ponto,
     * e tenta converter para double. Repete até que o usuário digite um número válido.
     * @param scanner A instância do Scanner a ser usada.
     * @return um número double válido.
     */
    private static double lerDouble(Scanner scanner) {
        while (true) { // Loop infinito até que um retorno válido aconteça
            String linha = scanner.nextLine(); // Lê a linha inteira como texto
            String linhaNormalizada = linha.replace(',', '.'); // Substitui ',' por '.'
            try {
                // Tenta converter o texto normalizado para double
                return Double.parseDouble(linhaNormalizada);
            } catch (NumberFormatException e) {
                // Se a conversão falhar, pede para o usuário tentar de novo
                System.out.print("Entrada inválida. Por favor, digite um número (ex: 1.90 ou 1,90): ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ProdutoAlimenticio> estoque = new ArrayList<>();

        System.out.println("--- Sistema de Gerenciamento de Alimentos Iniciado ---");
        System.out.println("O estoque está vazio. Use a opção 4 para adicionar produtos.");

        while (true) {
            System.out.println("\n===== MENU GERENCIADOR DE ALIMENTOS =====");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Calcular compra (demonstra SOBRECARGA específica)");
            System.out.println("3. Modificar preço (demonstra ENCAPSULAMENTO)");
            System.out.println("4. Adicionar novo produto (demonstra CONSTRUTORES)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            // Usamos um método auxiliar para ler a opção para evitar erros de input
            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                continue; // Volta para o início do loop
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- LISTA DE PRODUTOS NO ESTOQUE ---");
                    if (estoque.isEmpty()){
                        System.out.println("O estoque está vazio.");
                    } else {
                        for (ProdutoAlimenticio produto : estoque) {
                            System.out.println("--------------------");
                            System.out.println(produto.exibirInformacoes());
                        }
                        System.out.println("--------------------");
                    }
                    break;

                case 2:
                    if (estoque.isEmpty()){
                        System.out.println("O estoque está vazio. Adicione um produto primeiro.");
                        break;
                    }
                    System.out.println("\n--- QUAL PRODUTO DESEJA CALCULAR? ---");
                    for (int i = 0; i < estoque.size(); i++) {
                        System.out.println(i + " - " + estoque.get(i).getNome());
                    }
                    System.out.print("Digite o número do produto: ");
                    int produtoIndex = Integer.parseInt(scanner.nextLine());

                    if (produtoIndex < 0 || produtoIndex >= estoque.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    ProdutoAlimenticio produtoSelecionado = estoque.get(produtoIndex);
                    System.out.println("Produto selecionado: " + produtoSelecionado.getNome());

                    if (produtoSelecionado instanceof Perecivel) {
                        Perecivel perecivel = (Perecivel) produtoSelecionado;
                        System.out.println("Opções de cálculo para Perecível:");
                        System.out.println("1. Com desconto por vencimento próximo");
                        System.out.println("2. Com taxa e multa por estado");
                        System.out.print("Escolha a opção de cálculo: ");
                        int calcOpcao = Integer.parseInt(scanner.nextLine());
                        double precoFinal;
                        if (calcOpcao == 1) {
                            precoFinal = perecivel.calcularCompra(0.10, true);
                            System.out.println("Preço final (vencimento próximo): R$" + String.format("%.2f", precoFinal));
                        } else if (calcOpcao == 2) {
                            precoFinal = perecivel.calcularCompra(0.05, 0.02, true);
                            System.out.println("Preço final (com multa): R$" + String.format("%.2f", precoFinal));
                        } else {
                            System.out.println("Opção de cálculo inválida.");
                        }
                    } else if (produtoSelecionado instanceof naoPerecivel) {
                        naoPerecivel naoPerecivel = (naoPerecivel) produtoSelecionado;
                        System.out.println("Opções de cálculo para Não Perecível:");
                        System.out.println("1. Com desconto por embalagem");
                        System.out.println("2. Com taxa por tamanho");
                        System.out.print("Escolha a opção de cálculo: ");
                        int calcOpcao = Integer.parseInt(scanner.nextLine());
                        double precoFinal;
                        if (calcOpcao == 1) {
                            precoFinal = naoPerecivel.calcularCompra(0.0, true);
                            System.out.println("Preço final (desconto embalagem): R$" + String.format("%.2f", precoFinal));
                        } else if (calcOpcao == 2) {
                            precoFinal = naoPerecivel.calcularCompra(true);
                            System.out.println("Preço final (taxa por tamanho): R$" + String.format("%.2f", precoFinal));
                        } else {
                            System.out.println("Opção de cálculo inválida.");
                        }
                    }
                    break;
                
                case 3:
                     if (estoque.isEmpty()){
                        System.out.println("O estoque está vazio. Adicione um produto primeiro.");
                        break;
                    }
                    System.out.println("\n--- MODIFICAR PREÇO ---");
                    for (int i = 0; i < estoque.size(); i++) {
                        System.out.println(i + " - " + estoque.get(i).getNome());
                    }
                    System.out.print("Digite o número do produto para modificar o preço: ");
                    int produtoModificarIndex = Integer.parseInt(scanner.nextLine());

                    if (produtoModificarIndex < 0 || produtoModificarIndex >= estoque.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                    ProdutoAlimenticio produtoParaModificar = estoque.get(produtoModificarIndex);
                    System.out.println("Preço atual do " + produtoParaModificar.getNome() + ": R$" + produtoParaModificar.getPreco());
                    System.out.print("Digite o novo preço: ");
                    double novoPreco = lerDouble(scanner);
                    produtoParaModificar.setPreco(novoPreco);
                    System.out.println("Preço modificado com sucesso!");
                    break;

                case 4:
                    System.out.println("\n--- ADICIONAR NOVO PRODUTO ---");
                    System.out.print("Qual o tipo de produto? (1 - Perecível / 2 - Não Perecível): ");
                    int tipoProduto = Integer.parseInt(scanner.nextLine());

                    if (tipoProduto == 1) {
                        System.out.print("Qual construtor usar? (1 - Completo / 2 - Simplificado): ");
                        int construtorOpcao = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Ano de Fabricação: ");
                        int ano = Integer.parseInt(scanner.nextLine());
                        System.out.print("Classe (ex: Laticínio): ");
                        String classe = scanner.nextLine();
                        System.out.print("Preço: ");
                        double preco = lerDouble(scanner);

                        if (construtorOpcao == 1) {
                            System.out.print("Data de Validade (YYYYMMDD): ");
                            int dataValidade = Integer.parseInt(scanner.nextLine());
                            System.out.print("Estado (ex: bom): ");
                            String estado = scanner.nextLine();
                            estoque.add(new Perecivel(nome, ano, classe, preco, dataValidade, estado));
                        } else {
                            estoque.add(new Perecivel(nome, ano, classe, preco));
                        }
                        System.out.println("Produto Perecível adicionado com sucesso!");
                    } else if (tipoProduto == 2) {
                        System.out.print("Qual construtor usar? (1 - Completo / 2 - Simplificado): ");
                        int construtorOpcao = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Ano de Fabricação: ");
                        int ano = Integer.parseInt(scanner.nextLine());
                        System.out.print("Classe (ex: Grão): ");
                        String classe = scanner.nextLine();
                        System.out.print("Preço: ");
                        double preco = lerDouble(scanner);

                        if (construtorOpcao == 1) {
                            System.out.print("Método de Conservação (ex: Frezzer): ");
                            String metodo = scanner.nextLine();
                            System.out.print("Tamanho do Produto (ex: Grande): ");
                            String tamanho = scanner.nextLine();
                            System.out.print("Embalagem está perfeita? (true/false): ");
                            boolean embalagem = Boolean.parseBoolean(scanner.nextLine());
                            estoque.add(new naoPerecivel(nome, ano, classe, preco, metodo, tamanho, embalagem));
                        } else {
                            estoque.add(new naoPerecivel(nome, ano, classe, preco));
                        }
                        System.out.println("Produto Não Perecível adicionado com sucesso!");
                    } else {
                        System.out.println("Tipo de produto inválido.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    break;
            }
        }
    }
}