package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.PagamentoBO;
import greenpowerweb.model.vo.PagamentoVO;

import java.sql.SQLException;
import java.util.ArrayList;

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

    // Inserir (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPagamento(PagamentoVO pagamento, @Context UriInfo uriInfo) {
        try {
            pagamentoBO.cadastrarPagamento(pagamento);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(pagamento.getId_pagamento()));
            return Response.created(builder.build())
                    .entity("Pagamento cadastrado com sucesso! " + pagamento.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pagamento: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/{id_pagamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPagamento(PagamentoVO pagamento, @PathParam("id_pagamento") int idPagamento) {
        try {
            pagamento.setId_pagamento(idPagamento);
            pagamentoBO.atualizarPedido(pagamento);
            return Response.ok("Status do Pagamento atualizado! " + pagamento.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pagamento: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{id_pagamento}")
    public Response deletarPagamento(@PathParam("id_pagamento") int idPagamento) {
        try {
            pagamentoBO.deletarPedido(idPagamento);
            return Response.ok("Pagamento " + idPagamento + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pagamento: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPagamentos() {
        try {
            ArrayList<PagamentoVO> pagamentos = pagamentoBO.listarPedidos();
            return Response.ok(pagamentos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pagamentos: " + e.getMessage())
                    .build();
        }
    }
}
