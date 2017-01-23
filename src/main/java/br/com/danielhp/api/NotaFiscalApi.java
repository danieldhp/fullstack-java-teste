package br.com.danielhp.api;

import br.com.danielhp.controller.NotaFiscalController;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.NotaFiscalVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Daniel on 20/01/2017.
 */
@Path("notaFiscal")
public class NotaFiscalApi {

    @POST
    @Path("listarNotasFiscais")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<NotaFiscalVo> listarNotasFiscais() {
        NotaFiscalController controller = new NotaFiscalController();
        return controller.listarNotasFiscais();
    }

    @POST
    @Path("salvarNotaFiscal")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarNotaFiscal(NotaFiscalVo notaFiscalVo) {
        NotaFiscalController controller = new NotaFiscalController();
        return controller.salvarNotaFiscal(notaFiscalVo);
    }
}
