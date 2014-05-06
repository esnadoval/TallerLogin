
package co.edu.uniandes.csw.cliente.logic.mock;
import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

@Alternative
@Singleton
public class ClienteMockLogicService extends _ClienteMockLogicService implements IClienteLogicService {
	

    public ClienteDTO getClienteId(String name) {
        return null;
    }

}