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
        try{
            connection = this.conexion.crearConexion();
            connection.setAutoCommit(false);
            String query = """
                           INSERT INTO abonos
                           (monto,
                           id_jefe,
                           id_prestamo)
                           VALUES(?,?,?)
                           """;
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setFloat(1, abono.getMonto());
            statement.setInt(2, abono.getIdJefe());
            statement.setInt(3, abono.getIdPrestamo());
            
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se registro el abono");
            }
            ResultSet set = statement.getGeneratedKeys();
            if (!set.next()) {
                connection.rollback();
                throw new PersistenciaException("No se obtuvo el ID del abono insertado");
            }

            int id = set.getInt(1);
            String query2 = """
                            UPDATE prestamos
                            SET
                            monto = monto - ?
                            WHERE id = ?
                            """;
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setFloat(1, abono.getMonto());
            statement2.setInt(2, abono.getIdPrestamo());
            
            statement2.executeUpdate();
            
            connection.commit();
            AbonoDominio abonoBuscado = this.buscarPorID(id);
            return abonoBuscado;
            
        }catch (SQLException ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                throw new PersistenciaException("Error al hacer rollback: " + rollbackEx.getMessage());
            }
            throw new PersistenciaException("Ocurrió un error al registrar el abono: " + ex.getMessage());
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
            while (set.next()) {
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
        try{
            Connection connection = this.conexion.crearConexion();
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
            String filtroConLike = "%" + filtro.getFiltro() + "%";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, filtroConLike);
            statement.setString(2, filtroConLike);
            statement.setString(3, filtroConLike);
            statement.setString(4, filtroConLike);
            statement.setInt(5, filtro.getLimit());
            statement.setInt(6, filtro.getOffset());
            
            ResultSet set = statement.executeQuery();
            List<TablaAbonosDTO> abonos = null;
            while (set.next()) {

                if (abonos == null) {
                    abonos = new ArrayList<>();
                }

                abonos.add(this.convertirTablaAbonosDTO(set));
            }

            set.close();
            statement.close();
            connection.close();
            if (abonos == null) {
                throw new PersistenciaException("No se encontraron abonos");
            }

            return abonos;

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrio un error al buscar una tabla " + ex.getMessage());
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
