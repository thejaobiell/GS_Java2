package greenpowerweb.model.vo;

public class ProdutoVO {
	
	private int id_produto; 
    private String nome_produto;
	private String descricao_produto;
	private double preco_produto;
	private String tipo_produto;
	
	public ProdutoVO(){}

	public ProdutoVO(int id_produto, String nome_produto, String descricao_produto, double preco_produto, String tipo_produto) {
		this.id_produto = id_produto;
		this.nome_produto = nome_produto;
		this.descricao_produto = descricao_produto;
		this.preco_produto = preco_produto;
		this.tipo_produto = tipo_produto;
	}
	
	
	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public double getPreco_produto() {
		return preco_produto;
	}

	public void setPreco_produto(double preco_produto) {
		this.preco_produto = preco_produto;
	}

	public String getTipo_produto() {
		return tipo_produto;
	}

	public void setTipo_produto(String tipo_produto) {
		this.tipo_produto = tipo_produto;
	}

	@Override
	public String toString() {
	    return "Detalhes do Produto:\n" +
	           "ID do Produto: " + id_produto + "\n" +
	           "Nome do Produto: " + nome_produto + "\n" +
	           "Descrição do Produto: " + descricao_produto + "\n" +
	           "Preço do Produto: R$ " + String.format("%.2f", preco_produto) + "\n" +
	           "Tipo do Produto: " + tipo_produto;
	}
}
