package greenpowerweb.model.vo;

import java.sql.Date;

public class PedidoVO {
	private int id_pedido;
	private String email_cliente;
	private Date data_pedido;
	private String status_pedido;
	
	public PedidoVO() {}
	
	public PedidoVO(int id_pedido, String email_cliente, Date data_pedido, String status_pedido) {
		this.id_pedido = id_pedido;
		this.email_cliente = email_cliente;
		this.data_pedido = data_pedido;
		this.status_pedido = status_pedido;
	}

	public int getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}
	public String getEmail_cliente() {
		return email_cliente;
	}
	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	public String getStatus_pedido() {
		return status_pedido;
	}
	public void setStatus_pedido(String status_pedido) {
		this.status_pedido = status_pedido;
	}

	@Override
	public String toString() {
	    return String.format(
	        "Detalhes do Pedido:\n" +
	        "ID do Pedido: %d\n" +
	        "Email do Cliente: %s\n" +
	        "Data do Pedido: %s\n" +
	        "Status do Pedido: %s\n", 
	        id_pedido, email_cliente, data_pedido, status_pedido
	    );
	}
}