package greenpowerweb.model.vo;

public class ItemCompradoVO {
    private int id_item;
    private int id_pedido;
    private int id_produto;
    private int quantidade;
    private double preco_unitario;
    private double preco_final;

    public ItemCompradoVO() {}

	public ItemCompradoVO(int id_item, int id_pedido, int id_produto, int quantidade, double preco_unitario, double preco_final) {
		this.id_item = id_item;
		this.id_pedido = id_pedido;
		this.id_produto = id_produto;
		this.quantidade = quantidade;
		this.preco_unitario = preco_unitario;
		this.preco_final = preco_final;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public double getPreco_final() {
		return preco_final;
	}

	public void setPreco_final(double preco_final) {
		this.preco_final = preco_final;
	}

	@Override
	public String toString() {
		return "ItemCompradoVO [id_item=" + id_item + ", id_pedido=" + id_pedido + ", id_produto=" + id_produto
				+ ", quantidade=" + quantidade + ", preco_unitario=" + preco_unitario + ", preco_final=" + preco_final
				+ "]";
	}
}
