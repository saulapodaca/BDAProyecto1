package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import java.util.List;

/**
 *
 * @author saula
 */
public interface ICuentaEmpleadoDAO {

    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuentaEmpleado) 
            throws PersistenciaException;

    public void eliminarCuentaPorId(int id) throws PersistenciaException;
    
    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) 
            throws PersistenciaException;
    
    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws PersistenciaException;
}
