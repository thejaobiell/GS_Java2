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

    public void painelSolarDaoInsert(PainelSolarVO painelSolar) throws SQLException {
        String sql = "INSERT INTO PAINELSOLAR (id_painelsolar, email_cliente, energia_gerada_kwh, energia_consumida_kwh, data_registro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, painelSolar.getId_painelsolar());
            stmt.setString(2, painelSolar.getEmail_cliente());
            stmt.setDouble(3, painelSolar.getEnergia_gerada_kwh());
            stmt.setDouble(4, painelSolar.getEnergia_consumida_kwh());
            stmt.setDate(5, painelSolar.getData_registro());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir o painel solar. Nenhuma linha foi inserida.");
            }
        }
    }

    public List<PainelSolarVO> painelSolarDaoSelectAll() throws SQLException {
        List<PainelSolarVO> paineis = new ArrayList<>();
        String sql = "SELECT * FROM PAINELSOLAR";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PainelSolarVO painel = new PainelSolarVO(
                    rs.getInt("id_painelsolar"),
                    rs.getString("email_cliente"),
                    rs.getDouble("energia_gerada_kwh"),
                    rs.getDouble("energia_consumida_kwh"),
                    rs.getDate("data_registro")
                );
                paineis.add(painel);
            }
        }
        return paineis;
    }

    public void painelSolarDaoUpdate(PainelSolarVO painelSolar) throws SQLException {
        String sql = "UPDATE PAINELSOLAR SET energia_gerada_kwh = ?, energia_consumida_kwh = ?, data_registro = ?, email_cliente = ? WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, painelSolar.getEnergia_gerada_kwh());
            stmt.setDouble(2, painelSolar.getEnergia_consumida_kwh());
            stmt.setDate(3, painelSolar.getData_registro());
            stmt.setString(4, painelSolar.getEmail_cliente());
            stmt.setInt(5, painelSolar.getId_painelsolar());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Nenhum painel solar encontrado com o ID fornecido: " + painelSolar.getId_painelsolar());
            }
        }
    }

    public void painelSolarDaoDelete(int idPainelSolar) throws SQLException {
        String sql = "DELETE FROM PAINELSOLAR WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPainelSolar);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Nenhum painel solar encontrado com o ID fornecido: " + idPainelSolar);
            }
        }
    }

    public PainelSolarVO painelSolarDaoSelectById(int id) throws SQLException {
        String sql = "SELECT * FROM PAINELSOLAR WHERE id_painelsolar = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PainelSolarVO(
                        rs.getInt("id_painelsolar"),
                        rs.getString("email_cliente"),
                        rs.getDouble("energia_gerada_kwh"),
                        rs.getDouble("energia_consumida_kwh"),
                        rs.getDate("data_registro")
                    );
                } else {
                    throw new SQLException("Nenhum painel solar encontrado com o ID fornecido: " + id);
                }
            }
        }
    }
}
