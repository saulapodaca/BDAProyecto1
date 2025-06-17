/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasEmpleadoDTO;
import itson.sistemasgestorprestamos.Negocio.CuentaEmpleadoNegocio;
import itson.sistemasgestorprestamos.Negocio.ICuentaEmpleadoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.CuentaEmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.ICuentaEmpleadoDAO;
import java.util.List;

/**
 *
 * @author adell
 */
public class CuentaEmpleadoFachada implements ICuentaEmpleadoFachada {

    private ICuentaEmpleadoNegocio CuentaEmpleadoNegocio;

    public CuentaEmpleadoFachada() {
        IConexionBD conexion = new ConexionBD();
        ICuentaEmpleadoDAO cuentaEmpleadoDAO= new  CuentaEmpleadoDAO(conexion);
        this.CuentaEmpleadoNegocio = new CuentaEmpleadoNegocio(cuentaEmpleadoDAO);
    }
    
  

    @Override
    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws NegocioException {
        return CuentaEmpleadoNegocio.buscarCuentasEmpleadoPorId(filtro);
    }

    @Override
    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws NegocioException {
        return CuentaEmpleadoNegocio.obtenerIdCuentaDepartamentoPorClabe(clabe);
    }

    @Override
    public List<TablaCuentasEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        return CuentaEmpleadoNegocio.buscarTabla(filtro);
    }

    @Override
    public int contarTotalCuentas(FiltroDTO filtro) throws NegocioException {
        return CuentaEmpleadoNegocio.contarTotalCuentas(filtro);
    }
   
}
