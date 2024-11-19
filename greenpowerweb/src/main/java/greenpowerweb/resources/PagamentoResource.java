package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import greenpowerweb.model.bo.PagamentoBO;
import greenpowerweb.model.vo.PagamentoVO;

import java.sql.SQLException;
import java.util.List;

@Path("/pagamento")
public class PagamentoResource {

    private PagamentoBO pagamentoBO;

    public PagamentoResource() {
        try {
            this.pagamentoBO = new PagamentoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar PagamentoBO: " + e.getMessage());
        }
    }

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPagamento(PagamentoVO pagamento, @Context UriInfo uriInfo) {
        try {
            pagamentoBO.cadastrarPagamento(pagamento);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(pagamento.getId_pagamento()));
            return Response.created(builder.build())
                    .entity("Pagamento registrado com sucesso! " + pagamento.toString())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao registrar pagamento (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao registrar pagamento: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/{id_pagamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPagamento(PagamentoVO pagamento, @PathParam("id_pagamento") int idPagamento) {
        try {
            pagamento.setId_pagamento(idPagamento);
            pagamentoBO.atualizarPagamento(pagamento);
            return Response.ok("Pagamento atualizado com sucesso! ID: " + idPagamento)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pagamento (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro inesperado ao atualizar pagamento: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deletar/{id_pagamento}")
    public Response deletarPagamento(@PathParam("id_pagamento") int idPagamento) {
        try {
            pagamentoBO.deletarPagamento(idPagamento);
            return Response.ok("Pagamento " + idPagamento + " deletado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pagamento (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pagamento: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPagamentos() {
        try {
            List<PagamentoVO> pagamentos = pagamentoBO.listarPagamentos();
            return Response.ok(pagamentos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pagamentos (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pagamentos: " + e.getMessage())
                    .build();
        }
    }
}
