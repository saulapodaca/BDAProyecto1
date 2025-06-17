/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaAbonosDTO;
import itson.sistemasgestorprestamos.dominios.AbonoDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class AbonoDAO implements IAbonoDAO{
    
    private IConexionBD conexion;

    public AbonoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public AbonoDominio registrarAbono(RegistrarAbonoDTO abono) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            connection.setAutoCommit(false);
            
            String validarMontoQuery = "SELECT monto FROM prestamos WHERE id = ?";
            try (PreparedStatement validarStmt = connection.prepareStatement(validarMontoQuery)) {
                validarStmt.setInt(1, abono.getIdPrestamo());
                try (ResultSet rs = validarStmt.executeQuery()) {
                    if (rs.next()) {
                        float montoActual = rs.getFloat("monto");
                        if (abono.getMonto() > montoActual) {
                            connection.rollback();
                            throw new PersistenciaException("El monto del abono excede el monto restante del préstamo.");
                        }
                    } else {
                        connection.rollback();
                        throw new PersistenciaException("No se encontró el préstamo con ID " + abono.getIdPrestamo());
                    }
                }
            }

            String insertAbonoQuery = """
                                       INSERT INTO abonos 
                                      (monto, 
                                      id_jefe,
                                      id_prestamo,
                                      fecha_hora)
                                      VALUES (?, ?, ?, now());
        """;
            int idAbonoGenerado;
            try (PreparedStatement insertStmt = connection.prepareStatement(insertAbonoQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setFloat(1, abono.getMonto());
                insertStmt.setInt(2, abono.getIdJefe());
                insertStmt.setInt(3, abono.getIdPrestamo());

                int filasAfectadas = insertStmt.executeUpdate();
                if (filasAfectadas == 0) {
                    connection.rollback();
                    throw new PersistenciaException("No se registró el abono.");
                }

                try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                    if (!generatedKeys.next()) {
                        connection.rollback();
                        throw new PersistenciaException("No se obtuvo el ID del abono insertado.");
                    }
                    idAbonoGenerado = generatedKeys.getInt(1);
                }
            }

            String updatePrestamoQuery = """
                                         UPDATE prestamos 
                                        SET monto = monto - ? 
                                        WHERE id = ?
                                        """;
            try (PreparedStatement updateStmt = connection.prepareStatement(updatePrestamoQuery)) {
                updateStmt.setFloat(1, abono.getMonto());
                updateStmt.setInt(2, abono.getIdPrestamo());

                int filasActualizadas = updateStmt.executeUpdate();
                if (filasActualizadas == 0) {
                    connection.rollback();
                    throw new PersistenciaException("No se actualizó el monto del préstamo.");
                }
            }

            connection.commit();

            return this.buscarPorID(idAbonoGenerado);

        } catch (SQLException ex) {
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
    public AbonoDominio buscarPorID(int id) throws PersistenciaException {
        Connection connection = null;
        try{
            connection = this.conexion.crearConexion();
            String query = """
                           SELECT 
                           id,
                           fecha_hora,
                           monto,
                           id_jefe,
                           id_prestamo
                           FROM abonos
                           WHERE id = ?
                           """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet set = statement.executeQuery();
            AbonoDominio abono = null;
            if(set.next()) {
                abono = this.convertirAbonoDominio(set);
            }
            
            set.close();
            statement.close();
            connection.close();

            if (abono == null) {
                throw new PersistenciaException("No se encontró el abono con id " + id);
            }

            return abono;
            
        }catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el abono: " + ex.getMessage());
        }
    }

    @Override
    public List<TablaAbonosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        String query = """
                   SELECT 
                   id,
                   fecha_hora,
                   monto,
                   id_jefe,
                   id_prestamo
                   FROM abonos
                   WHERE ( CAST(fecha_hora AS CHAR) LIKE ?
                       OR CAST(monto AS CHAR) LIKE ?
                       OR CAST(id_jefe AS CHAR) LIKE ?
                       OR CAST(id_prestamo AS CHAR) LIKE ?)
                   LIMIT ?
                   OFFSET ?
                   """;

        List<TablaAbonosDTO> abonos = new ArrayList<>();
        String filtroConLike = "%" + filtro.getFiltro() + "%";

        try (Connection connection = this.conexion.crearConexion(); 
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, filtroConLike);
            statement.setString(2, filtroConLike);
            statement.setString(3, filtroConLike);
            statement.setString(4, filtroConLike);
            statement.setInt(5, filtro.getLimit());
            statement.setInt(6, filtro.getOffset());

            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    abonos.add(this.convertirTablaAbonosDTO(set));
                }
            }

            if (abonos.isEmpty()) {
                throw new PersistenciaException("No se encontraron abonos");
            }

            return abonos;

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar la tabla de abonos: " + ex.getMessage());
        }
    }
    
    private AbonoDominio convertirAbonoDominio(ResultSet set) throws SQLException{
        int id = set.getInt("id");
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        float monto = set.getFloat("monto");
        int idJefe = set.getInt("id_jefe");
        int idPrestamo = set.getInt("id_prestamo");
        return new AbonoDominio(id, fechaHora, monto, idJefe, idPrestamo);
    }

    private TablaAbonosDTO convertirTablaAbonosDTO(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        Float monto = set.getFloat("monto");
        int idJefe = set.getInt("id_jefe");
        int idPrestamo = set.getInt("id_prestamo");
        return new TablaAbonosDTO(id, fechaHora, monto, idJefe, idPrestamo);
    }
}
