/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasEmpleadoDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICuentaEmpleadoFachada {

    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws NegocioException;
    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws NegocioException;
    public List<TablaCuentasEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
    public int contarTotalCuentas(FiltroDTO filtro) throws NegocioException;
    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuenta) throws NegocioException;

}
