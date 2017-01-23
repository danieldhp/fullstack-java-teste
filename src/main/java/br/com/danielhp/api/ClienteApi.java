package br.com.danielhp.api;

import br.com.danielhp.controller.ClienteController;
import br.com.danielhp.filtro.FiltroAutoSugest;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.ClienteVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Daniel on 17/01/2017.
 */
@Path("cliente")
public class ClienteApi {

    @POST
    @Path("listarClientes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteVo> listarClientes(FiltroAutoSugest filtro) {
        ClienteController controller = new ClienteController();
        return controller.listarClientes(filtro);
    }

    @POST
    @Path("salvarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarCliente(ClienteVo clienteVo) {
        ClienteController controller = new ClienteController();
        return controller.salvarCliente(clienteVo);
    }
}