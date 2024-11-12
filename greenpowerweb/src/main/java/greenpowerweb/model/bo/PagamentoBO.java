package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import greenpowerweb.model.dao.PagamentoDAO;
import greenpowerweb.model.vo.PagamentoVO;

public class PagamentoBO {
	private PagamentoDAO pagamentoDAO;
	
	public PagamentoBO() throws ClassNotFoundException, SQLException{
		this.pagamentoDAO = new PagamentoDAO();
	}
	
	public void cadastrarPagamento(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
		pagamentoDAO.PagamentoDAO_INSERT(pagamento);
	}
	
	public void atualizarPedido(PagamentoVO pagamento) throws ClassNotFoundException, SQLException, IOException {
		pagamentoDAO.PagamentoDAO_ATUALIZAR(pagamento);
	}
	
	public void deletarPedido(int idPagamento) throws ClassNotFoundException, SQLException, IOException {
		pagamentoDAO.PagamentoDAO_DELETE(idPagamento);
	}
	
	public ArrayList<PagamentoVO> listarPedidos()  throws ClassNotFoundException, SQLException, IOException {
		return (ArrayList<PagamentoVO>) pagamentoDAO.PagamentoDAO_SELECTALL();
	}
}
