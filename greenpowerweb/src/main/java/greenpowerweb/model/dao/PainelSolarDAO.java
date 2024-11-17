package greenpowerweb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greenpowerweb.connection.ConnDAO;
import greenpowerweb.model.vo.PainelSolarVO;

public class PainelSolarDAO {
    private Connection conexao;

    public PainelSolarDAO() throws ClassNotFoundException, SQLException {
        this.conexao = new ConnDAO().conexao();
    }

    // CREATE
    public void PainelSolarDAO_INSERT(PainelSolarVO painelSolar) throws SQLException {
        String sql = "INSERT INTO PAINELSOLAR (id_painelsolar, id_pedido, energia_gerada_kwh, energia_consumida_kwh, data_registro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, painelSolar.getId_painelsolar());
            stmt.setInt(2, painelSolar.getId_pedido());
            stmt.setDouble(3, painelSolar.getEnergia_gerada_kwh());
            stmt.setDouble(4, painelSolar.getEnergia_consumida_kwh());
            stmt.setDate(5, painelSolar.getData_registro());
            stmt.executeUpdate();
        }
    }

    // READ (Listar todos os painéis solares)
    public List<PainelSolarVO> PainelSolarDAO_SELECTALL() throws SQLException {
        List<PainelSolarVO> paineis = new ArrayList<>();
        String sql = "SELECT * FROM PAINELSOLAR";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PainelSolarVO painel = new PainelSolarVO(
                    rs.getInt("id_painelsolar"),
                    rs.getInt("id_pedido"),
                    rs.getDouble("energia_gerada_kwh"),
                    rs.getDouble("energia_consumida_kwh"),
                    rs.getDate("data_registro")
                );
                paineis.add(painel);
            }
        }
        return paineis;
    }

    // UPDATE
    public void PainelSolarDAO_UPDATE(PainelSolarVO painelSolar) throws SQLException {
        String sql = "UPDATE PAINELSOLAR SET energia_gerada_kwh = ?, energia_consumida_kwh = ?, data_registro = ? WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, painelSolar.getEnergia_gerada_kwh());
            stmt.setDouble(2, painelSolar.getEnergia_consumida_kwh());
            stmt.setDate(3, painelSolar.getData_registro());
            stmt.setInt(4, painelSolar.getId_painelsolar());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void PainelSolarDAO_DELETE(int idPainelSolar) throws SQLException {
        String sql = "DELETE FROM PAINELSOLAR WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPainelSolar);
            stmt.executeUpdate();
        }
    }
    
    // SELECIONAR VIA ID
    public PainelSolarVO PainelSolarDAO_SELECTBYID(int id) throws SQLException {
        String sql = "SELECT * FROM PAINELSOLAR WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PainelSolarVO(
                        rs.getInt("id_painelsolar"),
                        rs.getInt("id_pedido"),
                        rs.getDouble("energia_gerada_kwh"),
                        rs.getDouble("energia_consumida_kwh"),
                        rs.getDate("data_registro")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}