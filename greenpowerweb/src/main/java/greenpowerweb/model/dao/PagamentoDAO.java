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

    // CREATE
    public void PagamentoDAO_INSERT(PagamentoVO pagamento) throws SQLException {
        String sql = "INSERT INTO PAGAMENTO (id_pagamento, id_pedido, id_transacao, forma_pagamento, " +
                     "status_pagamento, data_pagamento, valor_pagamento, id_cartao, chave_pix, numero_boleto, qtd_parcelas) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getId_pagamento());
            stmt.setInt(2, pagamento.getId_pedido());
            stmt.setString(3, pagamento.getId_transacao());
            stmt.setString(4, pagamento.getForma_pagamento());
            stmt.setString(5, pagamento.getStatus_pagamento());
            stmt.setDate(6, new java.sql.Date(pagamento.getData_pagamento().getTime()));
            stmt.setDouble(7, pagamento.getValor_pagamento());
            stmt.setInt(8, pagamento.getId_cartao());
            stmt.setString(9, pagamento.getChave_pix());
            stmt.setString(10, pagamento.getNumero_boleto());
            stmt.setInt(11, pagamento.getQtd_parcelas());
            stmt.executeUpdate();
        }
    }


    // READ (Listar todos os pagamentos)
    public List<PagamentoVO> PagamentoDAO_SELECTALL() throws SQLException {
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
                    rs.getDouble("valor_pagamento"),
                    rs.getDate("data_pagamento"),
                    rs.getString("status_pagamento"),
                    rs.getInt("id_cartao"),
                    rs.getString("chave_pix"),
                    rs.getString("numero_boleto"),
                    rs.getInt("qtd_parcelas")
                );
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }


    // UPDATE
    public void PagamentoDAO_ATUALIZAR(PagamentoVO pagamento) throws SQLException {
        String sql = "UPDATE PAGAMENTO SET status_pagamento = ?, data_pagamento = ?, id_cartao = ?, chave_pix = ?, numero_boleto = ? WHERE id_pagamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getStatus_pagamento());
            stmt.setDate(2, pagamento.getData_pagamento());
            if (pagamento.getId_cartao() != null) {
                stmt.setInt(3, pagamento.getId_cartao());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            if (pagamento.getChave_pix() != null) {
                stmt.setString(4, pagamento.getChave_pix());
            } else {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            }
            if (pagamento.getNumero_boleto() != null) {
                stmt.setString(5, pagamento.getNumero_boleto());
            } else {
                stmt.setNull(5, java.sql.Types.VARCHAR);
            }
            stmt.setInt(6, pagamento.getId_pagamento());
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




    // DELETE
    public void PagamentoDAO_DELETE(int idPagamento) throws SQLException {
        String sql = "DELETE FROM PAGAMENTO WHERE id_pagamento = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPagamento);
            stmt.executeUpdate();
        }
    }
}