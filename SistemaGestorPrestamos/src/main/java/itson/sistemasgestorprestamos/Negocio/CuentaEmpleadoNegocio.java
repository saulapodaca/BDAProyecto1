/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasEmpleadoDTO;
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
            this.ExcepcionesListas(filtro);
            return CuentaDAO.buscarCuentasEmpleadoPorId(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuentaEmpleado) throws NegocioException {
        try {
            this.parametroNulo(cuentaEmpleado);
            return CuentaDAO.registrarCuenta(cuentaEmpleado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void eliminarCuentaPorId(int id) throws NegocioException {
        try {
            this.idInvalido(id);
            CuentaDAO.eliminarCuentaPorId(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws NegocioException {
        try {
            this.stringInvalido(clabe);
            return CuentaDAO.obtenerIdCuentaDepartamentoPorClabe(clabe);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     @Override
    public int obtenerIdCuentaEmpleadoPorClabe(String clabe) throws NegocioException {
        try {
            this.stringInvalido(clabe);
            return CuentaDAO.obtenerIdCuentaEmpleadoPorClabe(clabe);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<TablaCuentasEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            this.ExcepcionesListas(filtro);
            return CuentaDAO.buscarTabla(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int contarTotalCuentas(FiltroDTO filtro) throws NegocioException {
        try {
            this.ExcepcionesListas(filtro);
            return CuentaDAO.contarTotalCuentas(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaEmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void ExcepcionesListas(FiltroDTO filtro) throws NegocioException {
        if (filtro.getLimit() < 0) {
            throw new NegocioException("Parametro invalido [Limite]");
        }
        if (filtro.getOffset() < 0) {
            throw new NegocioException("Lista vacia [Offset]");
        }
    }
    
    private void idInvalido(int id)throws NegocioException{
        if (id <= 0) {
            throw new NegocioException("la id es invalida");
        }
    }
    
    private void stringInvalido(String s) throws NegocioException{
        if (s.trim().isEmpty()) {
            throw new NegocioException("el string es invalido");
        }
    }
    
    private void parametroNulo(RegistrarCuentaEmpleadoDTO cuentaEmpleado) throws NegocioException{
        if (cuentaEmpleado == null) {
            throw new NegocioException("el parametro es nulo");
        }
    }
}
