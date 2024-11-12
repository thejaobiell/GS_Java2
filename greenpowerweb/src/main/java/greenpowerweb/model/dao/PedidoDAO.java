package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.PedidoVO;

public class PedidoDAO {
    private Connection conexao;

    public PedidoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    // CREATE
    public void PedidoDAO_INSERT(PedidoVO pedido) throws SQLException {
        String sql = "INSERT INTO PEDIDO (email_cliente, data_pedido, status_pedido) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pedido.getEmail_cliente());
            stmt.setDate(2, new java.sql.Date(pedido.getData_pedido().getTime()));
            stmt.setString(3, pedido.getStatus_pedido());
            stmt.executeUpdate();
        }
    }

    // READ (Listar todos os pedidos)
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
                    rs.getString("status_pedido")
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // READ (Buscar pedido por ID)
    public PedidoVO PedidoDAO_SELECTBYID(int idPedido) throws SQLException {
        PedidoVO pedido = null;
        String sql = "SELECT * FROM PEDIDO WHERE id_pedido = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new PedidoVO(
                        rs.getInt("id_pedido"),
                        rs.getString("email_cliente"),
                        rs.getDate("data_pedido"),
                        rs.getString("status_pedido")
                    );
                }
            }
        }
        return pedido;
    }

    // UPDATE
    public void PedidoDAO_ATUALIZAR(PedidoVO pedido) throws SQLException {
        String sql = "UPDATE PEDIDO SET email_cliente = ?, data_pedido = ?, status_pedido = ? WHERE id_pedido = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pedido.getEmail_cliente());
            stmt.setDate(2, new java.sql.Date(pedido.getData_pedido().getTime()));
            stmt.setString(3, pedido.getStatus_pedido());
            stmt.setInt(4, pedido.getId_pedido());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void PedidoDAO_DELETE(int idPedido) throws SQLException {
        String sql = "DELETE FROM PEDIDO WHERE id_pedido = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        }
    }
}
