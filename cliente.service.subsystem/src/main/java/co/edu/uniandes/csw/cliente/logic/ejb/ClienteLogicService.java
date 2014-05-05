
package co.edu.uniandes.csw.cliente.logic.ejb;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Default
@Stateless
@LocalBean
public class ClienteLogicService extends _ClienteLogicService implements IClienteLogicService {

    public ClienteDTO getClienteId(String name) {
        return persistance.getClienteId(name);
    }

}