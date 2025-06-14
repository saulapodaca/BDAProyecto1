/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;

/**
 *
 * @author adell
 */
public interface IEmpleadoDAO {
    
        void guardar(GuardarEmpleadoDTO alumno) throws PersistenciaException;

}
