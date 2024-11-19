package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.PedidoBO;
import greenpowerweb.model.vo.PedidoVO;

import java.sql.SQLException;
import java.util.List;

@Path("/pedido")
public class PedidoResource {

    private PedidoBO pedidoBO;

    public PedidoResource() {
        try {
            this.pedidoBO = new PedidoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar PedidoBO: " + e.getMessage());
        }
    }

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPedido(PedidoVO pedido, @Context UriInfo uriInfo) {
        try {
            pedidoBO.cadastrarPedido(pedido);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(pedido.getId_pedido()));
            return Response.created(builder.build())
                    .entity("Pedido cadastrado com sucesso! " + pedido.toString())
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pedido (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pedido: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar_valor/{id_pedido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarValorTotal(@PathParam("id_pedido") int id_pedido) {
        try {
            pedidoBO.atualizarValorTotal(id_pedido);
            return Response.ok("Valor total atualizado com sucesso para o pedido " + id_pedido).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar valor total (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar valor total: " + e.getMessage())
                    .build();
        }
    }
    
    @PUT
    @Path("/atualizar_completo/{id_pedido}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPedido(@PathParam("id_pedido") int id_pedido, PedidoVO pedido) {
        try {
            pedidoBO.atualizarPedido(id_pedido, pedido.getStatus_pedido(), pedido.getStatus_pagamento());
            return Response.ok("Pedido " + id_pedido + " atualizado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pedido (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pedido: " + e.getMessage())
                    .build();
        }
    }
    
    @DELETE
    @Path("/deletar/{id_pedido}")
    public Response deletarPedido(@PathParam("id_pedido") int id_pedido) {
        try {
            pedidoBO.deletarPedido(id_pedido);
            return Response.ok("Pedido " + id_pedido + " deletado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pedido (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pedido: " + e.getMessage())
                    .build();
        }
    }
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPedidos() {
        try {
            List<PedidoVO> pedidos = pedidoBO.listarPedidos();
            return Response.ok(pedidos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pedidos (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pedidos: " + e.getMessage())
                    .build();
        }
    }
}
