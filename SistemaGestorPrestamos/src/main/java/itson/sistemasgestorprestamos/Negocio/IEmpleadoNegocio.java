/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface IEmpleadoNegocio {

    public EmpleadosDominio guardar(GuardarEmpleadoDTO alumno) throws NegocioException;

    public EmpleadosDominio buscarEmpleadoPorId(int id) throws NegocioException;

    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro)throws NegocioException;
}
