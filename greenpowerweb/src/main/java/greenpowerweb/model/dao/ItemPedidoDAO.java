package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.ItemPedidoVO;

public class ItemPedidoDAO {
    private Connection conexao;

    public ItemPedidoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    public double buscarPrecoProduto(int idProduto) throws SQLException {
        String sql = "SELECT preco_produto FROM PRODUTO WHERE id_produto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("preco_produto");
                } else {
                    throw new IllegalArgumentException("Produto com id " + idProduto + " não encontrado.");
                }
            }
        }
    }
    
    // CREATE
    public void ItemPedidoDAO_INSERT(ItemPedidoVO itemPedido) throws SQLException {
        String sql = "INSERT INTO ITEM_PEDIDO (id_item, id_pedido, id_produto, quantidade, preco_unitario, preco_final) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getId_item());
            stmt.setInt(2, itemPedido.getId_pedido());
            stmt.setInt(3, itemPedido.getId_produto());
            stmt.setInt(4, itemPedido.getQuantidade());
            stmt.setDouble(5, itemPedido.getPreco_unitario());
            stmt.setDouble(6, itemPedido.getPreco_final());
            stmt.executeUpdate();
        }
    }

    // READ (Listar todos os itens de pedido)
    public List<ItemPedidoVO> ItemPedidoDAO_SELECTALL() throws SQLException {
        List<ItemPedidoVO> itensPedido = new ArrayList<>();
        String sql = "SELECT * FROM ITEM_PEDIDO";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ItemPedidoVO itemPedido = new ItemPedidoVO(
                    rs.getInt("id_item"),
                    rs.getInt("id_pedido"),
                    rs.getInt("id_produto"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_unitario")
                );
                itensPedido.add(itemPedido);
            }
        }
        return itensPedido;
    }

    // UPDATE
    public void ItemPedidoDAO_ATUALIZAR(ItemPedidoVO itemPedido) throws SQLException {
        String sql = "UPDATE ITEM_PEDIDO SET id_pedido = ?, id_produto = ?, quantidade = ? WHERE id_item = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getId_pedido());
            stmt.setInt(2, itemPedido.getId_produto());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setInt(4, itemPedido.getId_item());
            stmt.executeUpdate();
        }
    }


    // DELETE
    public void ItemPedidoDAO_DELETE(int idItem) throws SQLException {
        String sql = "DELETE FROM ITEM_PEDIDO WHERE id_item = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idItem);
            stmt.executeUpdate();
        }
    }
}