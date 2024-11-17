package greenpowerweb.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import greenpowerweb.model.dao.PainelSolarDAO;
import greenpowerweb.model.vo.PainelSolarVO;

public class PainelSolarBO {
    private PainelSolarDAO painelSolarDAO;

    public PainelSolarBO() throws ClassNotFoundException, SQLException {
        this.painelSolarDAO = new PainelSolarDAO();
    }

    public void cadastrarPainelSolar(PainelSolarVO painelSolar) throws SQLException {
        validarPainelSolarDados(painelSolar);
        painelSolarDAO.PainelSolarDAO_INSERT(painelSolar);
    }

    public void atualizarPainelSolar(PainelSolarVO painelSolar) throws SQLException {
        validarPainelSolarDados(painelSolar);
        painelSolarDAO.PainelSolarDAO_UPDATE(painelSolar);
    }

    public void deletarPainelSolar(int idPainelSolar) throws SQLException {
        painelSolarDAO.PainelSolarDAO_DELETE(idPainelSolar);
    }

    public ArrayList<PainelSolarVO> listarPaineisSolares() throws SQLException {
        return (ArrayList<PainelSolarVO>) painelSolarDAO.PainelSolarDAO_SELECTALL();
    }
    
    public PainelSolarVO buscarPainelSolarPorId(int id) throws SQLException {
        return painelSolarDAO.PainelSolarDAO_SELECTBYID(id);
    }

    private void validarPainelSolarDados(PainelSolarVO painelSolar) {
        if (painelSolar.getEnergia_gerada_kwh() < 0) {
            throw new IllegalArgumentException("Energia gerada não pode ser negativa.");
        }
        if (painelSolar.getEnergia_consumida_kwh() < 0) {
            throw new IllegalArgumentException("Energia consumida não pode ser negativa.");
        }
    }
}