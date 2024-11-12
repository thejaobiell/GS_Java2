package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        clienteDAO.ClienteDAO_INSERT(cliente);
    }

    public void atualizarCliente(ClienteVO cliente) throws ClassNotFoundException, SQLException, IOException {
        validarClienteDados(cliente);
        clienteDAO.ClienteDAO_ATUALIZAR(cliente);
    }

    public void deletarCliente(String cpfCliente) throws ClassNotFoundException, SQLException {
        clienteDAO.ClienteDAO_DELETE(cpfCliente);
    }

    public ArrayList<ClienteVO> listarClientes() throws ClassNotFoundException, SQLException {
        return (ArrayList<ClienteVO>) clienteDAO.ClienteDAO_SELECTALL();
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
