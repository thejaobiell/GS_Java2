package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.ProdutoBO;
import greenpowerweb.model.vo.ProdutoVO;

import java.sql.SQLException;
import java.util.List;

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

    // Inserir um único produto (POST)
    @POST
    @Path("/unico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(ProdutoVO produto, @Context UriInfo uriInfo) {
        try {
            produtoBO.cadastrarProduto(produto);
            return Response.ok("Produto cadastrado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produto: " + e.getMessage())
                    .build();
        }
    }

    // Inserir vários produtos (POST)
    @POST
    @Path("/varios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProdutos(List<ProdutoVO> produtos, @Context UriInfo uriInfo) {
        try {
            for (ProdutoVO produto : produtos) {
                produtoBO.cadastrarProduto(produto);
            }
            return Response.ok("Produtos cadastrados com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produtos: " + e.getMessage())
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
            List<ProdutoVO> produtos = produtoBO.listarProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos: " + e.getMessage())
                    .build();
        }
    }
}
