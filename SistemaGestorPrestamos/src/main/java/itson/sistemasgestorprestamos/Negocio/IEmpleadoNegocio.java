/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.util.List;

/**
 *
 * @author adell
 */
public interface IEmpleadoNegocio {

    public SesionEmpleadoDTO buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws NegocioException;

    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws NegocioException;

    public EmpleadosDominio buscarEmpleadoPorId(int id) throws NegocioException;

    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;

    public int contarTotalEmpleados(FiltroDTO filtro) throws NegocioException;

    public boolean esJefe(int idEmpleado) throws NegocioException;

}
