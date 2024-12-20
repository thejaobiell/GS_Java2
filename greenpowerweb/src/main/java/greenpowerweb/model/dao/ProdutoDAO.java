package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.ProdutoVO;

public class ProdutoDAO {
    private Connection conexao;

    public ProdutoDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    public void produtoDaoInsert(ProdutoVO produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (ID_PRODUTO, NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO, TIPO_PRODUTO) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, produto.getId_produto());
            stmt.setString(2, produto.getNome_produto());
            stmt.setString(3, produto.getDescricao_produto());
            stmt.setDouble(4, produto.getPreco_produto());
            stmt.setString(5, produto.getTipo_produto());
            stmt.executeUpdate();
        }
    }

    public List<ProdutoVO> produtoDaoSelectAll() throws SQLException {
        List<ProdutoVO> produtos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ProdutoVO produto = new ProdutoVO(
                    rs.getInt("ID_PRODUTO"),
                    rs.getString("NOME_PRODUTO"),
                    rs.getString("DESCRICAO_PRODUTO"),
                    rs.getDouble("PRECO_PRODUTO"),
                    rs.getString("TIPO_PRODUTO")
                );
                produtos.add(produto);
            }
        }
        
        if (produtos.isEmpty()) {
            throw new SQLException("Nenhum produto encontrado.");
        }
        
        return produtos;
    }

    public void produtoDaoAtualizar(ProdutoVO produto) throws SQLException {
        String sql = "UPDATE PRODUTO SET NOME_PRODUTO = ?, DESCRICAO_PRODUTO = ?, PRECO_PRODUTO = ?, TIPO_PRODUTO = ? WHERE ID_PRODUTO = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome_produto());
            stmt.setString(2, produto.getDescricao_produto());
            stmt.setDouble(3, produto.getPreco_produto());
            stmt.setString(4, produto.getTipo_produto());
            stmt.setInt(5, produto.getId_produto());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum produto encontrado com o ID fornecido.");
            }
        }
    }

    public void produtoDaoDelete(int id_produto) throws SQLException {
        String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id_produto);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum produto encontrado com o ID fornecido.");
            }
        }
    }
}
