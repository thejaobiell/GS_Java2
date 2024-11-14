package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import greenpowerweb.model.dao.PedidoDAO;
import greenpowerweb.model.vo.PedidoVO;

public class PedidoBO {
    private PedidoDAO pedidoDAO;
    
    public PedidoBO() throws ClassNotFoundException, SQLException {
        this.pedidoDAO = new PedidoDAO();
    }

    private void validarPedido(PedidoVO pedido) throws IllegalArgumentException {
        if (pedido.getValor_total() < 0) {
            throw new IllegalArgumentException("O valor total do pedido não pode ser negativo.");
        }
    }
    
    public void cadastrarPedido(PedidoVO pedido) throws ClassNotFoundException, SQLException, IOException {
        validarPedido(pedido);
        pedidoDAO.PedidoDAO_INSERT(pedido);
    }
    
    public void atualizarPedido(PedidoVO pedido) throws ClassNotFoundException, SQLException, IOException {
        validarPedido(pedido);
        pedidoDAO.PedidoDAO_ATUALIZAR(pedido);
    }
    
    public void deletarPedido(int idPedido) throws ClassNotFoundException, SQLException, IOException {
        pedidoDAO.PedidoDAO_DELETE(idPedido);
    }
    
    public ArrayList<PedidoVO> listarPedidos() throws ClassNotFoundException, SQLException, IOException {
        return (ArrayList<PedidoVO>) pedidoDAO.PedidoDAO_SELECTALL();
    }
}
