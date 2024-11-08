package br.com.greenpower.model.dao;

import br.com.greenpower.model.vo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para buscar um único usuário no banco
    public UsuarioVO buscarUsuario(String usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioVO(
                    rs.getString("usuario"),
                    rs.getString("senha")
                );
            }
        }
        return null;
    }

    // Método para inserir um usuário
    public void inserirUsuario(UsuarioVO usuarioVO) throws SQLException {
        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuarioVO.getUsuario());
            stmt.setString(2, usuarioVO.getSenha());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(UsuarioVO usuarioVO) throws SQLException {
        String sql = "UPDATE usuarios SET senha = ? WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuarioVO.getSenha());
            stmt.setString(2, usuarioVO.getUsuario());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um usuário
    public void deletarUsuario(String usuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os usuários
    public List<UsuarioVO> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<UsuarioVO> usuarios = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioVO usuario = new UsuarioVO(
                    rs.getString("usuario"),
                    rs.getString("senha")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
