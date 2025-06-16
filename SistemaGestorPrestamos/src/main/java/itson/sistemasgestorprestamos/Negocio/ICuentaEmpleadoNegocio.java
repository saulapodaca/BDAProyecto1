/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface ICuentaEmpleadoNegocio {

    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws NegocioException;

    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuentaEmpleado) throws NegocioException;

    public void eliminarCuentaPorId(int id) throws NegocioException;

    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws NegocioException;

    public int obtenerIdCuentaEmpleadoPorClabe(String clabe) throws NegocioException;
}
