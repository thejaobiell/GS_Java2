package greenpowerweb.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.PedidoVO;

public class PedidoDAO {
    private Connection conexao;

    public PedidoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    public void pedidoDaoInsert(PedidoVO pedido) throws SQLException {
        String sql = "INSERT INTO PEDIDO (id_pedido, email_cliente, data_pedido, status_pedido, status_pagamento, valor_total) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId_pedido());
            stmt.setString(2, pedido.getEmail_cliente());
            stmt.setDate(3, new java.sql.Date(pedido.getData_pedido().getTime()));
            stmt.setString(4, pedido.getStatus_pedido());
            stmt.setString(5, pedido.getStatus_pagamento());
            stmt.setDouble(6, 0.0);
            stmt.executeUpdate();
        }
    }

    public void pedidoDaoAtualizarPedido(int idPedido, String statusPedido, String statusPagamento) throws SQLException {
        String sql = "UPDATE PEDIDO SET status_pedido = ?, status_pagamento = ? WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, statusPedido);
            stmt.setString(2, statusPagamento);
            stmt.setInt(3, idPedido);
            stmt.executeUpdate();
        }
    }

    public List<PedidoVO> pedidoDaoSelectAll() throws SQLException {
        List<PedidoVO> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PedidoVO pedido = new PedidoVO(
                    rs.getInt("id_pedido"),
                    rs.getString("email_cliente"),
                    rs.getDate("data_pedido"),
                    rs.getString("status_pedido"),
                    rs.getString("status_pagamento"),
                    rs.getDouble("valor_total")
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public void pedidoDaoDelete(int idPedido) throws SQLException {
        String sql = "DELETE FROM PEDIDO WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }
}
