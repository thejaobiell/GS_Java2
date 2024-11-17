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
        pedidoDAO.PedidoDAO_INSERT(pedido);
        pedidoDAO.PedidoDAO_ATUALIZARValorTotal(pedido.getId_pedido());
    }

    public void atualizarValorTotal(int idPedido) throws SQLException {
        pedidoDAO.PedidoDAO_ATUALIZARValorTotal(idPedido);
    }

    public void atualizarPedido(int idPedido, String statusPedido, String statusPagamento) throws SQLException {
        pedidoDAO.PedidoDAO_ATUALIZARPedido(idPedido, statusPedido, statusPagamento);
    }

    public void deletarPedido(int idPedido) throws ClassNotFoundException, SQLException, IOException {
        pedidoDAO.PedidoDAO_DELETE(idPedido);
    }

    public List<PedidoVO> listarPedidos() throws ClassNotFoundException, SQLException, IOException {
        return pedidoDAO.PedidoDAO_SELECTALL();
    }
}
