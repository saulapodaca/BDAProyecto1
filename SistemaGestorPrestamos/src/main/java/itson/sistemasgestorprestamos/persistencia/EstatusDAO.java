/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.TablaEstatusDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.EstatusDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class EstatusDAO implements IEstatusDAO{
    
    private IConexionBD conexion;

    public EstatusDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public EstatusDominio buscarPorId(int id) throws PersistenciaException {
        Connection connection = null;
        try{
            connection = this.conexion.crearConexion();
            String query = """
                           SELECT 
                           id,
                           nombre,
                           fecha_hora,
                           id_jefe,
                           id_prestamo
                           FROM estatus
                           WHERE id = ?
                           """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet set = statement.executeQuery();
            EstatusDominio estatus = null;
            if(set.next()) {
                estatus = this.convertirEstatusDominio(set);
            }
            set.close();
            statement.close();
            connection.close();

            if (estatus == null) {
                throw new PersistenciaException("No se encontró el estatus con id " + id);
            }

            return estatus;
            
        }catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el estatus: " + ex.getMessage());
        }
    }

    @Override
    public List<TablaEstatusDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        try {
            connection = this.conexion.crearConexion();
            String query = """
                       SELECT 
                           id,
                           nombre,
                           fecha_hora,
                           id_jefe,
                           id_prestamo
                       FROM estatus
                       WHERE (CAST(nombre AS CHAR) LIKE ?
                              OR CAST(fecha_hora AS CHAR) LIKE ?
                              OR CAST(id_jefe AS CHAR) LIKE ?
                              OR CAST(id_prestamo AS CHAR) LIKE ?)
                       LIMIT ?
                       OFFSET ?
                       """;

            PreparedStatement statement = connection.prepareStatement(query);

            String textoFiltro = filtro.getFiltro() != null ? filtro.getFiltro().trim() : "";
            String filtroConLike = "%" + textoFiltro + "%";

            statement.setString(1, filtroConLike);
            statement.setString(2, filtroConLike);
            statement.setString(3, filtroConLike);
            statement.setString(4, filtroConLike);
            statement.setInt(5, filtro.getLimit());
            statement.setInt(6, filtro.getOffset());

            ResultSet set = statement.executeQuery();
            List<TablaEstatusDTO> estatus = new ArrayList<>();

            while (set.next()) {
                estatus.add(this.convertirTablaEstatusDTO(set));
            }

            set.close();
            statement.close();
            connection.close();

            if (estatus.isEmpty()) {
                throw new PersistenciaException("No se encontraron estatus.");
            }

            return estatus;

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar la tabla: " + ex.getMessage());
        }
    }
    
    @Override
    public int contarTotalEstatus(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            connection = this.conexion.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
            SELECT COUNT(es.id)
            FROM estatus AS es
            INNER JOIN prestamos AS p ON es.id_prestamo = p.id
            INNER JOIN cuentas_departamentos AS cd ON p.id_cuenta_departamento = cd.id
            INNER JOIN departamentos AS d ON cd.id_departamento = d.id
            WHERE 1=1
        """);

            List<Object> parametros = new ArrayList<>();

            String texto = filtro.getFiltro() != null ? filtro.getFiltro().trim() : "";
            if (!texto.isEmpty()) {
                String filtroTexto = "%" + texto + "%";
                queryBuilder.append("""
                AND (CAST(es.nombre AS CHAR) LIKE ?
                     OR CAST(es.fecha_hora AS CHAR) LIKE ?)
            """);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
            }

            if (filtro.getIdDepartamento() != null) {
                queryBuilder.append(" AND d.id = ?");
                parametros.add(filtro.getIdDepartamento());
            }

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < parametros.size(); i++) {
                Object param = parametros.get(i);
                int parameterIndex = i + 1;

                if (param instanceof String) {
                    preparedStatement.setString(parameterIndex, (String) param);
                } else if (param instanceof Integer) {
                    preparedStatement.setInt(parameterIndex, (Integer) param);
                }
            }

            resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                return resultset.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un problema al contar estatus: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                System.err.println("Error al cerrar recursos en contarTotalEstatus: " + closeEx.getMessage());
            }
        }
    }
    
    private EstatusDominio convertirEstatusDominio(ResultSet set) throws SQLException{
        int id = set.getInt("id");
        String txtEstatus = set.getString("nombre");
        Estatus estatus = Estatus.fromString(txtEstatus);
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        int idJefe = set.getInt("id_jefe");
        int idPrestamo = set.getInt("id_prestamo");
        return new EstatusDominio(id, estatus, fechaHora, idJefe, idPrestamo);
    }
    
    private TablaEstatusDTO convertirTablaEstatusDTO(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        String txtEstatus = set.getString("nombre");
        Estatus estatus = Estatus.fromString(txtEstatus);
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        int idJefe = set.getInt("id_jefe");
        int idPrestamo = set.getInt("id_prestamo");
        return new TablaEstatusDTO(id, estatus, fechaHora, idJefe, idPrestamo);
    }
}
