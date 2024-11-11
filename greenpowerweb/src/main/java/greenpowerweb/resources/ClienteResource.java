package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.ClienteBO;
import greenpowerweb.model.vo.ClienteVO;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/cliente")
public class ClienteResource {
    
    private ClienteBO clienteBO;
    
    public ClienteResource() {
        try {
            this.clienteBO = new ClienteBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar ClienteBO: " + e.getMessage());
        }
    }

    // Inserir (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Para retornar o cliente em JSON
    public Response cadastrarCliente(ClienteVO cliente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            clienteBO.cadastrarCliente(cliente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(cliente.getEmail_Cliente());
            return Response.created(builder.build())
                    .entity(cliente.toString()) // Retorna as informações do cliente cadastrado
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Para retornar o cliente atualizado em JSON
    public Response atualizarCliente(ClienteVO cliente, @PathParam("email") String email) {
        try {
            clienteBO.atualizarCliente(cliente);
            return Response.ok(cliente.toString()) // Retorna as informações do cliente atualizado
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/{email}")
    public Response deletarCliente(@PathParam("email") String email) {
        try {
            clienteBO.deletarCliente(email);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        try {
            ArrayList<ClienteVO> clientes = clienteBO.listarClientes();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar clientes: " + e.getMessage())
                    .build();
        }
    }
}
