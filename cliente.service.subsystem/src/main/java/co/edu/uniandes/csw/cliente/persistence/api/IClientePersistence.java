
package co.edu.uniandes.csw.cliente.persistence.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;

public interface IClientePersistence extends _IClientePersistence {
     public ClienteDTO getClienteId(String name);
}