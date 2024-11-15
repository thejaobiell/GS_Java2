package greenpowerweb.model.vo;

import java.sql.Date;

public class PedidoVO {
    private int id_pedido;
    private String email_cliente;
    private Date data_pedido;
    private String status_pedido;
    private String status_pagamento;
    private double valor_total;

    public PedidoVO() {}

    public PedidoVO(int id_pedido, String email_cliente, Date data_pedido, String status_pedido, String status_pagamento, double valor_total) {
        this.id_pedido = id_pedido;
        this.email_cliente = email_cliente;
        this.data_pedido = data_pedido;
        this.status_pedido = status_pedido;
        this.status_pagamento = status_pagamento;
        this.valor_total = valor_total;
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

    public String getStatus_pagamento() {
        return status_pagamento;
    }

    public void setStatus_pagamento(String status_pagamento) {
        this.status_pagamento = status_pagamento;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    @Override
    public String toString() {
        return "Detalhes do Pedido: " +
               "ID Pedido = " + id_pedido +
               ", Email Cliente = '" + email_cliente + '\'' +
               ", Data Pedido = " + data_pedido +
               ", Status Pedido = '" + status_pedido + '\'' +
               ", Status Pagamento = '" + status_pagamento + '\'' +
               ", Valor Total = " + valor_total;
    }
    
    
    public String toStringEdit() {
        return "Detalhes do Pedido Atualizado: " +
               "ID Pedido = " + id_pedido +
               ", Status Pedido = '" + status_pedido + '\'' +
               ", Status Pagamento = '" + status_pagamento;
    }
}