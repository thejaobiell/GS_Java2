package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.ClienteVO;

public class ClienteDAO {
    private Connection conexao;

    public ClienteDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    // CREATE
    public void ClienteDAO_INSERT(ClienteVO cliente) throws SQLException {
        String sql = "INSERT INTO CLIENTE (email_cliente, senha_cliente, nome_cliente, sobrenome_cliente, cpf_cliente, rua_cliente, numero_cliente, complemento_cliente, bairro_cliente, cidade_cliente, estado_cliente, cep_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail_Cliente());
            stmt.setString(2, cliente.getSenha_Cliente());
            stmt.setString(3, cliente.getNome_Cliente());
            stmt.setString(4, cliente.getSobrenome_Cliente());
            stmt.setString(5, cliente.getCpf_Cliente());
            stmt.setString(6, cliente.getRua_Cliente());
            stmt.setInt(7, cliente.getNumero_Cliente());
            stmt.setString(8, cliente.getComplemento_Cliente());
            stmt.setString(9, cliente.getBairro_Cliente());
            stmt.setString(10, cliente.getCidade_Cliente());
            stmt.setString(11, cliente.getEstado_Cliente());
            stmt.setString(12, cliente.getCep_Cliente());
            stmt.executeUpdate();
        }
    }

    // READ (Listar todos os clientes)
    public List<ClienteVO> ClienteDAO_SELECTALL() throws SQLException {
        List<ClienteVO> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                ClienteVO cliente = new ClienteVO(
                    rs.getString("email_cliente"),
                    rs.getString("senha_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("sobrenome_cliente"),
                    rs.getString("cpf_cliente"),
                    rs.getString("rua_cliente"),
                    rs.getInt("numero_cliente"),
                    rs.getString("complemento_cliente"),
                    rs.getString("bairro_cliente"),
                    rs.getString("cidade_cliente"),
                    rs.getString("estado_cliente"),
                    rs.getString("cep_cliente")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // UPDATE
    public void ClienteDAO_ATUALIZAR(ClienteVO cliente) throws SQLException {
        String sql = "UPDATE CLIENTE SET senha_cliente = ?, nome_cliente = ?, sobrenome_cliente = ?, rua_cliente = ?, numero_cliente = ?, complemento_cliente = ?, bairro_cliente = ?, cidade_cliente = ?, estado_cliente = ?, cep_cliente = ? WHERE email_cliente = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getSenha_Cliente());
            stmt.setString(2, cliente.getNome_Cliente());
            stmt.setString(3, cliente.getSobrenome_Cliente());
            stmt.setString(4, cliente.getRua_Cliente());
            stmt.setInt(5, cliente.getNumero_Cliente());
            stmt.setString(6, cliente.getComplemento_Cliente());
            stmt.setString(7, cliente.getBairro_Cliente());
            stmt.setString(8, cliente.getCidade_Cliente());
            stmt.setString(9, cliente.getEstado_Cliente());
            stmt.setString(10, cliente.getCep_Cliente());
            stmt.setString(11, cliente.getEmail_Cliente());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void ClienteDAO_DELETE(String emailCliente) throws SQLException {
        String sql = "DELETE FROM CLIENTE WHERE email_cliente = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, emailCliente);
            stmt.executeUpdate();
        }
    }
}
