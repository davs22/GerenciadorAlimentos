package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.model.Perecivel;
import com.example.model.ProdutoAlimenticio;
import com.example.model.naoPerecivel;

/**
 * Classe principal que gerencia o estoque de produtos alimentícios.
 * Contém o menu de interação com o utilizador para listar, calcular preços,
 * modificar e adicionar novos produtos.
 */
public class GerenciadorAlimentos {

    /**
     * MÉTODO AUXILIAR
     * Lê a próxima linha do scanner, substitui vírgula por ponto,
     * e tenta converter para double. Repete até que o utilizador digite um número válido.
     * @param scanner A instância do Scanner a ser usada.
     * @return um número double válido.
     */
    private static double lerDouble(Scanner scanner) {
        while (true) { // Loop infinito que só é quebrado quando um número válido é retornado.
            String linha = scanner.nextLine(); // Lê a linha inteira como texto.
            String linhaNormalizada = linha.replace(',', '.'); // Substitui ',' por '.' para aceitar ambos os formatos (ex: 1,90 e 1.90).
            try {
                // Tenta converter o texto normalizado para double.
                return Double.parseDouble(linhaNormalizada);
            } catch (NumberFormatException e) {
                // Se a conversão falhar, informa o erro e pede para o utilizador tentar de novo.
                System.out.print("Entrada inválida. Por favor, digite um número (ex: 1.90 ou 1,90): ");
            }
        }
    }

    // Método principal que inicia a execução do programa.
    public static void main(String[] args) {
        // Cria um Scanner para ler a entrada do utilizador a partir do console.
        Scanner scanner = new Scanner(System.in);
        // Cria uma lista para armazenar os produtos alimentícios em estoque.
        // A lista é do tipo da superclasse 'ProdutoAlimenticio' para poder conter objetos de ambas as subclasses (Polimorfismo).
        List<ProdutoAlimenticio> estoque = new ArrayList<>();

        // Mensagens iniciais para o utilizador.
        System.out.println("--- Sistema de Gerenciamento de Alimentos Iniciado ---");
        System.out.println("O estoque está vazio. Use a opção 4 para adicionar produtos.");

        // Loop principal do programa, que exibe o menu repetidamente até que o utilizador escolha sair.
        while (true) {
            // Exibe o menu de opções.
            System.out.println("\n===== MENU GERENCIADOR DE ALIMENTOS =====");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Calcular compra (demonstra SOBRECARGA específica)");
            System.out.println("3. Modificar preço (demonstra ENCAPSULAMENTO)");
            System.out.println("4. Adicionar novo produto (demonstra CONSTRUTORES)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            // Tenta ler a opção do utilizador e converte para um número inteiro.
            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Se o utilizador não digitar um número, exibe uma mensagem de erro e volta ao início do loop.
                System.out.println("Opção inválida. Por favor, digite um número.");
                continue;
            }

            // Estrutura 'switch' para executar a ação correspondente à opção escolhida.
            switch (opcao) {
                // Caso 1: Listar todos os produtos no estoque.
                case 1:
                    System.out.println("\n--- LISTA DE PRODUTOS NO ESTOQUE ---");
                    if (estoque.isEmpty()){
                        System.out.println("O estoque está vazio.");
                    } else {
                        // Percorre cada 'produto' na lista 'estoque'.
                        for (ProdutoAlimenticio produto : estoque) {
                            System.out.println("--------------------");
                            // Chama o método 'exibirInformacoes()'. Graças ao polimorfismo,
                            // o Java executa a versão correta do método (de Perecivel ou naoPerecivel).
                            System.out.println(produto.exibirInformacoes());
                        }
                        System.out.println("--------------------");
                    }
                    break; // Finaliza o case 1.

                // Caso 2: Calcular o preço final de um produto, demonstrando a sobrecarga de métodos.
                case 2:
                    if (estoque.isEmpty()){
                        System.out.println("O estoque está vazio. Adicione um produto primeiro.");
                        break;
                    }
                    // Mostra a lista de produtos para o utilizador escolher.
                    System.out.println("\n--- QUAL PRODUTO DESEJA CALCULAR? ---");
                    for (int i = 0; i < estoque.size(); i++) {
                        System.out.println(i + " - " + estoque.get(i).getNome() + " (Preço: R$" + String.format("%.2f", estoque.get(i).getPreco()) + ")");
                    }
                    System.out.print("Digite o número do produto: ");
                    int produtoIndex = Integer.parseInt(scanner.nextLine());

                    // Valida se o índice escolhido pelo utilizador é válido.
                    if (produtoIndex < 0 || produtoIndex >= estoque.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    ProdutoAlimenticio produtoSelecionado = estoque.get(produtoIndex);
                    System.out.println("Produto selecionado: " + produtoSelecionado.getNome());

                    // Verifica se o objeto é do tipo 'Perecivel' para mostrar as opções de cálculo corretas.
                    if (produtoSelecionado instanceof Perecivel) {
                        Perecivel perecivel = (Perecivel) produtoSelecionado; // Faz o "cast" para o tipo específico.
                        System.out.println("Opções de cálculo para Perecível:");
                        System.out.println("1. Com desconto por vencimento próximo");
                        System.out.println("2. Com taxa e multa por estado danificado");
                        System.out.print("Escolha a opção de cálculo: ");
                        int calcOpcao = Integer.parseInt(scanner.nextLine());
                        double precoOriginal = perecivel.getPreco();
                        double precoFinal;

                        if (calcOpcao == 1) {
                            System.out.print("Deseja ATIVAR a verificação de vencimento? (true/false): ");
                            boolean ativar = Boolean.parseBoolean(scanner.nextLine());
                            // Chama o método sobrecarregado que lida com vencimento.
                            precoFinal = perecivel.calcularCompra(0.10, ativar);
                            
                            if (precoFinal != precoOriginal) {
                                System.out.println("CONDIÇÃO ATINGIDA! Desconto por vencimento próximo foi aplicado.");
                            } else {
                                System.out.println("CONDIÇÃO NÃO ATINGIDA. Nenhum desconto especial foi aplicado.");
                            }
                            System.out.println("Preço final: R$" + String.format("%.2f", precoFinal));
                        
                        } else if (calcOpcao == 2) {
                            System.out.print("Deseja ATIVAR a verificação de multa por estado danificado? (true/false): ");
                            boolean ativar = Boolean.parseBoolean(scanner.nextLine());
                            // Chama o outro método sobrecarregado que lida com multas.
                            precoFinal = perecivel.calcularCompra(0.05, 0.02, ativar);
                            
                            if (precoFinal != precoOriginal) {
                                System.out.println("CONDIÇÃO ATINGIDA! Multa por estado danificado foi aplicada.");
                            } else {
                                System.out.println("CONDIÇÃO NÃO ATINGIDA. Nenhuma multa especial foi aplicada.");
                            }
                            System.out.println("Preço final: R$" + String.format("%.2f", precoFinal));
                        
                        } else {
                            System.out.println("Opção de cálculo inválida.");
                        }

                    } else if (produtoSelecionado instanceof naoPerecivel) {
                        naoPerecivel naoPerecivel = (naoPerecivel) produtoSelecionado;
                        // Lógica similar para produtos não perecíveis.
                        System.out.println("Opções de cálculo para Não Perecível:");
                        System.out.println("1. Com desconto por embalagem danificada");
                        System.out.println("2. Com taxa por tamanho grande");
                        System.out.print("Escolha a opção de cálculo: ");
                        int calcOpcao = Integer.parseInt(scanner.nextLine());
                        double precoOriginal = naoPerecivel.getPreco();
                        double precoFinal;

                        if (calcOpcao == 1) {
                            System.out.print("Deseja ATIVAR a verificação de desconto por embalagem? (true/false): ");
                            boolean ativar = Boolean.parseBoolean(scanner.nextLine());
                            precoFinal = naoPerecivel.calcularCompra(0.05, ativar);
                            
                            if (precoFinal != precoOriginal) {
                                System.out.println("CONDIÇÃO ATINGIDA! Desconto por embalagem danificada foi aplicado.");
                            } else {
                                System.out.println("CONDIÇÃO NÃO ATINGIDA. Nenhum desconto especial foi aplicado.");
                            }
                            System.out.println("Preço final: R$" + String.format("%.2f", precoFinal));
                        
                        } else if (calcOpcao == 2) {
                            System.out.print("Deseja ATIVAR a verificação de taxa por tamanho? (true/false): ");
                            boolean ativar = Boolean.parseBoolean(scanner.nextLine());
                            precoFinal = naoPerecivel.calcularCompra(ativar);
                            
                            if (precoFinal != precoOriginal) {
                                System.out.println("CONDIÇÃO ATINGIDA! Taxa por tamanho grande foi aplicada.");
                            } else {
                                System.out.println("CONDIÇÃO NÃO ATINGIDA. Nenhuma taxa especial foi aplicada.");
                            }
                            System.out.println("Preço final: R$" + String.format("%.2f", precoFinal));
                        
                        } else {
                            System.out.println("Opção de cálculo inválida.");
                        }
                    }
                    break;
                
                // Caso 3: Modificar o preço base de um produto.
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
                    System.out.println("Preço atual do " + produtoParaModificar.getNome() + ": R$" + String.format("%.2f", produtoParaModificar.getPreco()));
                    System.out.print("Digite o novo preço: ");
                    double novoPreco = lerDouble(scanner);
                    // Utiliza o método 'setPreco()'. Isso demonstra o encapsulamento, pois a lógica de validação
                    // (que impede preços negativos) está dentro do setter, protegendo o objeto.
                    produtoParaModificar.setPreco(novoPreco);
                    System.out.println("Preço modificado com sucesso!");
                    break;

                // Caso 4: Adicionar um novo produto ao estoque.
                case 4:
                    System.out.println("\n--- ADICIONAR NOVO PRODUTO ---");
                    System.out.print("Qual o tipo de produto? (1 - Perecível / 2 - Não Perecível): ");
                    int tipoProduto = Integer.parseInt(scanner.nextLine());

                    // Lógica para adicionar um produto Perecível
                    if (tipoProduto == 1) {
                        System.out.print("Qual construtor usar? (1 - Completo / 2 - Simplificado / 3 - Padrão): ");
                        int construtorOpcao = Integer.parseInt(scanner.nextLine());

                        // Utiliza o construtor padrão (sem parâmetros)
                        if (construtorOpcao == 3) {
                            estoque.add(new Perecivel());
                            System.out.println("Produto Perecível com valores padrão adicionado com sucesso!");
                        } else {
                            // Pede os dados comuns aos outros construtores
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Ano de Fabricação: ");
                            int ano = Integer.parseInt(scanner.nextLine());
                            System.out.print("Classe (ex: Laticínio): ");
                            String classe = scanner.nextLine();
                            System.out.print("Preço: ");
                            double preco = lerDouble(scanner);

                            // Pede dados adicionais se for o construtor completo
                            if (construtorOpcao == 1) {
                                System.out.print("Data de Validade (YYYYMMDD): ");
                                int dataValidade = Integer.parseInt(scanner.nextLine());
                                System.out.print("Estado do produto (ex: Perfeito, Danificado): ");
                                String estado = scanner.nextLine();
                                System.out.print("Está quase vencendo? (true/false): ");
                                boolean quaseVencendo = Boolean.parseBoolean(scanner.nextLine());
                                // Chama o construtor completo
                                estoque.add(new Perecivel(nome, ano, classe, preco, dataValidade, estado, quaseVencendo));
                                System.out.println("Produto Perecível (completo) adicionado com sucesso!");
                            
                            // Utiliza o construtor simplificado
                            } else if (construtorOpcao == 2){
                                estoque.add(new Perecivel(nome, ano, classe, preco));
                                System.out.println("Produto Perecível (simplificado) adicionado com sucesso!");
                            
                            } else {
                                System.out.println("Opção de construtor inválida.");
                            }
                        }

                    } else if (tipoProduto == 2) {
                        // Lógica similar para adicionar um produto Não Perecível
                        System.out.print("Qual construtor usar? (1 - Completo / 2 - Simplificado / 3 - Padrão): ");
                        int construtorOpcao = Integer.parseInt(scanner.nextLine());

                        if (construtorOpcao == 3) {
                            estoque.add(new naoPerecivel());
                            System.out.println("Produto Não Perecível com valores padrão adicionado com sucesso!");
                        
                        } else {
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
                                System.out.print("Tamanho do Produto (ex: Pequeno, Medio, Grande): ");
                                String tamanho = scanner.nextLine();
                                System.out.print("Embalagem está perfeita? (true/false): ");
                                boolean embalagem = Boolean.parseBoolean(scanner.nextLine());
                                estoque.add(new naoPerecivel(nome, ano, classe, preco, metodo, tamanho, embalagem));
                                System.out.println("Produto Não Perecível (completo) adicionado com sucesso!");
                            
                            } else if (construtorOpcao == 2) {
                                estoque.add(new naoPerecivel(nome, ano, classe, preco));
                                System.out.println("Produto Não Perecível (simplificado) adicionado com sucesso!");
                            
                            } else {
                                System.out.println("Opção de construtor inválida.");
                            }
                        }
                    } else {
                        System.out.println("Tipo de produto inválido.");
                    }
                    break;

                // Caso 0: Sair do programa.
                case 0:
                    System.out.println("Saindo do programa...");
                    scanner.close(); // Fecha o scanner para libertar os recursos do sistema.
                    return; // Encerra o método main e, consequentemente, o programa.

                // 'default' é executado se o utilizador digitar um número que não corresponde a nenhum 'case'.
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    break;
            }
        }
    }
}