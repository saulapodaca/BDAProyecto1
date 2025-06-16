/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
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

    @Override
    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuentaEmpleado) throws NegocioException {
        try {
            return CuentaDAO.registrarCuenta(cuentaEmpleado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void eliminarCuentaPorId(int id) throws NegocioException {
        try {
            CuentaDAO.eliminarCuentaPorId(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws NegocioException {
        try {
            return CuentaDAO.obtenerIdCuentaDepartamentoPorClabe(clabe);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     @Override
    public int obtenerIdCuentaEmpleadoPorClabe(String clabe) throws NegocioException {
        try {
            return CuentaDAO.obtenerIdCuentaEmpleadoPorClabe(clabe);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
