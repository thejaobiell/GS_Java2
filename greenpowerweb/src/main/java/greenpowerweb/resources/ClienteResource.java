package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.ClienteBO;
import greenpowerweb.model.vo.ClienteVO;

import java.sql.SQLException;
import java.util.List;

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

    // Cadastrar (POST)
    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarCliente(ClienteVO cliente, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            clienteBO.cadastrarCliente(cliente);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(cliente.getEmail_Cliente());
            return Response.created(builder.build())
                    .entity(cliente.toString()) 
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Atualizar (PUT)
    @PUT
    @Path("/atualizar/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(ClienteVO cliente, @PathParam("email") String email) {
        try {
            clienteBO.atualizarCliente(cliente);
            return Response.ok("Cliente atualizado com sucesso!" + cliente.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Deletar (DELETE)
    @DELETE
    @Path("/deletar/{email}")
    public Response deletarCliente(@PathParam("email") String email) {
        try {
            clienteBO.deletarCliente(email);
            return Response.ok("Cliente "+ email + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar cliente: " + e.getMessage())
                    .build();
        }
    }

    // Consultar (GET)
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        try {
            List<ClienteVO> clientes = clienteBO.listarClientes();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar clientes: " + e.getMessage())
                    .build();
        }
    }
    
    // Verificar login (GET)
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarLogin(@QueryParam("email") String email, @QueryParam("senha") String senha) {
        try {
            ClienteVO cliente = clienteBO.verificarLogin(email, senha);
            
            if (cliente != null) {
                return Response.ok(cliente).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Email ou senha incorretos. Tente Novamente")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao verificar login: " + e.getMessage())
                    .build();
        }
    }


}