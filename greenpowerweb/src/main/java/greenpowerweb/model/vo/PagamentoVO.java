package greenpowerweb.model.vo;

import java.sql.Date;

public class PagamentoVO {
    private int id_pagamento;  
    private String email_cliente; 
    private String forma_pagamento; 
    private double valor_pagamento;
    private Date data_pagamento;
    private String status_pagamento;
    
    public PagamentoVO(){}

	public PagamentoVO(int id_pagamento, String email_cliente, String forma_pagamento, double valor_pagamento,
			Date data_pagamento, String status_pagamento) {
		this.id_pagamento = id_pagamento;
		this.email_cliente = email_cliente;
		this.forma_pagamento = forma_pagamento;
		this.valor_pagamento = valor_pagamento;
		this.data_pagamento = data_pagamento;
		this.status_pagamento = status_pagamento;
	}

	public int getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public double getValor_pagamento() {
		return valor_pagamento;
	}

	public void setValor_pagamento(double valor_pagamento) {
		this.valor_pagamento = valor_pagamento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public String getStatus_pagamento() {
		return status_pagamento;
	}

	public void setStatus_pagamento(String status_pagamento) {
		this.status_pagamento = status_pagamento;
	}

	@Override
	public String toString() {
	    return "Detalhes do Pagamento:\n" +
	            "ID do Pagamento: " + id_pagamento + "\n" +
	            "Email do Cliente: " + email_cliente + "\n" +
	            "Forma de Pagamento: " + forma_pagamento + "\n" +
	            "Valor do Pagamento: R$ " + String.format("%.2f", valor_pagamento)+ "\n" +
	            "Data do Pagamento: " + data_pagamento + "\n" +
	            "Status do Pagamento: " + status_pagamento;
	 }
}