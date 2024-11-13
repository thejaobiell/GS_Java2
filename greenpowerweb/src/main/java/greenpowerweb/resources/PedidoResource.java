package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.PedidoBO;
import greenpowerweb.model.vo.PedidoVO;

import java.sql.SQLException;
import java.util.ArrayList;

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

    // Inserir (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPedido(PedidoVO pedido, @Context UriInfo uriInfo) {
        try {
            pedidoBO.cadastrarPedido(pedido);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(pedido.getId_pedido()));
            return Response.created(builder.build())
                    .entity(pedido)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/{id_pedido}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPedido(PedidoVO pedido, @PathParam("id_pedido") int idPedido) {
        try {
            pedido.setId_pedido(idPedido);
            pedidoBO.atualizarPedido(pedido);
            return Response.ok("Status do Pedido atualizado! " + pedido.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{id_pedido}")
    public Response deletarPedido(@PathParam("id_pedido") int idPedido) {
        try {
            pedidoBO.deletarPedido(idPedido);
            return Response.ok("Pedido " + idPedido + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar pedido: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPedidos() {
        try {
            ArrayList<PedidoVO> pedidos = pedidoBO.listarPedidos();
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar pedidos: " + e.getMessage())
                    .build();
        }
    }
}
