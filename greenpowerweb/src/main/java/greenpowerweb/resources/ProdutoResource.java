package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.ProdutoBO;
import greenpowerweb.model.vo.ProdutoVO;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/produto")
public class ProdutoResource {

    private ProdutoBO produtoBO;

    public ProdutoResource() {
        try {
            this.produtoBO = new ProdutoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar ProdutoBO: " + e.getMessage());
        }
    }

    // Inserir (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(ProdutoVO produto, @Context UriInfo uriInfo) {
        try {
            produtoBO.cadastrarProduto(produto);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(produto.getId_produto()));
            return Response.created(builder.build())
                    .entity(produto.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produto: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/{id_produto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(ProdutoVO produto, @PathParam("id_produto") int id_produto) {
        try {
            produto.setId_produto(id_produto);
            produtoBO.atualizarProduto(produto);
            return Response.ok("Produto atualizado com sucesso! " + produto.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar produto: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{id_produto}")
    public Response deletarProduto(@PathParam("id_produto") int id_produto) {
        try {
            produtoBO.deletarProduto(id_produto);
            return Response.ok("Produto " + id_produto + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar produto: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        try {
            ArrayList<ProdutoVO> produtos = produtoBO.listarProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos: " + e.getMessage())
                    .build();
        }
    }
}
