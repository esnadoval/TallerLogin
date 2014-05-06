
package co.edu.uniandes.csw.carrocompras.logic.api;

import co.edu.uniandes.csw.carrocompras.logic.dto.CarroComprasDTO;
import java.util.List;

public interface ICarroComprasLogicService extends _ICarroComprasLogicService {
    public List<CarroComprasDTO> getCarroComprassCliente(String name);
}