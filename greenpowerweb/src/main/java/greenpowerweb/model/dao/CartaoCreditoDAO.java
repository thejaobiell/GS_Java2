package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.CartaoCreditoVO;

public class CartaoCreditoDAO {
    private Connection conexao;

    public CartaoCreditoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    // CREATE
    public void CartaoCreditoDAO_INSERT(CartaoCreditoVO cartao) throws SQLException {
        String sql = "INSERT INTO CARTAO_CREDITO (id_cartao, email_cliente, numero_cartao, validade_cartao, codigo_seguranca, bandeira) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cartao.getId_cartao());
            stmt.setString(2, cartao.getEmail_cliente());
            stmt.setString(3, cartao.getNumero_cartao());
            stmt.setDate(4, cartao.getValidade_cartao());
            stmt.setString(5, cartao.getCodigo_seguranca());
            stmt.setString(6, cartao.getBandeira());
            stmt.executeUpdate();
        }
    }


    // READ (Listar todos os cartões de crédito)
    public List<CartaoCreditoVO> CartaoCreditoDAO_SELECTALL() throws SQLException {
        List<CartaoCreditoVO> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM CARTAO_CREDITO";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                CartaoCreditoVO cartao = new CartaoCreditoVO(
                    rs.getInt("id_cartao"),
                    rs.getString("email_cliente"),
                    rs.getString("numero_cartao"),
                    rs.getDate("validade_cartao"),
                    rs.getString("codigo_seguranca"),
                    rs.getString("bandeira")
                );
                cartoes.add(cartao);
            }
        }
        return cartoes;
    }

    // UPDATE
    public void CartaoCreditoDAO_ATUALIZAR(CartaoCreditoVO cartao) throws SQLException {
        String sql = "UPDATE CARTAO_CREDITO SET validade_cartao = ?, codigo_seguranca = ? WHERE numero_cartao = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, cartao.getValidade_cartao());
            stmt.setString(2, cartao.getCodigo_seguranca());
            stmt.setString(3, cartao.getNumero_cartao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o cartão: " + e.getMessage());
        }
    }

    // DELETE
    public void CartaoCreditoDAO_DELETE(String numeroCartao) throws SQLException {
        String sql = "DELETE FROM CARTAO_CREDITO WHERE numero_cartao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, numeroCartao); // Use numero_cartao como String
            stmt.executeUpdate();
        }
    }

}
