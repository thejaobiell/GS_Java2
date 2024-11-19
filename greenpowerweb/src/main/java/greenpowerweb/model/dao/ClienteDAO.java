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

    public void clienteDaoInsert(ClienteVO cliente) throws SQLException {
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
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Erro ao inserir o cliente. Nenhuma linha afetada.");
            }
        }
    }

    public List<ClienteVO> clienteDaoSelectAll() throws SQLException {
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
    
    public ClienteVO clienteDaoLogin(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM CLIENTE WHERE email_cliente = ? AND senha_cliente = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ClienteVO(
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
                } else {
                    throw new SQLException("Credenciais inválidas para o cliente.");
                }
            }
        }
    }

    public void clienteDaoAtualizar(ClienteVO cliente, String emailClienteOriginal) throws SQLException {
        String sql = "UPDATE CLIENTE SET email_cliente = ?, senha_cliente = ?, nome_cliente = ?, sobrenome_cliente = ?, rua_cliente = ?, numero_cliente = ?, complemento_cliente = ?, bairro_cliente = ?, cidade_cliente = ?, estado_cliente = ?, cep_cliente = ? WHERE email_cliente = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getEmail_Cliente());
            stmt.setString(2, cliente.getSenha_Cliente()); 
            stmt.setString(3, cliente.getNome_Cliente());
            stmt.setString(4, cliente.getSobrenome_Cliente());
            stmt.setString(5, cliente.getRua_Cliente());
            stmt.setInt(6, cliente.getNumero_Cliente());
            stmt.setString(7, cliente.getComplemento_Cliente());
            stmt.setString(8, cliente.getBairro_Cliente());
            stmt.setString(9, cliente.getCidade_Cliente());
            stmt.setString(10, cliente.getEstado_Cliente());
            stmt.setString(11, cliente.getCep_Cliente());
            stmt.setString(12, emailClienteOriginal);

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum cliente encontrado com o email fornecido para atualização.");
            }
        }
    }

    public void clienteDaoDelete(String emailCliente) throws SQLException {
        String sql = "DELETE FROM CLIENTE WHERE email_cliente = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, emailCliente);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Nenhum cliente encontrado com o email fornecido para exclusão.");
            }
        }
    }
}
