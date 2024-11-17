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
            throw new IllegalArgumentException("A quantidade de parcelas não pode ser menor que 1 ou maior que 10");
        }
        
        if (pagamento.getForma_pagamento() == null || pagamento.getForma_pagamento().isEmpty()) {
            throw new IllegalArgumentException("Forma de pagamento não pode ser vazia");
        }
        
        pagamentoDAO.pagamentoDaoInsert(pagamento);
    }
    
    public void atualizarPagamento(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamentoDAO.pagamentoDaoAtualizar(pagamento);
        pagamentoDAO.atualizarStatusPedido(pagamento.getId_pedido(), pagamento.getStatus_pagamento());
    }
    
    public void deletarPagamento(int idPagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamentoDAO.pagamentoDaoDelete(idPagamento);
    }
    
    public List<PagamentoVO> listarPagamentos() throws ClassNotFoundException, SQLException, IOException {
        return pagamentoDAO.pagamentoDaoSelectAll();
    }
}
