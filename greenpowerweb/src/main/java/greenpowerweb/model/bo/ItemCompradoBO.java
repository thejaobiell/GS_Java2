package greenpowerweb.model.bo;

import java.sql.SQLException;
import java.util.List;

import greenpowerweb.model.dao.ItemCompradoDAO;
import greenpowerweb.model.vo.ItemCompradoVO;

public class ItemCompradoBO {
    private ItemCompradoDAO itemCompradoDAO;

    public ItemCompradoBO() throws ClassNotFoundException, SQLException {
        this.itemCompradoDAO = new ItemCompradoDAO();
    }

    public void cadastrarItemComprado(ItemCompradoVO itemComprado) throws SQLException {
        if (itemComprado == null) {
            throw new IllegalArgumentException("ItemComprado não pode ser nulo.");
        }

        double precoProduto = itemCompradoDAO.buscarPrecoProduto(itemComprado.getId_produto());
        itemComprado.setPreco_unitario(precoProduto);
        itemComprado.setPreco_final(itemComprado.getQuantidade() * precoProduto);

        itemCompradoDAO.ItemPedidoDAO_INSERT(itemComprado);
    }

    public List<ItemCompradoVO> listarItensComprados() throws SQLException {
        return itemCompradoDAO.ItemPedidoDAO_SELECTALL();
    }

    public void atualizarItemComprado(ItemCompradoVO itemComprado) throws SQLException {
        if (itemComprado == null) {
            throw new IllegalArgumentException("ItemComprado não pode ser nulo.");
        }
        itemCompradoDAO.ItemPedidoDAO_ATUALIZAR(itemComprado);
    }

    public void deletarItemComprado(int idItem) throws SQLException {
        if (idItem <= 0) {
            throw new IllegalArgumentException("ID do item comprado inválido.");
        }
        itemCompradoDAO.ItemPedidoDAO_DELETE(idItem);
    }
}