package br.com.greenpower.resource;

import br.com.greenpower.connection.ConnDAO;
import br.com.greenpower.model.bo.UsuarioBO;
import br.com.greenpower.model.vo.UsuarioVO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO;

    public UsuarioResource() {
        try {
            Connection conn = new ConnDAO().conexao();
            usuarioBO = new UsuarioBO(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioVO usuarioVO) {
        try {
            usuarioBO.cadastrarUsuario(usuarioVO.getUsuario(), usuarioVO.getSenha());
            return Response.status(Response.Status.CREATED)
                           .entity("{\"Mensagem\":\"Usuário cadastrado com sucesso\"}")
                           .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"ALERTA\":\"Erro ao cadastrar usuário\"}")
                           .build();
        }
    }


    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        try {
            List<UsuarioVO> usuarios = usuarioBO.listarUsuarios();
            if (usuarios.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"ALERTA\":\"Nenhum usuário encontrado\"}")
                               .build();
            }
            return Response.status(Response.Status.OK)
                           .entity(usuarios)
                           .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"ALERTA\":\"Erro ao listar usuários\"}")
                           .build();
        }
    }
    
    @PUT
    @Path("/atualizar/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("usuario") String usuario, UsuarioVO usuarioVO) {
        try {
            usuarioBO.atualizarUsuario(usuario, usuarioVO.getSenha());
            return Response.status(Response.Status.OK)
                           .entity("{\"Mensagem\":\"Usuário atualizado com sucesso\"}")
                           .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"ALERTA\":\"Erro ao atualizar usuário\"}")
                           .build();
        }
    }

    @DELETE
    @Path("/deletar/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarUsuario(@PathParam("usuario") String usuario) {
        try {
            usuarioBO.deletarUsuario(usuario);
            return Response.status(Response.Status.OK)
                           .entity("{\"Mensagem\":\"Usuário deletado com sucesso\"}")
                           .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"ALERTA\":\"Erro ao deletar usuário\"}")
                           .build();
        }
    }

}
