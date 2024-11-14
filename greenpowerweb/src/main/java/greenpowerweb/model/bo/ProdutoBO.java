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
        produtoDAO.ProdutoDAO_INSERT(produto);
    }

    public void atualizarProduto(ProdutoVO produto) throws ClassNotFoundException, SQLException, IOException {
        produtoDAO.ProdutoDAO_ATUALIZAR(produto);
    }

    public void deletarProduto(int idProduto) throws ClassNotFoundException, SQLException, IOException {
        produtoDAO.ProdutoDAO_DELETE(idProduto);
    }

    public List<ProdutoVO> listarProdutos() throws ClassNotFoundException, SQLException, IOException {
        return produtoDAO.ProdutoDAO_SELECTALL();
    }
}
