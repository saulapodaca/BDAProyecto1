/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICuentaDepartamentoFachada {
    
     
    
    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws NegocioException;

    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws NegocioException;

    public void eliminarCuentaPorId(int id) throws NegocioException;

    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws NegocioException;
}
