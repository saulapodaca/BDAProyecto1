/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.ICuentaEmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell
 */
public class CuentaEmpleadoNegocio implements ICuentaEmpleadoNegocio {
    
    private ICuentaEmpleadoDAO CuentaDAO;

    public CuentaEmpleadoNegocio(ICuentaEmpleadoDAO CuentaDAO) {
        this.CuentaDAO = CuentaDAO;
    }
    
    
    @Override
    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws NegocioException {
        try {
            return CuentaDAO.buscarCuentasEmpleadoPorId(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
