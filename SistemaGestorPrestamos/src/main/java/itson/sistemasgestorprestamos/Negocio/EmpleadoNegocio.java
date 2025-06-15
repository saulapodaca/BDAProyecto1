/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.IEmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell
 */
public class EmpleadoNegocio implements IEmpleadoNegocio {

    private IEmpleadoDAO EmpleadoDAO;

    public EmpleadoNegocio(IEmpleadoDAO EmpleadoDAO) {
        this.EmpleadoDAO = EmpleadoDAO;
    }
    
    
    @Override
    public boolean esJefe(int idEmpleado) throws NegocioException {
        try {
            return EmpleadoDAO.esJefe(idEmpleado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
    @Override
    public SesionEmpleadoDTO buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws NegocioException {
        try {
            return EmpleadoDAO.buscarPorUsuarioYContraseña(empleado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public int contarTotalEmpleados(FiltroDTO filtro) throws NegocioException {
        try {
            return EmpleadoDAO.contarTotalEmpleados(filtro);
        } catch (PersistenciaException ex) {
            Logger.getLogger(EmpleadoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws NegocioException {
        try {
            this.parametroNulo(empleado);
            return EmpleadoDAO.guardar(empleado);
        } catch (PersistenciaException e) {
            throw new NegocioException("error" + e.getMessage());
        }

    }

    @Override
    public EmpleadosDominio buscarEmpleadoPorId(int id) throws NegocioException {
        this.idInvalido(id);
        EmpleadosDominio empleado = this.buscarEmpleadoPorId(id);
        return empleado;
    }

    @Override
    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        try {
            this.ExcepcionesListas(filtro);
            List<TablaEmpleadoDTO> tablaEmpleado = this.EmpleadoDAO.buscarTabla(filtro);
            return tablaEmpleado;
        } catch (PersistenciaException e) {
            throw new NegocioException("error" + e.getMessage());
        }
    }

    /**
     * VALIDACIONES
     */
    private void parametroNulo(GuardarEmpleadoDTO empleado) throws NegocioException {

        if (empleado.getNombres().isEmpty()) {
            throw new NegocioException("Nombre vacio");
        }
        if (empleado.getApellidoPaterno().isEmpty()) {
            throw new NegocioException("Apellido paterno vacio");
        }

    }

    private void idInvalido(int id) throws NegocioException {
        if (id < 0) {
            throw new NegocioException("Id con valores negativos");
        }
    }

    private void ExcepcionesListas(FiltroDTO filtro) throws NegocioException {
        if (filtro.getLimit() < 0) {
            throw new NegocioException("Parametro invalido [Limite]");
        }
        if (filtro.getOffset() < 0) {
            throw new NegocioException("Lista vacia [Offset]");
        }
    }

    
    

}
