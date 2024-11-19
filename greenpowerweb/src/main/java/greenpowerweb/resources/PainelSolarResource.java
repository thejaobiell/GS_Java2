package greenpowerweb.resources;

import greenpowerweb.model.bo.PainelSolarBO;
import greenpowerweb.model.vo.PainelSolarVO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/painelsolares")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PainelSolarResource {
    private PainelSolarBO painelSolarBO;

    public PainelSolarResource() {
        try {
            this.painelSolarBO = new PainelSolarBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new WebApplicationException("Erro ao inicializar PainelSolarBO: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("/registar")
    public Response cadastrarPainelSolar(PainelSolarVO painelSolar) {
        try {
            painelSolarBO.cadastrarPainelSolar(painelSolar);
            return Response.status(Response.Status.CREATED).entity(painelSolar).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar o painel solar: " + e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro de validação: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/listar")
    public Response listarPaineisSolares() {
        try {
            List<PainelSolarVO> paineis = painelSolarBO.listarPaineisSolares();
            return Response.ok(paineis).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar os painéis solares: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/listar/{id}")
    public Response buscarPainelSolarPorId(@PathParam("id") int id) {
        try {
            PainelSolarVO painel = painelSolarBO.buscarPainelSolarPorId(id);
            if (painel == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Painel solar com ID " + id + " não encontrado.").build();
            }
            return Response.ok(painel).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar o painel solar: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/atualizar/{id}")
    public Response atualizarPainelSolar(@PathParam("id") int id, PainelSolarVO painelSolar) {
        try {
            painelSolar.setId_painelsolar(id);
            painelSolarBO.atualizarPainelSolar(painelSolar);
            return Response.ok().entity("Painel solar atualizado com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o painel solar: " + e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro de validação: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deletar/{id}")
    public Response deletarPainelSolar(@PathParam("id") int id) {
        try {
            painelSolarBO.deletarPainelSolar(id);
            return Response.ok().entity("Painel solar com ID " + id + " foi removido com sucesso.").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao remover o painel solar: " + e.getMessage()).build();
        }
    }
}
