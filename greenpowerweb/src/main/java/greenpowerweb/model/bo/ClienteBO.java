package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import greenpowerweb.model.dao.ClienteDAO;
import greenpowerweb.model.vo.ClienteVO;
import greenpowerweb.service.CEPService;

public class ClienteBO {
    private ClienteDAO clienteDAO;
    private CEPService cepService;

    public ClienteBO() throws ClassNotFoundException, SQLException {
        this.clienteDAO = new ClienteDAO();
        this.cepService = new CEPService();
    }

    public void cadastrarCliente(ClienteVO cliente) throws ClassNotFoundException, SQLException, IOException {
        validarClienteDados(cliente);
        clienteDAO.clienteDaoInsert(cliente);
    }

    public void atualizarCliente(String cpf, ClienteVO cliente) throws ClassNotFoundException, SQLException, IOException {
        clienteDAO.clienteDaoAtualizar(cpf, cliente);
    }


    public void deletarCliente(String cpfCliente) throws ClassNotFoundException, SQLException {
        clienteDAO.clienteDaoDelete(cpfCliente);
    }

    public List<ClienteVO> listarClientes() throws ClassNotFoundException, SQLException {
        return clienteDAO.clienteDaoSelectAll();
    }
    
    public ClienteVO verificarLogin(String email, String senha) throws SQLException {
        ClienteVO cliente = clienteDAO.clienteDaoLogin(email, senha);
        if (cliente == null) {
            throw new SQLException("Credenciais inválidas. O cliente não foi encontrado.");
        }
        return cliente;
    }

    private void validarClienteDados(ClienteVO cliente) throws IOException {
        CEPService.ResultadoConsultaCEP cepConsulta = cepService.consultarCEP(cliente.getCep_Cliente());
        if (cepConsulta.getCepFormatado() == null || cepConsulta.getCepFormatado().isEmpty()) {
            throw new IllegalArgumentException("CEP inválido( " + cepConsulta.getCepFormatado() +" ), coloque um CEP válido.");
        }
        if (cliente.getComplemento_Cliente() == null || cliente.getComplemento_Cliente().isEmpty()) {
            cliente.setComplemento_Cliente("N/A");
        }
    }
}
