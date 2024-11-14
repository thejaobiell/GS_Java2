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
        pagamentoDAO.PagamentoDAO_INSERT(pagamento);
    }
    
    public void atualizarPagamentoPix(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamento.setId_cartao(null);
        pagamento.setNumero_boleto(null);
        pagamentoDAO.PagamentoDAO_ATUALIZAR(pagamento);
    }
    
    public void atualizarPagamentoCartao(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamento.setChave_pix(null);
        pagamento.setNumero_boleto(null);
        pagamentoDAO.PagamentoDAO_ATUALIZAR(pagamento);
    }

    public void atualizarPagamentoBoleto(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamento.setId_cartao(null);
        pagamento.setChave_pix(null);
        pagamentoDAO.PagamentoDAO_ATUALIZAR(pagamento);
    }
    
    public void deletarPagamento(int idPagamento) throws ClassNotFoundException, SQLException, IOException {
        pagamentoDAO.PagamentoDAO_DELETE(idPagamento);
    }
    
    public List<PagamentoVO> listarPagamentos() throws ClassNotFoundException, SQLException, IOException {
        return pagamentoDAO.PagamentoDAO_SELECTALL();
    }
}
