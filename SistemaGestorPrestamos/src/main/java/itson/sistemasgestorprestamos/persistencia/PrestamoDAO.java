/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemagestorprestamos.utilidades.SesionIniciada;
import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.SolicitudPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void pagarPrestamo(int idCuentaEmpleado, int idCuentaDepartamento, int idPrestamo)  throws PersistenciaException {
        Connection connection = null;

        try {
            connection = this.conexion.crearConexion();

            CallableStatement stmt = connection.prepareCall("{ CALL pago_prestamo(?, ?, ?) }");

            stmt.setInt(1, idCuentaEmpleado);
            stmt.setInt(2, idCuentaDepartamento);
            stmt.setInt(3, idPrestamo);

            stmt.execute();

            System.out.println("Préstamo pagado exitosamente.");

        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un error al solicitar el prestamo" + e.getMessage());
        }
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

            String query2 = """
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            connection = this.conexion.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
                SELECT
                    p.id,
                    p.fecha_hora,
                    p.monto,
                    p.estatusActual,
                    p.id_tipo,
                    p.id_cuenta_departamento,
                    p.id_cuenta_empleado
                FROM prestamos AS p
                LEFT JOIN cuentas_departamentos AS cd
                    ON p.id_cuenta_departamento = cd.id
                LEFT JOIN departamentos AS d
                    ON cd.id_departamento = d.id
                WHERE 1=1
                """);
            List<Object> parametros = new ArrayList<>();

            String filtroTexto = "%" + filtro.getFiltro() + "%";
            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                queryBuilder.append("""
                AND (CAST(fecha_hora AS CHAR) LIKE ?
                    OR CAST(monto AS CHAR) LIKE ?
                    OR CAST(estatusActual AS CHAR) LIKE ?)
            """);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
            }

            if (filtro.getIdDepartamento() != null) {
                queryBuilder.append(" AND (d.id = ? OR d.id IS NULL)");
                parametros.add(filtro.getIdDepartamento());
            }

            queryBuilder.append(" LIMIT ? OFFSET ?;");
            parametros.add(filtro.getLimit());
            parametros.add(filtro.getOffset());

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < parametros.size(); i++) {
                Object param = parametros.get(i);
                if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                }

            }

            resultset = preparedStatement.executeQuery();

            List<TablaPrestamosDTO> prestamos = new ArrayList<>();
            while (resultset.next()) {
                prestamos.add(this.convertirTablaDominio(resultset));
            }

            return prestamos;

        } catch (SQLException e) {

            throw new PersistenciaException("Ocurrió un problema al leer empleados: " + e.getMessage());
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
                System.err.println("Error al cerrar recursos en buscarTabla: " + closeEx.getMessage());

            }
        }
    }

    @Override
    public List<TablaPrestamosDTO> buscarTablaAbonar(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            connection = this.conexion.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
                SELECT
                    p.id,
                    p.fecha_hora,
                    p.monto,
                    p.estatusActual,
                    p.id_tipo,
                    p.id_cuenta_departamento,
                    p.id_cuenta_empleado
                FROM prestamos AS p
                LEFT JOIN cuentas_departamentos AS cd
                    ON p.id_cuenta_departamento = cd.id
                LEFT JOIN departamentos AS d
                    ON cd.id_departamento = d.id
                WHERE 1=1
                    AND p.estatusActual IN ('Pagado', 'Abonado')
                """);
            List<Object> parametros = new ArrayList<>();

            String filtroTexto = "%" + filtro.getFiltro() + "%";
            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                queryBuilder.append("""
                AND (CAST(fecha_hora AS CHAR) LIKE ?
                    OR CAST(monto AS CHAR) LIKE ?
                    OR CAST(estatusActual AS CHAR) LIKE ?)
            """);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
            }

            if (filtro.getIdDepartamento() != null) {
                queryBuilder.append(" AND (d.id = ? OR d.id IS NULL)");
                parametros.add(filtro.getIdDepartamento());
            }

            queryBuilder.append(" LIMIT ? OFFSET ?;");
            parametros.add(filtro.getLimit());
            parametros.add(filtro.getOffset());

            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < parametros.size(); i++) {
                Object param = parametros.get(i);
                if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                }

            }

            resultset = preparedStatement.executeQuery();

            List<TablaPrestamosDTO> prestamos = new ArrayList<>();
            while (resultset.next()) {
                prestamos.add(this.convertirTablaDominio(resultset));
            }

            return prestamos;

        } catch (SQLException e) {

            throw new PersistenciaException("Ocurrió un problema al leer empleados: " + e.getMessage());
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
                System.err.println("Error al cerrar recursos en buscarTabla: " + closeEx.getMessage());

            }
        }
    }

    @Override
    public List<ReportePrestamoDTO> obtenerPrestamosFiltrados(filtroPrestamosDTO filtro) throws PersistenciaException {
        List<ReportePrestamoDTO> listaPrestamos = new ArrayList<>();
        try {
            String codigoSQL = codigoSQLReportePrestamo(filtro);
            int index = 3;
            Connection connection = this.conexion.crearConexion();
            PreparedStatement comando = connection.prepareStatement(codigoSQL);

            Date fechaInicio = Date.valueOf(filtro.getFechaInicio());
            Date fechaFin = Date.valueOf(filtro.getFechaFin());
            comando.setDate(1, fechaInicio);
            comando.setDate(2, fechaFin);

            if (filtro.getTiposPrestamo() != null) {
                for (String tipo : filtro.getTiposPrestamo()) {
                    comando.setString(index++, tipo);
                }
            }
            if (filtro.getDepartamentos() != null) {
                for (String departamento : filtro.getDepartamentos()) {
                    comando.setString(index++, departamento);
                }
            }

            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                ReportePrestamoDTO prestamo = new ReportePrestamoDTO();
                prestamo.setTipoPrestamo(resultado.getString("tipoPrestamo"));
                prestamo.setDepartamento(resultado.getString("departamento"));
                prestamo.setFechaHora(resultado.getTimestamp("fechaHora").toLocalDateTime());
                prestamo.setNombreSolicitante(resultado.getString("nombreSolicitante"));
                prestamo.setMontoSolicitado(resultado.getFloat("montoSolicitado"));
                listaPrestamos.add(prestamo);
            }
            resultado.close();
            comando.close();
            connection.close();

            return listaPrestamos;
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un error al buscar los prestamos: " + e.getMessage());
        }

    }

    /**
     * esto valida que exista algun tipo de prestamo por el cual filtrar si no
     * es null entra y lo que hace es que con el sb se agrega al codigo visto
     * anteriormente y entra a un ciclo que dependiendo de cuantos tipos tenga
     * la lista se irán agregando para que contenga todos ej si tiene 3 tipos de
     * prestamo por los cuales filtrar entrará 3 veces y se agregarán los 3
     * parametros por los cuales filtrar capich o no capich a y el delete chart
     * lo q hace es que si se ve en el segundo append se agrega un "?" junto a
     * una "," entonces lanzaría error, para evitarlo, despues de que se
     * agreguen todos por los que se filtrará borra el último caracter que es la
     * ","
     *
     * @param filtro
     * @return codigo sql
     */
    private String codigoSQLReportePrestamo(filtroPrestamosDTO filtro) {
        StringBuilder codigoSQL = new StringBuilder("""
                SELECT tp.nombre_descriptivo as tipoPrestamo, 
                d.nombre as departamento, p.fecha_hora as fechaHora, 
                concat_ws(" ", e.nombres,e.apellidoPaterno) as nombreSolicitante, 
                p.monto as montoSolicitado
                FROM tipos_prestamos tp 
                INNER JOIN prestamos p on tp.id = p.id_tipo
                INNER JOIN cuentas_empleados ce on p.id_cuenta_empleado = ce.id
                INNER JOIN empleados e on ce.id_empleado = e.id
                INNER JOIN departamentos d on e.id_departamento = d.id
                WHERE p.fecha_hora between ? and DATE_ADD(?, INTERVAL 1 DAY)                     
                                                        """);

        if (filtro.getTiposPrestamo() != null
                && !filtro.getTiposPrestamo().isEmpty()) {
            codigoSQL.append(" AND tp.nombre_descriptivo IN (")
                    .append("?,".repeat(filtro.getTiposPrestamo().size()))
                    .deleteCharAt(codigoSQL.length() - 1).append(")");
        }

        if (filtro.getDepartamentos() != null
                && !filtro.getDepartamentos().isEmpty()) {
            codigoSQL.append(" AND d.nombre IN (")
                    .append("?,".repeat(filtro.getDepartamentos().size()))
                    .deleteCharAt(codigoSQL.length() - 1).append(")");
        }

        return codigoSQL.toString();
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

    @Override
    public int contarTotalPrestamos(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {

            connection = this.conexion.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
            SELECT COUNT(p.id)
            FROM prestamos AS p
            INNER JOIN cuentas_departamentos AS cd
            ON p.id_cuenta_departamento = cd.id
            INNER JOIN departamentos AS d
            ON cd.id_departamento = d.id
            WHERE 1=1
            """);

            List<Object> parametros = new ArrayList<>();

            String filtroTexto = "%" + filtro.getFiltro() + "%";
            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                queryBuilder.append("""
                AND (CAST(fecha_hora AS CHAR) LIKE ?
                    OR CAST(monto AS CHAR) LIKE ?
                    OR CAST(estatusActual AS CHAR) LIKE ?)
                 """);
                parametros.add(filtroTexto);
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
            throw new PersistenciaException("Ocurrió un problema al contar empleados: " + e.getMessage());
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
                System.err.println("Error al cerrar recursos en contarTotalEmpleados: " + closeEx.getMessage());
            }
        }
    }

    private TablaPrestamosDTO convertirTablaDominio(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        LocalDateTime fechaHora = set.getTimestamp("fecha_hora").toLocalDateTime();
        float monto = set.getFloat("monto");
        String txtEstatus = set.getString("estatusActual");
        Estatus estatus = Estatus.fromString(txtEstatus);
        int tipoPrestamo = set.getInt("id_tipo");
        int cuentaDepartamento = set.getInt("id_cuenta_departamento");
        int cuentaEmpleado = set.getInt("id_cuenta_empleado");
        return new TablaPrestamosDTO(id, fechaHora, monto, estatus, tipoPrestamo, cuentaDepartamento, cuentaEmpleado);
    }

}
