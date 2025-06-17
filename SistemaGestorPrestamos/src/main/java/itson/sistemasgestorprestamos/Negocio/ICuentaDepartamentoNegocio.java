/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasDepartamentoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICuentaDepartamentoNegocio {

    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws NegocioException;

    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws NegocioException;

    public void eliminarCuentaPorId(int id) throws NegocioException;

    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws NegocioException;
    
    public int contarTotalCuentas(FiltroDTO filtro) throws NegocioException;
    
    public List<TablaCuentasDepartamentoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
}
