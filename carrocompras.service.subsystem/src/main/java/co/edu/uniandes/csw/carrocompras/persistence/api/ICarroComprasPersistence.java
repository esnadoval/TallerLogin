
package co.edu.uniandes.csw.carrocompras.persistence.api;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import java.util.List;

public interface ICarroComprasPersistence extends _ICarroComprasPersistence {
    public List<CarroComprasDTO> getCarroComprassCliente(Long id);
}