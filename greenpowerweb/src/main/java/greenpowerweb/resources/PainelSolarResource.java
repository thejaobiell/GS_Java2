package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.PainelSolarBO;
import greenpowerweb.model.vo.PainelSolarVO;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/painelsolar")
public class PainelSolarResource {

    private PainelSolarBO painelSolarBO;

    public PainelSolarResource() {
        try {
            this.painelSolarBO = new PainelSolarBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar PainelSolarBO: " + e.getMessage());
        }
    }

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPainelSolar(PainelSolarVO painelSolar, @Context UriInfo uriInfo) throws SQLException {
        try {
            painelSolarBO.cadastrarPainelSolar(painelSolar);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(painelSolar.getId_painelsolar()));
            return Response.created(builder.build())
                    .entity("Painel solar cadastrado com sucesso! " + painelSolar.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar painel solar: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPainelSolar(PainelSolarVO painelSolar, @PathParam("id") int id) {
        try {
            painelSolar.setId_painelsolar(id);
            painelSolarBO.atualizarPainelSolar(painelSolar);
            return Response.ok("Painel solar atualizado com sucesso! " + painelSolar.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar painel solar: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/deletar/{id}")
    public Response deletarPainelSolar(@PathParam("id") int id) {
        try {
            painelSolarBO.deletarPainelSolar(id);
            return Response.ok("Painel solar com ID " + id + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar painel solar: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPaineisSolares() {
        try {
            ArrayList<PainelSolarVO> paineis = painelSolarBO.listarPaineisSolares();
            return Response.ok(paineis).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar painéis solares: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPainelSolar(@PathParam("id") int id) {
        try {
            PainelSolarVO painel = painelSolarBO.buscarPainelSolarPorId(id);
            if (painel != null) {
                return Response.ok(painel).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Painel solar não encontrado.")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao consultar painel solar: " + e.getMessage())
                    .build();
        }
    }
}
