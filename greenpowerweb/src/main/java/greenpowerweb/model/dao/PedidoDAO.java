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

    public double calcularValorTotal(int idPedido) throws SQLException {
        String sql = "SELECT SUM(preco_final) AS valor_total FROM ITEM_COMPRADO WHERE id_pedido = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("valor_total");
                }
            }
        }
        return 0.0;
    }

    public void PedidoDAO_INSERT(PedidoVO pedido) throws SQLException {
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

    public void PedidoDAO_ATUALIZARValorTotal(int idPedido) throws SQLException {
        double valorTotal = calcularValorTotal(idPedido);

        String sql = "UPDATE PEDIDO SET valor_total = ? WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valorTotal);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        }
    }

    public void PedidoDAO_ATUALIZARPedido(int idPedido, String statusPedido, String statusPagamento) throws SQLException {
        double valorTotal = calcularValorTotal(idPedido);

        String sql = "UPDATE PEDIDO SET valor_total = ?, status_pedido = ?, status_pagamento = ? WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valorTotal);
            stmt.setString(2, statusPedido);
            stmt.setString(3, statusPagamento);
            stmt.setInt(4, idPedido);
            stmt.executeUpdate();
        }
    }

    public List<PedidoVO> PedidoDAO_SELECTALL() throws SQLException {
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

    public void PedidoDAO_DELETE(int idPedido) throws SQLException {
        String sql = "DELETE FROM PEDIDO WHERE id_pedido = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }
}
