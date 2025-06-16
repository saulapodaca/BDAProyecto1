/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.Negocio.CuentaDepartamentoNegocio;
import itson.sistemasgestorprestamos.Negocio.ICuentaDepartamentoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.CuentaDepartamentoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.ICuentaDepartamentoDAO;
import java.util.List;

/**
 *
 * @author adell
 */
public class CuentaDepartamentoFachada implements ICuentaDepartamentoFachada {

    private ICuentaDepartamentoNegocio CuentaDepartamentoNegocio;

    public CuentaDepartamentoFachada() {
        IConexionBD conexion = new ConexionBD();
        ICuentaDepartamentoDAO cuentaDepartamentoDAO = new CuentaDepartamentoDAO(conexion);

        this.CuentaDepartamentoNegocio = new CuentaDepartamentoNegocio(cuentaDepartamentoDAO);
    }

    @Override
    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws NegocioException {
        return CuentaDepartamentoNegocio.registrarCuenta(cuentaDepartamento);
    }

    @Override
    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws NegocioException {
        return CuentaDepartamentoNegocio.buscarCuentasDepartamentosPorId(filtro);
    }

    @Override
    public void eliminarCuentaPorId(int id) throws NegocioException {
         CuentaDepartamentoNegocio.eliminarCuentaPorId(id);
    }

    @Override
    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws NegocioException {
        return CuentaDepartamentoNegocio.buscarCuentaPorId(id);
    }

}
