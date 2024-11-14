package greenpowerweb.model.bo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import greenpowerweb.model.dao.CartaoCreditoDAO;
import greenpowerweb.model.vo.CartaoCreditoVO;

public class CartaoCreditoBO {
    private CartaoCreditoDAO cartaoCreditoDAO;

    public CartaoCreditoBO() throws ClassNotFoundException, SQLException {
        this.cartaoCreditoDAO = new CartaoCreditoDAO();
    }

    public void cadastrarCartao(CartaoCreditoVO cartao) throws SQLException {
        String bandeira = identificarBandeira(cartao.getNumero_cartao());
        if ("Bandeira desconhecida".equals(bandeira)) {
            throw new IllegalArgumentException("Bandeira do cartão não identificada");
        }
        cartao.setBandeira(bandeira);
        cartaoCreditoDAO.CartaoCreditoDAO_INSERT(cartao);
    }

    public List<CartaoCreditoVO> listarCartoes() throws SQLException {
        return cartaoCreditoDAO.CartaoCreditoDAO_SELECTALL();
    }

    public void atualizarCartao(CartaoCreditoVO cartao) throws SQLException {
        LocalDate validadeCartao = cartao.getValidade_cartao().toLocalDate();
        LocalDate dataAtual = LocalDate.now();
        if (validadeCartao.isBefore(dataAtual)) {
            throw new IllegalArgumentException("A data de validade do cartão está vencida.");
        }
        cartaoCreditoDAO.CartaoCreditoDAO_ATUALIZAR(cartao);
    }

    public void excluirCartao(String numeroCartao) throws SQLException {
        cartaoCreditoDAO.CartaoCreditoDAO_DELETE(numeroCartao);
    }

    

    
    public String identificarBandeira(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.length() < 13 || numeroCartao.length() > 19) {
            return "Número de cartão inválido";
        }

        numeroCartao = numeroCartao.replaceAll("\\s", "");

        if (numeroCartao.startsWith("4") && (numeroCartao.length() == 13 || numeroCartao.length() == 16)) {
            return "Visa";
        }

        if ((numeroCartao.startsWith("5") && Integer.parseInt(numeroCartao.substring(0, 2)) >= 51
                && Integer.parseInt(numeroCartao.substring(0, 2)) <= 55) ||
            (numeroCartao.startsWith("22") && Integer.parseInt(numeroCartao.substring(0, 4)) >= 2221
                && Integer.parseInt(numeroCartao.substring(0, 4)) <= 2720)) {
            return "MasterCard";
        }

        if ((numeroCartao.startsWith("34") || numeroCartao.startsWith("37")) && numeroCartao.length() == 15) {
            return "American Express";
        }

        if (numeroCartao.startsWith("5067") || numeroCartao.startsWith("4576") || numeroCartao.startsWith("4011") ||
            numeroCartao.startsWith("4312") || numeroCartao.startsWith("4389") || numeroCartao.startsWith("5066") ||
            numeroCartao.startsWith("5090") || numeroCartao.startsWith("5091") || numeroCartao.startsWith("5092") ||
            numeroCartao.startsWith("5093") || numeroCartao.startsWith("5094")) {
            return "Elo";
        }
        return "Bandeira desconhecida";
    }
}