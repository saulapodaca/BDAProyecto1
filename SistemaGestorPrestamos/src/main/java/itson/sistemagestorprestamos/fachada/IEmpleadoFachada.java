/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import itson.sistemasgestorprestamos.persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author adell
 */
public interface IEmpleadoFachada {

    public EmpleadosDominio buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws NegocioException;

    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws NegocioException;

    public EmpleadosDominio buscarEmpleadoPorId(int id) throws NegocioException;

    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws NegocioException;
}
