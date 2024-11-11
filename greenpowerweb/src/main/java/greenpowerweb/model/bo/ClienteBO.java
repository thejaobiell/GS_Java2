package greenpowerweb.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import greenpowerweb.model.dao.ClienteDAO;
import greenpowerweb.model.vo.ClienteVO;

public class ClienteBO {
    ClienteDAO clienteDAO;

    public ClienteBO() throws ClassNotFoundException, SQLException {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(ClienteVO cliente) throws ClassNotFoundException, SQLException {
        clienteDAO.ClienteDAO_INSERT(cliente);
    }

    public void atualizarCliente(ClienteVO cliente) throws ClassNotFoundException, SQLException {
        clienteDAO.ClienteDAO_ATUALIZAR(cliente);
    }

    public void deletarCliente(String cpfCliente) throws ClassNotFoundException, SQLException {
        clienteDAO.ClienteDAO_DELETE(cpfCliente);
    }

    public ArrayList<ClienteVO> listarClientes() throws ClassNotFoundException, SQLException {
        return (ArrayList<ClienteVO>) clienteDAO.ClienteDAO_SELECTALL();
    }
}
