/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.util.List;


/**
 *
 * @author adell
 */
public interface IEmpleadoDAO {
    
       public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws PersistenciaException;
       public EmpleadosDominio buscarEmpleadoPorId(int id) throws PersistenciaException;
       public List<TablaEmpleadoDTO> buscarTabla (FiltroDTO filtro) throws PersistenciaException;
}
