package br.com.danielhp.api;

import br.com.danielhp.controller.CalculoImpostosController;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.CalculoImpostoVo;
import br.com.danielhp.vo.InformacoesCalculoVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Daniel on 22/01/2017.
 */
@Path("calculoImpostos")
public class CalculoImpostosApi {

    @POST
    @Path("calcularImpostos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info calcularImpostos(InformacoesCalculoVo informacoesCalculo) {
        CalculoImpostosController controller = new CalculoImpostosController();
        return controller.calcularImpostos(informacoesCalculo);
    }

    @POST
    @Path("listarCalculosImpostos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CalculoImpostoVo> listarCalculosImpostos(InformacoesCalculoVo informacoesCalculo) {
        CalculoImpostosController controller = new CalculoImpostosController();
        return controller.listarCalculosImpostos(informacoesCalculo);
    }

    @POST
    @Path("alterarStatusImposto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info alterarStatusImposto(CalculoImpostoVo calculoImposto) {
        CalculoImpostosController controller = new CalculoImpostosController();
        return controller.alterarStatusImposto(calculoImposto);
    }
}
