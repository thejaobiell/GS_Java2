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

    // Cadastrar um pedido (POST)
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPedido(PedidoVO pedido, @Context UriInfo uriInfo) {
        try {
            pedidoBO.cadastrarPedido(pedido);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(pedido.getId_pedido()));
            String mensagem = "Pedido cadastrado com sucesso!\n" + pedido.toString();
            return Response.created(builder.build())
                    .entity(mensagem)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar pedido (PUT)
    @PUT
    @Path("/atualizar/{id_pedido}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPedido(PedidoVO pedido, @PathParam("id_pedido") int id_pedido) {
        try {
            pedido.setId_pedido(id_pedido);
            pedidoBO.atualizarPedido(pedido);
            return Response.ok("Pedido atualizado com sucesso! " + pedido.toStringEdit())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Deletar pedido (DELETE)
    @DELETE
    @Path("/deletar/{id_pedido}")
    public Response deletarPedido(@PathParam("id_pedido") int id_pedido) {
        try {
            pedidoBO.deletarPedido(id_pedido);
            return Response.ok("Pedido " + id_pedido + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Listar pedidos (GET)
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPedidos() {
        try {
            List<PedidoVO> pedidos = pedidoBO.listarPedidos();
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pedidos: " + e.getMessage())
                    .build();
        }
    }
}
