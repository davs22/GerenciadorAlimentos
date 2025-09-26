package com.example.model;

/**
 * Representa a classe base (superclasse) para todos os produtos alimentícios.
 * Contém os atributos e comportamentos comuns a qualquer alimento no sistema.
 */
public class ProdutoAlimenticio {

    // --- ATRIBUTOS ---
    // Atributos privados garantem o encapsulamento. Eles só podem ser acessados
    // e modificados através dos métodos getters e setters.

    private String nome;          // Nome do produto.
    private int anoFabricacao;    // Ano em que o produto foi fabricado.
    private String classe;        // Categoria do produto (ex: Fruta, Grão, Laticínio).
    private double preco;         // Preço base do produto.

    // --- CONSTRUTORES ---

    /**
     * Construtor padrão (sem parâmetros).
     * Cria um objeto "ProdutoAlimenticio" com valores default pré-definidos.
     * Útil para criar uma instância básica do objeto rapidamente.
     */
    public ProdutoAlimenticio() {
        this.nome = "Banana";
        // Usa o setter para garantir que o valor default passe pela validação.
        this.setAnoFabricacao(2025);
        this.classe = "Fruta";
        // Usa o setter para garantir a validação do preço.
        this.setPreco(1.90);
    }

    /**
     * Construtor sobrecarregado.
     * Permite criar um objeto "ProdutoAlimenticio" fornecendo todos os valores
     * dos atributos no momento da criação.
     * @param nome Nome do produto.
     * @param anoFabricacao Ano de fabricação do produto.
     * @param classe Categoria do produto.
     * @param preco Preço do produto.
     */
    public ProdutoAlimenticio(String nome, int anoFabricacao, String classe, double preco) {
        this.nome = nome;
        // Usa os setters nos construtores para centralizar e reutilizar a lógica de validação.
        this.setAnoFabricacao(anoFabricacao);
        this.classe = classe;
        this.setPreco(preco);
    }

    // --- MÉTODOS DE ENCAPSULAMENTO (GETTERS E SETTERS) ---

    // Getter para o atributo 'nome'. Retorna o nome do produto.
    public String getNome() {
        return nome;
    }

    // Setter para o atributo 'nome'. Permite alterar o nome do produto.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o atributo 'anoFabricacao'.
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Setter para o atributo 'anoFabricacao'.
     * Inclui uma validação para garantir que o ano não seja um valor negativo.
     * @param anoFabricacao O novo ano de fabricação.
     */
    public void setAnoFabricacao(int anoFabricacao) {
        if (anoFabricacao >= 0) {
            this.anoFabricacao = anoFabricacao; // Só atribui se o valor for válido.
        } else {
            // Informa o utilizador sobre o erro se a validação falhar.
            System.out.println("O ano de fabricação não pode ser negativo");
        }
    }

    // Getter para o atributo 'classe'.
    public String getClasse() {
        return classe;
    }

    // Setter para o atributo 'classe'.
    public void setClasse(String classe) {
        this.classe = classe;
    }

    // Getter para o atributo 'preco'.
    public double getPreco() {
        return preco;
    }

    /**
     * Setter para o atributo 'preco'.
     * Inclui uma validação para garantir que o preço não seja negativo.
     * @param preco O novo preço do produto.
     */
    public void setPreco(double preco) {
        if (preco >= 0.0) {
            this.preco = preco; // Atribui o novo preço se for válido.
        } else {
            // Informa sobre o erro se o preço for inválido.
            System.out.println("O valor do preco não pode ser negativo");
        }
    }

    // --- MÉTODOS DE COMPORTAMENTO ---

    /**
     * Retorna uma String formatada com as informações básicas do produto.
     * Este método será sobrescrito (@Override) nas subclasses para adicionar informações específicas.
     * @return Uma String com os detalhes do produto.
     */
    public String exibirInformacoes() {
        // Formata o preço para sempre exibir duas casas decimais (ex: R$1,90)
        return "\nAlimento: " + nome + "\nData de fabricação: " + anoFabricacao + "\nClasse: " +
               classe + "\nPreço: " + String.format("R$%.2f", preco);
    }


    // --- MÉTODOS SOBRECARREGADOS para calcular o preço da compra ---
    // A sobrecarga (overloading) permite ter vários métodos com o mesmo nome,
    // desde que tenham assinaturas (parâmetros) diferentes.

    /**
     * Calcula o preço da compra sem nenhum adicional.
     * @return o preço base.
     */
    public double calcularCompra() {
        return preco;
    }

    /**
     * SOBRECARGA 1: Calcula o preço da compra com um desconto percentual.
     * @param desconto O valor do desconto (ex: 0.10 para 10%).
     * @return o preço com desconto.
     */
    public double calcularCompra(double desconto) {
        return preco * (1 - desconto);
    }

    /**
     * SOBRECARGA 2: Calcula o preço da compra com desconto e uma taxa adicional.
     * @param desconto O valor do desconto.
     * @param taxa O valor da taxa (ex: 0.05 para 5%).
     * @return o preço final com desconto e taxa.
     */
    public double calcularCompra(double desconto, double taxa) {
        double precoComDesconto = preco * (1 - desconto);
        return precoComDesconto * (1 + taxa);
    }
}