/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.Negocio.EmpleadoNegocio;
import itson.sistemasgestorprestamos.Negocio.IEmpleadoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.EmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import itson.sistemasgestorprestamos.persistencia.IEmpleadoDAO;
import java.util.List;

/**
 *
 * @author adell
 */
public class empleadoFachada implements IEmpleadoFachada {

    private IEmpleadoNegocio empleadoNegocio;
    
    public empleadoFachada() {
        IConexionBD conexion = new ConexionBD();
        IEmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
        this.empleadoNegocio = new EmpleadoNegocio(empleadoDAO);
    }
    
    
    @Override
    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws NegocioException {
        return this.empleadoNegocio.guardar(empleado);
    }

    @Override
    public EmpleadosDominio buscarEmpleadoPorId(int id) throws NegocioException {
        return this.empleadoNegocio.buscarEmpleadoPorId(id);
    }

    @Override
    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        return this.empleadoNegocio.buscarTabla(filtro);
    }

    @Override
    public EmpleadosDominio buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws NegocioException {
        return this.empleadoNegocio.buscarPorUsuarioYContraseña(empleado);
    }

}
