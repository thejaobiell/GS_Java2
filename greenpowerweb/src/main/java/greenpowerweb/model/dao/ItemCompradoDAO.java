package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.ItemCompradoVO;

public class ItemCompradoDAO {
    private Connection conexao;

    public ItemCompradoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    // CREATE
    public void ItemPedidoDAO_INSERT(ItemCompradoVO itemPedido) throws SQLException {
        String sqlInsert = "INSERT INTO ITEM_COMPRADO (id_item, id_pedido, id_produto, quantidade, preco_unitario, preco_final) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlUpdatePedido = "UPDATE PEDIDO SET valor_total = (SELECT SUM(preco_final) FROM ITEM_COMPRADO WHERE id_pedido = ?) WHERE id_pedido = ?";
        try (PreparedStatement stmtInsert = conexao.prepareStatement(sqlInsert);
             PreparedStatement stmtUpdate = conexao.prepareStatement(sqlUpdatePedido)) {
            stmtInsert.setInt(1, itemPedido.getId_item());
            stmtInsert.setInt(2, itemPedido.getId_pedido());
            stmtInsert.setInt(3, itemPedido.getId_produto());
            stmtInsert.setInt(4, itemPedido.getQuantidade());
            stmtInsert.setDouble(5, itemPedido.getPreco_unitario());
            stmtInsert.setDouble(6, itemPedido.getPreco_final());
            stmtInsert.executeUpdate();
            stmtUpdate.setInt(1, itemPedido.getId_pedido());
            stmtUpdate.setInt(2, itemPedido.getId_pedido());
            stmtUpdate.executeUpdate();
        }
    }


    // READ (Listar todos os itens de pedido)
    public List<ItemCompradoVO> ItemPedidoDAO_SELECTALL() throws SQLException {
        List<ItemCompradoVO> itensPedido = new ArrayList<>();
        String sql = "SELECT * FROM ITEM_COMPRADO";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ItemCompradoVO itemPedido = new ItemCompradoVO(
                    rs.getInt("id_item"),
                    rs.getInt("id_pedido"),
                    rs.getInt("id_produto"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_unitario"),
                    rs.getDouble("preco_final")
                );
                itensPedido.add(itemPedido);
            }
        }
        return itensPedido;
    }

    // UPDATE
    public void ItemPedidoDAO_ATUALIZAR(ItemCompradoVO itemPedido) throws SQLException {
        String sqlUpdateItem = "UPDATE ITEM_COMPRADO SET id_pedido = ?, id_produto = ?, quantidade = ?, preco_unitario = ?, preco_final = ? WHERE id_item = ?";
        String sqlUpdatePedido = "UPDATE PEDIDO SET valor_total = (SELECT SUM(preco_final) FROM ITEM_COMPRADO WHERE id_pedido = ?) WHERE id_pedido = ?";

        try (PreparedStatement stmtUpdateItem = conexao.prepareStatement(sqlUpdateItem);
             PreparedStatement stmtUpdatePedido = conexao.prepareStatement(sqlUpdatePedido)) {
            stmtUpdateItem.setInt(1, itemPedido.getId_pedido());
            stmtUpdateItem.setInt(2, itemPedido.getId_produto());
            stmtUpdateItem.setInt(3, itemPedido.getQuantidade());
            stmtUpdateItem.setDouble(4, itemPedido.getPreco_unitario());
            stmtUpdateItem.setDouble(5, itemPedido.getPreco_final());
            stmtUpdateItem.setInt(6, itemPedido.getId_item());
            stmtUpdateItem.executeUpdate();
            stmtUpdatePedido.setInt(1, itemPedido.getId_pedido());
            stmtUpdatePedido.setInt(2, itemPedido.getId_pedido());
            stmtUpdatePedido.executeUpdate();
        }
    }


    // DELETE
    public void ItemPedidoDAO_DELETE(int idItem) throws SQLException {
        String sql = "DELETE FROM ITEM_COMPRADO WHERE id_item = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idItem);
            stmt.executeUpdate();
        }
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
}