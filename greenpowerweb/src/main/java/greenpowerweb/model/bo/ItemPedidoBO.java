package greenpowerweb.model.bo;

import java.sql.SQLException;
import java.util.List;

import greenpowerweb.model.dao.ItemPedidoDAO;
import greenpowerweb.model.vo.ItemPedidoVO;

public class ItemPedidoBO {
    private ItemPedidoDAO itemPedidoDAO;

    public ItemPedidoBO() throws ClassNotFoundException, SQLException {
        this.itemPedidoDAO = new ItemPedidoDAO();
    }

    public void cadastrarItemPedido(ItemPedidoVO itemPedido) throws SQLException {
        if (itemPedido == null) {
            throw new IllegalArgumentException("ItemPedido não pode ser nulo.");
        }

        double precoProduto = itemPedidoDAO.buscarPrecoProduto(itemPedido.getId_produto());
        itemPedido.setPreco_unitario(precoProduto);
        itemPedido.setPreco_final(itemPedido.getQuantidade() * precoProduto);

        itemPedidoDAO.ItemPedidoDAO_INSERT(itemPedido);
    }

    public List<ItemPedidoVO> listarItensPedido() throws SQLException {
        return itemPedidoDAO.ItemPedidoDAO_SELECTALL();
    }

    public void atualizarItemPedido(ItemPedidoVO itemPedido) throws SQLException {
        if (itemPedido == null) {
            throw new IllegalArgumentException("ItemPedido não pode ser nulo.");
        }
        itemPedidoDAO.ItemPedidoDAO_ATUALIZAR(itemPedido);
    }

    public void deletarItemPedido(int idItem) throws SQLException {
        if (idItem <= 0) {
            throw new IllegalArgumentException("ID do item do pedido inválido.");
        }
        itemPedidoDAO.ItemPedidoDAO_DELETE(idItem);
    }
    
    
}
