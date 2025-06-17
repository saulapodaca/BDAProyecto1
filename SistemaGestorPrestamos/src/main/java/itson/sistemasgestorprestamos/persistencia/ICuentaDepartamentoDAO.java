/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasDepartamentoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICuentaDepartamentoDAO {

    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws PersistenciaException;

    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws PersistenciaException;

    public void eliminarCuentaPorId(int id) throws PersistenciaException;
    
    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws PersistenciaException;
    
    public int contarTotalCuentas(FiltroDTO filtro) throws PersistenciaException;
    
    public List<TablaCuentasDepartamentoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;
}
