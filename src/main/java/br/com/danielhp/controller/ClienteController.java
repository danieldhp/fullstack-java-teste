package br.com.danielhp.controller;

import br.com.danielhp.dao.ClienteDao;
import br.com.danielhp.entitybean.Cliente;
import br.com.danielhp.filtro.FiltroAutoSugest;
import br.com.danielhp.mapper.ClienteMapper;
import br.com.danielhp.util.Info;
import br.com.danielhp.vo.ClienteVo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Daniel on 20/01/2017.
 */
public class ClienteController {
    private static final Logger logger = Logger.getLogger(ClienteController.class.getName());

    public Info salvarCliente(ClienteVo clienteVo) {
        Info info;

        try {
            Cliente cliente = ClienteMapper.convertToEntity(clienteVo);

            ClienteDao clienteDao = ClienteDao.getInstance();
            if (cliente.getId() != null && cliente.getId() > 0) {
                clienteDao.merge(cliente);
            } else {
                clienteDao.persist(cliente);
            }

            clienteVo = ClienteMapper.convertToVo(cliente);

            info = new Info(Info.TIPO_SUCCESS, "Salvo com sucesso", clienteVo);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
            info = new Info(Info.TIPO_DANGER, "Erro ao salvar", null);
        }
        return info;
    }

    public List<ClienteVo> listarClientes(FiltroAutoSugest filtro) {
        List<ClienteVo> listaClientes = null;

        try {
            ClienteDao clienteDao = ClienteDao.getInstance();
            List<Cliente> lista = clienteDao.list(filtro.getPesquisa());

            listaClientes = ClienteMapper.convertToListVo(lista);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return listaClientes;
    }
}
