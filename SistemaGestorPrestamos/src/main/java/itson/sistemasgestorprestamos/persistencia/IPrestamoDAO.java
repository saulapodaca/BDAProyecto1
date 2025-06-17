package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.util.List;

/**
 * Esta interfaz proporciona los métodos para gestionar préstamos en el sistema.
 *
 * @author Camila Zubía
 */
public interface IPrestamoDAO {

    /**
     * Guarda una nueva solicitud de préstamo en la base de datos.
     *
     * @param solicitud
     * @return Retorna un objeto PrestamosDominio con los datos del préstamo.
     * @throws PersistenciaException Lanza una PersistenciaException en caso de
     * error.
     */
    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws PersistenciaException;

    /**
     * Inserta un nuevo préstamo en la base de datos.
     *
     * @param prestamo
     * @return Retorna el objeto PrestamosDominio del préstamo registrado.
     * @throws PersistenciaException Lanza una PersistenciaException si no se
     * puede guardar.
     */
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws PersistenciaException;

    /**
     * Modifica el estatus de un préstamo en la base de datos.
     *
     * @param id
     * @param estatus
     * @return Retorna el préstamo actualizado.
     * @throws PersistenciaException Lanza una PersistenciaException si la
     * actualización falla.
     */
    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws PersistenciaException;

    /**
     * Obtiene un préstamo específico basado en su identificador.
     *
     * @param idPrestamo
     * @return Retorna el objeto PrestamosDominio si se encuentra el préstamo.
     * @throws PersistenciaException Lanza una PersistenciaException si el
     * préstamo no existe.
     */
    public PrestamosDominio buscarPorId(int idPrestamo) throws PersistenciaException;

    /**
     * Recupera una lista de préstamos según los filtros especificados.
     *
     * @param filtro
     * @return Retorna una lista de TablaPrestamosDTO con los préstamos
     * filtrados.
     * @throws PersistenciaException Lanza una PersistenciaException si ocurre
     * un error en la consulta.
     */
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Obtiene un reporte de préstamos aplicando múltiples filtros como fechas y
     * tipos de préstamo.
     *
     * @param filtro
     * @return Retorna una lista de ReportePrestamoDTO con los resultados.
     * @throws PersistenciaException Lanza una PersistenciaException si la
     * consulta falla.
     */
    public List<ReportePrestamoDTO> obtenerPrestamosFiltrados(filtroPrestamosDTO filtro) throws PersistenciaException;

    /**
     * Obtiene préstamos que tienen el estatus "Pagado" o "Abonado".
     *
     * @param filtro
     * @return Retorna una lista de TablaPrestamosDTO.
     * @throws PersistenciaException Lanza una PersistenciaException en caso de
     * error en la consulta.
     */
    public List<TablaPrestamosDTO> buscarTablaAbonar(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Cuenta la cantidad total de préstamos en la base de datos según los
     * filtros proporcionados.
     *
     * @param filtro
     * @return Retorna un número entero con el total de registros encontrados.
     * @throws PersistenciaException Lanza una PersistenciaException si la
     * consulta falla
     */
    public int contarTotalPrestamos(FiltroDTO filtro) throws PersistenciaException;

    /**
     * Realiza el pago de un préstamo mediante una operación en la base de
     * datos.
     *
     * @param idCuentaEmpleado
     * @param idCuentaDepartamento
     * @param idPrestamo
     * @throws PersistenciaException No retorna un valor, pero lanza una
     * PersistenciaException si hay errores en el pago.
     */
    public void pagarPrestamo(int idCuentaEmpleado, int idCuentaDepartamento, int idPrestamo) throws PersistenciaException;
}
