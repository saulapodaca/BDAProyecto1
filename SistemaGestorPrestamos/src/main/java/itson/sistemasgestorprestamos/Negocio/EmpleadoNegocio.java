/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.IEmpleadoDAO;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;

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
    public EmpleadosDominio guardar(GuardarEmpleadoDTO alumno) throws NegocioException {
        try {
            this.parametroNulo(alumno);
            return EmpleadoDAO.guardar(alumno);
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
            throw new NegocioException ("error"+ e.getMessage());
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
