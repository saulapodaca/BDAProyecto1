package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasEmpleadoDTO;
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
    
    public int obtenerIdCuentaEmpleadoPorClabe(String clabe) throws PersistenciaException;
    
    public List<TablaCuentasEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;
    
    public int contarTotalCuentas(FiltroDTO filtro) throws PersistenciaException;
}
