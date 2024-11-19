package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import greenpowerweb.model.bo.ItemCompradoBO;
import greenpowerweb.model.vo.ItemCompradoVO;

import java.sql.SQLException;
import java.util.List;

@Path("/itemcomprado")
public class ItemCompradoResource {

    private ItemCompradoBO itemCompradoBO;

    public ItemCompradoResource() {
        try {
            this.itemCompradoBO = new ItemCompradoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar ItemCompradoBO: " + e.getMessage());
        }
    }

    @POST
    @Path("/registrar-unico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarItemComprado(ItemCompradoVO itemComprado) {
        try {
            itemCompradoBO.cadastrarItemComprado(itemComprado);
            return Response.ok("Item comprado cadastrado com sucesso! " + itemComprado.toString()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar item comprado (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar item comprado: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/registrar-varios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarItensComprados(List<ItemCompradoVO> itensComprados) {
        try {
            for (ItemCompradoVO itemComprado : itensComprados) {
                itemCompradoBO.cadastrarItemComprado(itemComprado);
            }
            return Response.ok("Itens comprados cadastrados com sucesso! " + itensComprados.toString()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar itens comprados (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar itens comprados: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/{id_item}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarItemComprado(ItemCompradoVO itemComprado, @PathParam("id_item") int idItem) {
        try {
            itemComprado.setId_item(idItem);
            itemCompradoBO.atualizarItemComprado(itemComprado);
            return Response.ok("Item comprado atualizado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar item comprado (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar item comprado: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deletar/{id_item}")
    public Response deletarItemComprado(@PathParam("id_item") int idItem) {
        try {
            itemCompradoBO.deletarItemComprado(idItem);
            return Response.ok("Item comprado " + idItem + " deletado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar item comprado (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar item comprado: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarItensComprados() {
        try {
            List<ItemCompradoVO> itensComprados = itemCompradoBO.listarItensComprados();
            return Response.ok(itensComprados).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar itens comprados (DB): " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar itens comprados: " + e.getMessage())
                    .build();
        }
    }
}
