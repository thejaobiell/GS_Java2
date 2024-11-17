package greenpowerweb.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.PagamentoVO;

public class PagamentoDAO {
    private Connection conexao;

    public PagamentoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    public void pagamentoDaoInsert(PagamentoVO pagamento) throws SQLException {
        String sqlValorPedido = "SELECT valor_total FROM PEDIDO WHERE id_pedido = ?";
        double valorTotalPedido = 0.0;

        try (PreparedStatement stmtValorPedido = conexao.prepareStatement(sqlValorPedido)) {
            stmtValorPedido.setInt(1, pagamento.getId_pedido());
            try (ResultSet rs = stmtValorPedido.executeQuery()) {
                if (rs.next()) {
                    valorTotalPedido = rs.getDouble("valor_total");
                } else {
                    throw new SQLException("Pedido não encontrado para o id_pedido: " + pagamento.getId_pedido());
                }
            }
        }
        pagamento.setValor_pagamento(valorTotalPedido);

        String sql = "INSERT INTO PAGAMENTO (id_pagamento, id_pedido, id_transacao, forma_pagamento, status_pagamento, data_pagamento, valor_pagamento, qtd_parcelas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getId_pagamento());
            stmt.setInt(2, pagamento.getId_pedido());
            stmt.setString(3, pagamento.getId_transacao());
            stmt.setString(4, pagamento.getForma_pagamento());
            stmt.setString(5, pagamento.getStatus_pagamento());
            stmt.setDate(6, new java.sql.Date(pagamento.getData_pagamento().getTime()));
            stmt.setDouble(7, pagamento.getValor_pagamento());
            stmt.setInt(8, pagamento.getQtd_parcelas());

            stmt.executeUpdate();
        }
    }

    public List<PagamentoVO> pagamentoDaoSelectAll() throws SQLException {
        List<PagamentoVO> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM PAGAMENTO";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PagamentoVO pagamento = new PagamentoVO(
                    rs.getInt("id_pagamento"),
                    rs.getInt("id_pedido"),
                    rs.getString("id_transacao"),
                    rs.getString("forma_pagamento"),
                    rs.getString("status_pagamento"),
                    rs.getDate("data_pagamento"),
                    rs.getDouble("valor_pagamento"),
                    rs.getInt("qtd_parcelas")
                );
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }

    public void pagamentoDaoAtualizar(PagamentoVO pagamento) throws SQLException {
        String sql = "UPDATE PAGAMENTO SET status_pagamento = ?, data_pagamento = ? WHERE id_pagamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getStatus_pagamento());
            stmt.setDate(2, new java.sql.Date(pagamento.getData_pagamento().getTime()));
            stmt.setInt(3, pagamento.getId_pagamento());
            stmt.executeUpdate();
        }
    }

    public void atualizarStatusPedido(int idPedido, String statusPagamento) throws SQLException {
        String sqlPedido = "UPDATE PEDIDO SET status_pagamento = ? WHERE id_pedido = ?";
        try (PreparedStatement stmtPedido = conexao.prepareStatement(sqlPedido)) {
            stmtPedido.setString(1, statusPagamento);
            stmtPedido.setInt(2, idPedido);
            stmtPedido.executeUpdate();
        }
    }

    public void pagamentoDaoDelete(int idPagamento) throws SQLException {
        String sql = "DELETE FROM PAGAMENTO WHERE id_pagamento = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPagamento);
            stmt.executeUpdate();
        }
    }
}
