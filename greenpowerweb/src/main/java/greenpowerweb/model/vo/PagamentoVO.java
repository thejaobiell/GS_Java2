package greenpowerweb.model.vo;

import java.sql.Date;

public class PagamentoVO {
    private int id_pagamento;
    private int id_pedido;  
    private String id_transacao;
    private String forma_pagamento;
    private String status_pagamento;
    private Date data_pagamento;
    private double valor_pagamento;
    private int qtd_parcelas;

    public PagamentoVO () {}
    
	public PagamentoVO(int id_pagamento, int id_pedido, String id_transacao, String forma_pagamento, String status_pagamento, Date data_pagamento, double valor_pagamento, int qtd_parcelas) {
		this.id_pagamento = id_pagamento;
		this.id_pedido = id_pedido;
		this.id_transacao = id_transacao;
		this.forma_pagamento = forma_pagamento;
		this.status_pagamento = status_pagamento;
		this.data_pagamento = data_pagamento;
		this.valor_pagamento = valor_pagamento;
		this.qtd_parcelas = qtd_parcelas;
	}

	public int getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getId_transacao() {
		return id_transacao;
	}

	public void setId_transacao(String id_transacao) {
		this.id_transacao = id_transacao;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public String getStatus_pagamento() {
		return status_pagamento;
	}

	public void setStatus_pagamento(String status_pagamento) {
		this.status_pagamento = status_pagamento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public double getValor_pagamento() {
		return valor_pagamento;
	}

	public void setValor_pagamento(double valor_pagamento) {
		this.valor_pagamento = valor_pagamento;
	}

	public int getQtd_parcelas() {
		return qtd_parcelas;
	}

	public void setQtd_parcelas(int qtd_parcelas) {
		this.qtd_parcelas = qtd_parcelas;
	}

	@Override
	public String toString() {
	    return "Detalhes do Pagamento: {" + 
	           "\n  ID do Pagamento: " + id_pagamento + "," + 
	           "\n  ID do Pedido associado: " + id_pedido + "," + 
	           "\n  ID da Transação: " + id_transacao + "," + 
	           "\n  Forma de Pagamento: " + forma_pagamento + "," + 
	           "\n  Status do Pagamento: " + status_pagamento + "," + 
	           "\n  Data de Pagamento: " + data_pagamento + "," + 
	           "\n  Valor do Pagamento: R$ " + String.format("%.2f", valor_pagamento) + "," + 
	           "\n  Quantidade de Parcelas: " + qtd_parcelas + 
	           "\n}";
	}
}