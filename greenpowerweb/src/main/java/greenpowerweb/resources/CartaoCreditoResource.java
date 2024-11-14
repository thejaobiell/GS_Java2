package greenpowerweb.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import greenpowerweb.model.bo.CartaoCreditoBO;
import greenpowerweb.model.vo.CartaoCreditoVO;

import java.sql.SQLException;
import java.util.List;

@Path("/cartao")
public class CartaoCreditoResource {

    private CartaoCreditoBO cartaoCreditoBO;

    public CartaoCreditoResource() {
        try {
            this.cartaoCreditoBO = new CartaoCreditoBO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao inicializar CartaoCreditoBO: " + e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarCartao(CartaoCreditoVO cartao, @Context UriInfo uriInfo) {
        try {
            cartaoCreditoBO.cadastrarCartao(cartao);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(cartao.getNumero_cartao()));
            return Response.created(builder.build())
                    .entity("Cartão cadastrado com sucesso! " + cartao.toString())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar cartão: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{numero_cartao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCartao(CartaoCreditoVO cartao, @PathParam("numero_cartao") String numeroCartao) {
        try {
            cartao.setNumero_cartao(numeroCartao);
            cartaoCreditoBO.atualizarCartao(cartao);
            return Response.ok("Cartão atualizado com sucesso! " + cartao.toStringEdit())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cartão: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{numero_cartao}")
    public Response deletarCartao(@PathParam("numero_cartao") String numeroCartao) {
        try {
            cartaoCreditoBO.excluirCartao(numeroCartao);
            return Response.ok("Cartão " + numeroCartao + " deletado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar cartão: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCartoes() {
        try {
            List<CartaoCreditoVO> cartoes = cartaoCreditoBO.listarCartoes();
            return Response.ok(cartoes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar cartões: " + e.getMessage())
                    .build();
        }
    }
}
