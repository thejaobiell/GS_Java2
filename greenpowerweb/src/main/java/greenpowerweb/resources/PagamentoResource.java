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

    // Atualizar (PUT) - Atualizar pagamento com Pix
    @PUT
    @Path("/pix/{id_pagamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPagamentoPix(PagamentoVO pagamento, @PathParam("id_pagamento") int idPagamento) {
        try {
            pagamento.setId_pagamento(idPagamento);
            pagamentoBO.atualizarPagamentoPix(pagamento);
            return Response.ok("Pagamento atualizado com sucesso utilizando Pix! " + pagamento.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pagamento com Pix: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT) - Atualizar pagamento com Cartão
    @PUT
    @Path("/cartao/{id_pagamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPagamentoCartao(PagamentoVO pagamento, @PathParam("id_pagamento") int idPagamento) {
        try {
            pagamento.setId_pagamento(idPagamento);
            pagamentoBO.atualizarPagamentoCartao(pagamento);
            return Response.ok("Pagamento atualizado com sucesso utilizando Cartão! " + pagamento.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pagamento com Cartão: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT) - Atualizar pagamento com Boleto
    @PUT
    @Path("/boleto/{id_pagamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPagamentoBoleto(PagamentoVO pagamento, @PathParam("id_pagamento") int idPagamento) {
        try {
            pagamento.setId_pagamento(idPagamento);
            pagamentoBO.atualizarPagamentoBoleto(pagamento);
            return Response.ok("Pagamento atualizado com sucesso utilizando Boleto! " + pagamento.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pagamento com Boleto: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{id_pagamento}")
    public Response deletarPagamento(@PathParam("id_pagamento") int idPagamento) {
        try {
            pagamentoBO.deletarPagamento(idPagamento);
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
            List<PagamentoVO> pagamentos = pagamentoBO.listarPagamentos();
            return Response.ok(pagamentos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pagamentos: " + e.getMessage())
                    .build();
        }
    }
}
