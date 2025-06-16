/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemagestorprestamos.utilidades.SesionIniciada;
import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class PrestamoDAO implements IPrestamoDAO {

    private IConexionBD conexion;

    public PrestamoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public PrestamosDominio guardarSolicitud(SolicitudPrestamoDTO solicitud) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            String query = """
                           INSERT INTO prestamos
                           (fecha_hora,
                           monto,
                           estatusActual,
                           id_tipo,
                           id_cuenta_empleado)
                           VALUES(?,?,?,?,?)
                           """;
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            LocalDateTime fechaCreacion = LocalDateTime.now();
            Timestamp timestampParaDB = Timestamp.valueOf(fechaCreacion);

            statement.setTimestamp(1, timestampParaDB);
            statement.setDouble(2, solicitud.getMonto());
            statement.setString(3, solicitud.getEstatus());
            statement.setInt(4, solicitud.getIdTipo());
            statement.setInt(5, solicitud.getIdCuentaEmpleado());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se registro la solicitud del prestamo");
            }
            ResultSet set = statement.getGeneratedKeys();

            if (set.next()) {
                int idGenerado = set.getInt(1);
                return this.buscarPorId(idGenerado);
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al solicitar el prestamo" + ex.getMessage());
        }
        return null;

    }

    @Override
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            String query = """
                           INSERT INTO prestamos
                           (monto,
                           id_tipo,
                           id_cuenta_departamento,
                           id_cuenta_empleado)
                           VALUES(?,?,?,?,?)
                           """;
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setFloat(1, prestamo.getMonto());
            statement.setInt(2, prestamo.getTipoPrestamo());
            statement.setInt(3, prestamo.getCuentaDepartamento());
            statement.setInt(4, prestamo.getCuentaEmpleado());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se registro el prestamo");
            }
            ResultSet set = statement.getGeneratedKeys();

            if (set.next()) {
                int idGenerado = set.getInt(1);
                return this.buscarPorId(idGenerado);
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el abono: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public PrestamosDominio cambiarEstatus(int id, Estatus estatus) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            connection.setAutoCommit(false);
            String query = """
                           UPDATE prestamos
                           SET estatusActual = ?
                           WHERE id = ?
                           """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estatus.toString());
            statement.setInt(2, id);
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas == 0) {
                connection.rollback();
                throw new PersistenciaException("No se actualizó el monto del préstamo.");
            }
            
            String query2 ="""
                           INSERT INTO estatus
                           (nombre,
                           fecha_hora,
                           id_jefe,
                           id_prestamo)
                           VALUES(?,?,?,?)
                           """;
            PreparedStatement statement2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            statement2.setString(1, estatus.toString());
            statement2.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement2.setInt(3, SesionIniciada.getInstancia().getEmpleado().getId());
            statement2.setInt(4, id);
            statement2.executeUpdate(query2);
            
            connection.commit();
            
            return this.buscarPorId(id);
            
        }catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    throw new PersistenciaException("Error al hacer rollback: " + rollbackEx.getMessage());
                }
            }
            throw new PersistenciaException("Ocurrió un error al registrar el abono: " + ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new PersistenciaException("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    @Override
    public PrestamosDominio buscarPorId(int idPrestamo) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            String query = """
                           SELECT 
                           id,
                           fecha_hora,
                           monto,
                           estatusActual,
                           id_tipo,
                           id_cuenta_departamento,
                           id_cuenta_empleado
                           FROM prestamos
                           WHERE id = ?
                           """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPrestamo);

            ResultSet set = statement.executeQuery();
            PrestamosDominio prestamo = null;
            while (set.next()) {
                prestamo = this.convertirPrestamoDominio(set);
            }

            set.close();
            statement.close();
            connection.close();

            if (prestamo == null) {
                throw new PersistenciaException("No se encontró el prestamo con id " + idPrestamo);
            }

            return prestamo;

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el abono: " + ex.getMessage());
        }
    }

    @Override
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {

        return null;

    }

    private PrestamosDominio convertirPrestamoDominio(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        float monto = set.getFloat("monto");
        String txtEstatus = set.getString("estatusActual");
        Estatus estatus = Estatus.fromString(txtEstatus);
        int idTipo = set.getInt("id_tipo");
        int idCuentaDepa = set.getInt("id_cuenta_departamento");
        int idCuentaEmpleado = set.getInt("id_cuenta_empleado");
        return new PrestamosDominio(id, fechaHora, monto, estatus, idTipo, idCuentaDepa, idCuentaEmpleado);
    }

}
