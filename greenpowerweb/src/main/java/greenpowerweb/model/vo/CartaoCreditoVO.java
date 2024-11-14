package greenpowerweb.model.vo;

import java.sql.Date;

public class CartaoCreditoVO {
    private int id_cartao;
    private String email_cliente;
    private String numero_cartao;
    private Date validade_cartao;
    private String codigo_seguranca;
    private String bandeira;

    public CartaoCreditoVO() {}

    public CartaoCreditoVO(int id_cartao, String email_cliente, String numero_cartao, Date validade_cartao, String codigo_seguranca, String bandeira) {
        this.id_cartao = id_cartao;
        this.email_cliente = email_cliente;
        this.numero_cartao = numero_cartao;
        this.validade_cartao = validade_cartao;
        this.codigo_seguranca = codigo_seguranca;
        this.bandeira = bandeira;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public Date getValidade_cartao() {
        return validade_cartao;
    }

    public void setValidade_cartao(Date validade_cartao) {
        this.validade_cartao = validade_cartao;
    }

    public String getCodigo_seguranca() {
        return codigo_seguranca;
    }

    public void setCodigo_seguranca(String codigo_seguranca) {
        this.codigo_seguranca = codigo_seguranca;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    @Override
    public String toString() {
        return "Detalhes do Cartão de Crédito:\n" +
               "ID do Cartão: " + id_cartao + "\n" +
               "Email do Cliente: " + email_cliente + "\n" +
               "Número do Cartão: " + numero_cartao + "\n" +
               "Validade do Cartão: " + validade_cartao + "\n" +
               "Código de Segurança: " + codigo_seguranca + "\n" +
               "Bandeira: " + bandeira;
    }
    
    public String toStringEdit() {
        return "Detalhes da Edição dos dados do Cartão de Crédito ( " + numero_cartao +" ):\n" +
               "Nova Validade do Cartão: " + validade_cartao + "\n" +
               "Novo Código de Segurança: " + codigo_seguranca + "\n";
    }
}
