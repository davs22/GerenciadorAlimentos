package com.example.model;

/**
 * Representa um produto alimentício não perecível, uma subclasse de ProdutoAlimenticio.
 * Esta classe herda os atributos básicos e adiciona características próprias
 * relacionadas à sua conservação, tamanho e embalagem.
 */
public class naoPerecivel extends ProdutoAlimenticio {

    // --- ATRIBUTOS ESPECÍFICOS DA SUBCLASSE ---
    private String metodoConservacao; // Como o produto deve ser armazenado (ex: "Local Seco", "Geladeira").
    private String tamanhoProduto;    // A dimensão do produto (ex: "Pequeno", "Grande").
    private Boolean embalagemPerfeita; // Indica se a embalagem está intacta (true) ou não (false).

    // --- CONSTRUTORES ---

    /**
     * Construtor padrão (sem parâmetros).
     * Cria um produto não perecível com valores default.
     * A chamada 'super()' invoca o construtor padrão da classe mãe (ProdutoAlimenticio).
     */
    public naoPerecivel() {
        super();
        this.metodoConservacao = "Geladeira";
        this.tamanhoProduto = "Pequeno";
        this.embalagemPerfeita = true;
    }

    /**
     * Construtor sobrecarregado (simplificado).
     * Recebe os dados essenciais e chama o construtor mais completo com valores padrão
     * para os atributos específicos de um produto não perecível.
     * Utiliza 'this(...)' para encadear a chamada a outro construtor na mesma classe.
     */
    public naoPerecivel(String nome, int anoFabricacao, String classe, double preco) {
        this(nome, anoFabricacao, classe, preco, "Frezzer", "Medio", true);
    }

    /**
     * Construtor sobrecarregado (completo).
     * Inicializa um objeto naoPerecivel com todos os seus atributos.
     */
    public naoPerecivel(String nome, int anoFabricacao, String classe, double preco, String metodoConservacao,
                      String tamanhoProduto, Boolean embalagemPerfeita) {
        // 'super(...)' chama o construtor sobrecarregado da superclasse para inicializar a parte base do objeto.
        super(nome, anoFabricacao, classe, preco);
        // Em seguida, inicializa os atributos específicos desta classe.
        this.metodoConservacao = metodoConservacao;
        this.tamanhoProduto = tamanhoProduto;
        this.embalagemPerfeita = embalagemPerfeita;
    }

    // --- SOBRESCRITA DE MÉTODO (@Override) ---

    /**
     * Sobrescreve o método 'exibirInformacoes' da superclasse para incluir
     * os detalhes específicos de um produto não perecível.
     * @return Uma String formatada com todas as informações do produto.
     */
    @Override
    public String exibirInformacoes() {
        // Reutiliza o método da superclasse para não repetir código.
        String informacoesBase = super.exibirInformacoes();
        // Adiciona as informações desta classe ao resultado.
        return informacoesBase + "\nMetódo de conservação: " + metodoConservacao + "\nTamanho do produto: "
               + tamanhoProduto + "\nEmbalagem perfeita: " + embalagemPerfeita;
    }

    // --- MÉTODOS SOBRECARREGADOS ---

    /**
     * Calcula o preço com desconto SE a verificação for ativada E a embalagem não estiver perfeita.
     * @param desconto O desconto base a ser aplicado.
     * @param descontoPorEmbalagem Flag (true/false) que funciona como um "interruptor" para ativar a lógica.
     * @return O preço final com desconto se a condição for atendida; caso contrário, o preço original.
     */
    public double calcularCompra(double desconto, boolean descontoPorEmbalagem) {
        // A condição exige que o flag seja verdadeiro E que a embalagem não esteja perfeita.
        if (descontoPorEmbalagem && !this.embalagemPerfeita) { // !this.embalagemPerfeita é o mesmo que this.embalagemPerfeita == false
            // Aplica o desconto base recebido como parâmetro.
            double precoFinal = super.calcularCompra(desconto);
            // Aplica o desconto adicional de 5% por embalagem danificada.
            precoFinal *= 0.95;
            return precoFinal;
        }
        // Se a condição não for atendida, retorna o preço original sem alterações.
        return this.getPreco();
    }

    /**
     * Calcula o preço com taxa SE a verificação for ativada E o produto for "grande".
     * @param taxaTamanho Flag (true/false) que ativa a lógica de taxa.
     * @return O preço final com taxa se a condição for atendida; caso contrário, o preço original.
     */
    public double calcularCompra(boolean taxaTamanho) {
        // A condição exige que o flag seja verdadeiro E que o produto seja grande.
        if (taxaTamanho && "grande".equalsIgnoreCase(tamanhoProduto)) {
            // Pega o preço base do produto.
            double precoFinal = super.calcularCompra();
            // Aplica a taxa de 20% por ser um produto grande.
            precoFinal *= 1.20;
            return precoFinal;
        }
        // Se a condição não for atendida, retorna o preço original sem alterações.
        return this.getPreco();
    }

    // --- GETTERS E SETTERS ---
    // Métodos para acesso e modificação controlada dos atributos privados (encapsulamento).

    public String getMetodoConservacao() {
        return metodoConservacao;
    }

    public void setMetodoConservacao(String metodoConservacao) {
        this.metodoConservacao = metodoConservacao;
    }

    public String getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(String tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public Boolean getEmbalagemPerfeita() {
        return embalagemPerfeita;
    }

    public void setEmbalagemPerfeita(Boolean embalagemPerfeita) {
        this.embalagemPerfeita = embalagemPerfeita;
    }
}