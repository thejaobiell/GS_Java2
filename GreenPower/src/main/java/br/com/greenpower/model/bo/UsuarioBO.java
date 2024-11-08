package br.com.greenpower.model.bo;

import br.com.greenpower.model.dao.UsuarioDAO;
import br.com.greenpower.model.vo.UsuarioVO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public UsuarioBO(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    // Método para validar o login do usuário
    public boolean validarLogin(String usuario, String senha) throws SQLException {
        UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(usuario);
        if (usuarioVO != null && usuarioVO.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    // Método para adicionar um novo usuário
    public void cadastrarUsuario(String usuario, String senha) throws SQLException {
        UsuarioVO usuarioVO = new UsuarioVO(usuario, senha);
        usuarioDAO.inserirUsuario(usuarioVO);
    }

    // Método para listar todos os usuários
    public List<UsuarioVO> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    // Método para atualizar um usuário
    public void atualizarUsuario(String usuario, String senha) throws SQLException {
        UsuarioVO usuarioVO = new UsuarioVO(usuario, senha);
        usuarioDAO.atualizarUsuario(usuarioVO);
    }

    // Método para deletar um usuário
    public void deletarUsuario(String usuario) throws SQLException {
        usuarioDAO.deletarUsuario(usuario);
    }
}
