package greenpowerweb.model.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import greenpowerweb.model.dao.ProdutoDAO;
import greenpowerweb.model.vo.ProdutoVO;

public class ProdutoBO {
    private ProdutoDAO produtoDAO;

    public ProdutoBO() throws ClassNotFoundException, SQLException {
        this.produtoDAO = new ProdutoDAO();
    }

    public void cadastrarProduto(ProdutoVO produto) throws ClassNotFoundException, SQLException, IOException {
        if (produto.getPreco_produto() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        produtoDAO.produtoDaoInsert(produto);
    }

    public void atualizarProduto(ProdutoVO produto) throws ClassNotFoundException, SQLException, IOException {
        if (produto.getPreco_produto() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        produtoDAO.produtoDaoAtualizar(produto);
    }

    public void deletarProduto(int idProduto) throws ClassNotFoundException, SQLException, IOException {
        produtoDAO.produtoDaoDelete(idProduto);
    }

    public List<ProdutoVO> listarProdutos() throws ClassNotFoundException, SQLException, IOException {
        return produtoDAO.produtoDaoSelectAll();
    }
}
