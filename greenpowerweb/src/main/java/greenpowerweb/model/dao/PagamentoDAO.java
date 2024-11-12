package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO PAGAMENTO (id_pagamento, email_cliente, forma_pagamento, valor_pagamento, data_pagamento, status_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getId_pagamento());
            stmt.setString(2, pagamento.getEmail_cliente());
            stmt.setString(3, pagamento.getForma_pagamento());
            stmt.setDouble(4, pagamento.getValor_pagamento());
            stmt.setDate(5, pagamento.getData_pagamento());
            stmt.setString(6, pagamento.getStatus_pagamento());
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
                    rs.getString("email_cliente"),
                    rs.getString("forma_pagamento"),
                    rs.getDouble("valor_pagamento"),
                    rs.getDate("data_pagamento"),
                    rs.getString("status_pagamento")
                );
                pagamentos.add(pagamento);
            }
        }
        return pagamentos;
    }

    // UPDATE
    public void PagamentoDAO_ATUALIZAR(PagamentoVO pagamento) throws SQLException {
        String sql = "UPDATE PAGAMENTO SET forma_pagamento = ?, valor_pagamento = ?, data_pagamento = ?, status_pagamento = ? WHERE id_pagamento = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getForma_pagamento());
            stmt.setDouble(2, pagamento.getValor_pagamento());
            stmt.setDate(3, pagamento.getData_pagamento());
            stmt.setString(4, pagamento.getStatus_pagamento());
            stmt.setInt(5, pagamento.getId_pagamento());
            stmt.executeUpdate();
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
