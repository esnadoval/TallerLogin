
package co.edu.uniandes.csw.cliente.logic.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;

public interface IClienteLogicService extends _IClienteLogicService {
     public ClienteDTO getClienteId(String name);
}