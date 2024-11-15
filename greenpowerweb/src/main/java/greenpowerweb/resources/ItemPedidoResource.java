package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import greenpowerweb.model.bo.ItemPedidoBO;
import greenpowerweb.model.vo.ItemPedidoVO;

import java.sql.SQLException;
import java.util.List;

@Path("/itempedido")
public class ItemPedidoResource {

    private ItemPedidoBO itemPedidoBO;

    public ItemPedidoResource() {
        try {
            this.itemPedidoBO = new ItemPedidoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar ItemPedidoBO: " + e.getMessage());
        }
    }

    // Inserir (POST)
    @POST
    @Path("/unico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarItemPedido(ItemPedidoVO itemPedido) {
        try {
            itemPedidoBO.cadastrarItemPedido(itemPedido);
            return Response.ok("Item de pedido cadastrado com sucesso! " + itemPedido.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar item de pedido: " + e.getMessage())
                    .build();
        }
    }

    // Inserir varios (POST)
    @POST
    @Path("/varios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarItensPedido(List<ItemPedidoVO> itensPedido) {
        try {
            for (ItemPedidoVO itemPedido : itensPedido) {
                itemPedidoBO.cadastrarItemPedido(itemPedido);
            }
            return Response.ok("Itens de pedido cadastrados com sucesso! " + itensPedido.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar itens de pedido: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/{id_item}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarItemPedido(ItemPedidoVO itemPedido, @PathParam("id_item") int idItem) {
        try {
            itemPedido.setId_item(idItem);
            itemPedidoBO.atualizarItemPedido(itemPedido);
            return Response.ok("Item de pedido atualizado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar item de pedido: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{id_item}")
    public Response deletarItemPedido(@PathParam("id_item") int idItem) {
        try {
            itemPedidoBO.deletarItemPedido(idItem);
            return Response.ok("Item de pedido " + idItem + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar item de pedido: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarItensPedido() {
        try {
            List<ItemPedidoVO> itensPedido = itemPedidoBO.listarItensPedido();
            return Response.ok(itensPedido).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar itens de pedido: " + e.getMessage())
                    .build();
        }
    }
}
