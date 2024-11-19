package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import greenpowerweb.model.dao.PedidoDAO;
import greenpowerweb.model.vo.PedidoVO;

public class PedidoBO {
    private PedidoDAO pedidoDAO;

    public PedidoBO() throws ClassNotFoundException, SQLException {
        this.pedidoDAO = new PedidoDAO();
    }

    public void cadastrarPedido(PedidoVO pedido) throws ClassNotFoundException, SQLException, IOException {
        pedidoDAO.pedidoDaoInsert(pedido);
    }

    public void atualizarPedido(int idPedido, String statusPedido, String statusPagamento) throws SQLException {
        pedidoDAO.pedidoDaoAtualizarPedido(idPedido, statusPedido, statusPagamento);
    }

    public void deletarPedido(int idPedido) throws ClassNotFoundException, SQLException, IOException {
        pedidoDAO.pedidoDaoDelete(idPedido);
    }

    public List<PedidoVO> listarPedidos() throws ClassNotFoundException, SQLException, IOException {
        return pedidoDAO.pedidoDaoSelectAll();
    }
}
