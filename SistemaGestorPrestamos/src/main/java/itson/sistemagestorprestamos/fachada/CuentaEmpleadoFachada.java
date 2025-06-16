/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.Negocio.ICuentaEmpleadoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public class CuentaEmpleadoFachada implements ICuentaEmpleadoFachada {
    
        private ICuentaEmpleadoNegocio CuentaEmpleadoNegocio;

    
    @Override
    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws NegocioException {
        return CuentaEmpleadoNegocio.buscarCuentasEmpleadoPorId(filtro);
    }
    
}
