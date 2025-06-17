/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.util.List;

/**
 * Interfaz para gestionar la persistencia de empleados en el sistema. Define
 * los métodos de acceso a la información de empleados.
 *
 * @author Adell
 */
public interface IEmpleadoDAO {

    /**
     * Busca un empleado en la base de datos utilizando su usuario y contraseña.
     *
     * @param empleado Objeto `LoginEmpleadoDTO` con las credenciales del
     * empleado.
     * @return Instancia de `SesionEmpleadoDTO` si el usuario y contraseña son
     * correctos.
     * @throws PersistenciaException Si el usuario no es encontrado o hay un
     * error en la consulta.
     */
    public SesionEmpleadoDTO buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws PersistenciaException;

    /**
     * Guarda un nuevo registro de empleado en la base de datos.
     *
     * @param empleado Objeto `GuardarEmpleadoDTO` con los datos del empleado a
     * registrar.
     * @return Instancia de `EmpleadosDominio` con los datos del empleado
     * guardado.
     * @throws PersistenciaException Si la inserción falla o ocurre un error en
     * la transacción.
     */
    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws PersistenciaException;

    /**
     * Busca un empleado por su identificador único en la base de datos.
     *
     * @param id Identificador del empleado.
     * @return Instancia de `EmpleadosDominio` si el empleado existe.
     * @throws PersistenciaException Si el empleado no existe o hay un error en
     * la consulta.
     */
    public EmpleadosDominio buscarEmpleadoPorId(int id) throws PersistenciaException;

    /**
     * Obtiene una lista de empleados según los criterios de filtro
     * proporcionados.
     *
     * @param filtro Objeto `FiltroDTO` con los criterios de búsqueda.
     * @return Lista de `TablaEmpleadoDTO` con los empleados filtrados.
     * @throws PersistenciaException Si ocurre un error en la consulta SQL.
     */
    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Cuenta el número total de empleados en la base de datos según los filtros
     * aplicados.
     *
     * @param filtro Objeto `FiltroDTO` con los criterios de búsqueda.
     * @return Número total de registros encontrados.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    public int contarTotalEmpleados(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Verifica si un empleado es jefe en la empresa.
     *
     * @param idEmpleado Identificador único del empleado.
     * @return `true` si el empleado es jefe, `false` en caso contrario.
     * @throws PersistenciaException Si ocurre un error en la consulta SQL.
     */
    public boolean esJefe(int idEmpleado) throws PersistenciaException;
}
