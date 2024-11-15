package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import greenpowerweb.model.dao.PagamentoDAO;
import greenpowerweb.model.vo.PagamentoVO;

public class PagamentoBO {
    private PagamentoDAO pagamentoDAO;
    
    public PagamentoBO() throws ClassNotFoundException, SQLException {
        this.pagamentoDAO = new PagamentoDAO();
    }
    
    public void cadastrarPagamento(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        if (pagamento.getQtd_parcelas() <= 0 || pagamento.getQtd_parcelas() > 10) {
            throw new IllegalArgumentException("A quantidade de parcelas não pode ser menor que 0 ou maior que 10");
        }
    	
        switch (pagamento.getForma_pagamento()) {
            case "Cartão de Crédito":
                pagamento.setChave_pix(null);
                pagamento.setNumero_boleto(null);
                break;
            case "Pix":
                pagamento.setId_cartao(null);
                pagamento.setNumero_boleto(null);
                break;
            case "Boleto":
                pagamento.setId_cartao(null);
                pagamento.setChave_pix(null);
                break;
            default:
                throw new IllegalArgumentException("Forma de pagamento inválida");
        }
        pagamentoDAO.PagamentoDAO_INSERT(pagamento);
    }
    
    public void atualizarPagamento(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamentoDAO.PagamentoDAO_ATUALIZAR(pagamento);
        pagamentoDAO.atualizarStatusPedido(pagamento.getId_pedido(), pagamento.getStatus_pagamento());
    }
    
    public void deletarPagamento(int idPagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamentoDAO.PagamentoDAO_DELETE(idPagamento);
    }
    
    public List<PagamentoVO> listarPagamentos() throws ClassNotFoundException, SQLException, IOException {
        return pagamentoDAO.PagamentoDAO_SELECTALL();
    }
}
