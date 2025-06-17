/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasDepartamentoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import itson.sistemasgestorprestamos.persistencia.ICuentaDepartamentoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell
 */
public class CuentaDepartamentoNegocio implements ICuentaDepartamentoNegocio {

    private ICuentaDepartamentoDAO CuentaDAO;

    public CuentaDepartamentoNegocio(ICuentaDepartamentoDAO CuentaDAO) {
        this.CuentaDAO = CuentaDAO;
    }

    @Override
    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws NegocioException {
        try {
            return CuentaDAO.registrarCuenta(cuentaDepartamento);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws NegocioException {
        try {
            return CuentaDAO.buscarCuentasDepartamentosPorId(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void eliminarCuentaPorId(int id) throws NegocioException {
        try {
            CuentaDAO.eliminarCuentaPorId(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws NegocioException {
        try {
            return CuentaDAO.buscarCuentaPorId(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int contarTotalCuentas(FiltroDTO filtro) throws NegocioException {
        try {
            return CuentaDAO.contarTotalCuentas(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<TablaCuentasDepartamentoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            return CuentaDAO.buscarTabla(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CuentaDepartamentoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
