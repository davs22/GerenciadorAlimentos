package com.example.model;

/**
 * Representa um produto alimentício perecível, uma subclasse de ProdutoAlimenticio.
 * Esta classe herda os atributos básicos e adiciona características específicas
 * como data de validade, estado físico e se está próximo do vencimento.
 */
public class Perecivel extends ProdutoAlimenticio {

    // --- ATRIBUTOS ESPECÍFICOS DA SUBCLASSE ---
    private int dataValidade;     // Data de validade no formato YYYYMMDD.
    private String estadoPoduto;  // Estado físico do produto (ex: "Perfeito", "Danificado").
    private Boolean quaseVencendo; // Flag que indica se o produto está próximo da data de validade.

    // --- CONSTRUTORES ---

    /**
     * Construtor padrão (sem parâmetros).
     * Inicializa um produto perecível com valores default.
     * A chamada 'super()' invoca o construtor padrão da classe mãe (ProdutoAlimenticio).
     */
    public Perecivel() {
        super(); // Chama o construtor ProdutoAlimenticio()
        this.dataValidade = 2027;
        this.estadoPoduto = "Perfeito";
        this.quaseVencendo = false;
    }

    /**
     * Construtor sobrecarregado (simplificado).
     * Recebe os dados essenciais e chama o construtor mais completo com valores padrão
     * para os atributos específicos de um produto perecível.
     * Utiliza 'this(...)' para encadear a chamada a outro construtor na mesma classe.
     */
    public Perecivel(String nome, int anoFabricacao, String classe, double preco) {
        this(nome, anoFabricacao, classe, preco, 2025, "Perfeito", false);
    }

    /**
     * Construtor sobrecarregado (completo).
     * Inicializa um objeto Perecivel com todos os seus atributos, tanto os herdados quanto os próprios.
     */
    public Perecivel(String nome, int anoFabricacao, String classe, double preco, int dataValidade, String estadoPoduto, Boolean quaseVencendo) {
        // 'super(...)' chama o construtor sobrecarregado da superclasse para inicializar a parte base do objeto.
        super(nome, anoFabricacao, classe, preco);
        // Em seguida, inicializa os atributos específicos desta classe.
        this.dataValidade = dataValidade;
        this.estadoPoduto = estadoPoduto;
        this.quaseVencendo = quaseVencendo;
    }

    // --- SOBRESCRITA DE MÉTODO (@Override) ---

    /**
     * Sobrescreve o método 'exibirInformacoes' da superclasse para adicionar
     * os detalhes específicos de um produto perecível.
     * @return Uma String formatada com todas as informações do produto.
     */
    @Override
    public String exibirInformacoes() {
        // Reutiliza o método da superclasse para não repetir código.
        String informacoesBase = super.exibirInformacoes();
        // Adiciona as informações desta classe ao resultado.
        return informacoesBase + "\nData de validade: " + dataValidade + "\nEstado do produto: " + estadoPoduto
                + "\nEstá quase vencendo? " + quaseVencendo;
    }

    // --- MÉTODOS SOBRECARREGADOS ---

    /**
     * Calcula o preço com desconto SE a verificação for ativada E o produto estiver quase a vencer.
     * @param desconto O desconto base a ser aplicado.
     * @param vencimentoProximo Flag (true/false) que funciona como um "interruptor" para ativar a lógica.
     * @return O preço final com desconto se a condição for atendida; caso contrário, o preço original.
     */
    public double calcularCompra(double desconto, boolean vencimentoProximo) {
        if (vencimentoProximo && this.quaseVencendo) {
            double precoFinal = super.calcularCompra(desconto);
            precoFinal *= 0.95; // Aplica um desconto adicional de 5%.
            return precoFinal;
        }
        // Se a condição não for atendida, retorna o preço original sem alterações.
        return this.getPreco();
    }

    /**
     * Calcula o preço com multa SE a verificação for ativada E o produto estiver danificado.
     * @param desconto Desconto base.
     * @param taxa Taxa base.
     * @param aplicarMultaPorEstado Flag (true/false) que ativa a lógica de multa.
     * @return O preço final com multa se a condição for atendida; caso contrário, o preço original.
     */
    public double calcularCompra(double desconto, double taxa, boolean aplicarMultaPorEstado) {
        if (aplicarMultaPorEstado && "danificado".equalsIgnoreCase(estadoPoduto)) {
            double precoFinal = super.calcularCompra(desconto, taxa);
            precoFinal *= 1.03; // Aplica uma multa de 3%.
            return precoFinal;
        }
        // Se a condição não for atendida, retorna o preço original sem alterações.
        return this.getPreco();
    }

    // --- GETTERS E SETTERS ---
    // Métodos para acesso e modificação controlada dos atributos privados (encapsulamento).

    public int getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(int dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getEstadoPoduto() {
        return estadoPoduto;
    }

    public void setEstadoPoduto(String estadoPoduto) {
        this.estadoPoduto = estadoPoduto;
    }

    public Boolean getQuaseVencendo() {
        return quaseVencendo;
    }

    public void setQuaseVencendo(Boolean quaseVencendo) {
        this.quaseVencendo = quaseVencendo;
    }
}