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

    @POST
    @Path("/registrar-unico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProduto(ProdutoVO produto, @Context UriInfo uriInfo) {
        try {
            produtoBO.cadastrarProduto(produto);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(produto.getId_produto()));
            
            return Response.created(builder.build())
                    .entity(produto)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produto (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produto: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/registrar-varios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProdutos(List<ProdutoVO> produtos, @Context UriInfo uriInfo) {
        try {
            for (ProdutoVO produto : produtos) {
                produtoBO.cadastrarProduto(produto);
            }
            return Response.ok("Produtos cadastrados com sucesso! " + produtos.toString()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produtos (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar produtos: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/{id_produto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProduto(ProdutoVO produto, @PathParam("id_produto") int id_produto) {
        try {
            produto.setId_produto(id_produto);
            produtoBO.atualizarProduto(produto);
            return Response.ok("Produto atualizado com sucesso! " + produto.toString())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar produto (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar produto: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deletar/{id_produto}")
    public Response deletarProduto(@PathParam("id_produto") int id_produto) {
        try {
            produtoBO.deletarProduto(id_produto);
            return Response.ok("Produto " + id_produto + " deletado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar produto (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar produto: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProdutos() {
        try {
            List<ProdutoVO> produtos = produtoBO.listarProdutos();
            return Response.ok(produtos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar produtos: " + e.getMessage())
                    .build();
        }
    }
}
