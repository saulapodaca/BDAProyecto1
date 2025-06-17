package itson.sistemasgestorprestamos.persistencia;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasDepartamentoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasEmpleadosDominio;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class CuentaEmpleadoDAO implements ICuentaEmpleadoDAO {

    private IConexionBD manejadorConexiones;

    public CuentaEmpleadoDAO(IConexionBD manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    @Override
    public CuentasEmpleadosDominio registrarCuenta(RegistrarCuentaEmpleadoDTO cuentaEmpleado) throws PersistenciaException {
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            String codigoSQL = """
                               INSERT INTO CUENTAS_EMPLEADOS
                               (clabe, banco, saldo, idEmpleado)
                               VALUES (?,?,?,?);
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, cuentaEmpleado.getClabe());
            comando.setString(2, cuentaEmpleado.getBanco());
            comando.setFloat(3, cuentaEmpleado.getSaldo());
            comando.setInt(4, cuentaEmpleado.getIdEmpleado());

            int filasAfectadas = comando.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("Error al registrar la cuenta del empleado");
            }

            ResultSet resultado = comando.getGeneratedKeys();
            int id = resultado.getInt(1);
            CuentasEmpleadosDominio cuenta = buscarCuentasPorId(id);
            resultado.close();
            comando.close();
            conexion.close();

            return cuenta;
        } catch (SQLException e) {
            throw new PersistenciaException("La cuenta del empleado NO ha sido registrada: " + e.getMessage());
        }
    }

    @Override
    public void eliminarCuentaPorId(int id) throws PersistenciaException {
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               UPDATE CUENTAS_EMPLEADOS
                               SET ESTATUS = 'INACTIVA'
                               WHERE idCuentaEmpleado = ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, id);
            int filasAfectadas = comando.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se eliminó la cuenta.");
            }
            conexion.close();
        } catch (SQLException e) {
            throw new PersistenciaException("No se eliminó la cuenta.: " + e.getMessage());
        }
    }

    @Override
    public List<CuentasEmpleadosDominio> buscarCuentasEmpleadoPorId(FiltroDTO filtro) throws PersistenciaException {
        List<CuentasEmpleadosDominio> listaCuentas = new LinkedList<>();
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT 
                               id, 
                               clabe, 
                               activo, 
                               nombre_banco,
                               saldo,
                               id_empleado
                               FROM CUENTAS_EMPLEADOS
                               WHERE id_empleado = ?
                               LIMIT ?
                               OFFSET ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            int idEmpleado = Integer.parseInt(filtro.getFiltro());
            comando.setInt(1, idEmpleado);
            comando.setInt(2, filtro.getLimit());
            comando.setInt(3, filtro.getOffset());

            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                CuentasEmpleadosDominio cuenta = convertirCuentaDominio(resultado);
                listaCuentas.add(cuenta);
            }
            resultado.close();
            comando.close();
            conexion.close();

            if (listaCuentas == null) {
                throw new PersistenciaException("No se encontró la cuenta del empleado: " + filtro.getFiltro());
            }

            return listaCuentas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Ocurrió un error al buscar la cuenta.");
        }
    }

    private CuentasEmpleadosDominio buscarCuentasPorId(int id) throws PersistenciaException {
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT
                               idCuentaEmpleado,
                               clabe,
                               estatus,
                               banco,
                               saldo,
                               idEmpleado
                               FROM CUENTAS_EMPLEADOS
                               WHERE idCuentaEmpleado = ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            CuentasEmpleadosDominio cuenta = convertirCuentaDominio(resultado);
            resultado.close();
            comando.close();
            conexion.close();

            if (cuenta == null) {
                throw new PersistenciaException("No se encontró la cuenta: " + id);
            }

            return cuenta;
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un error al buscar la cuenta : " + e.getMessage());
        }
    }

    private CuentasEmpleadosDominio convertirCuentaDominio(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String clabe = resultado.getString("clabe");
        String estatus = resultado.getString("activo");
        String banco = resultado.getString("nombre_banco");
        float saldo = resultado.getFloat("saldo");
        int idEmpleado = resultado.getInt("id_empleado");
        return new CuentasEmpleadosDominio(id, clabe, estatus, banco, saldo, idEmpleado);
    }

    public int obtenerIdCuentaDepartamentoPorClabe(String clabe) throws PersistenciaException {
        int idCuenta = -1;
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                           SELECT id
                           FROM CUENTAS_DEPARTAMENTOS
                           WHERE clabe = ?;
                           """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, clabe);

            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                idCuenta = resultado.getInt("id");
            }

            resultado.close();
            comando.close();
            conexion.close();

            if (idCuenta == -1) {
                throw new PersistenciaException("No se encontró cuenta con la clabe: " + clabe);
            }

            return idCuenta;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Ocurrió un error al buscar el ID de la cuenta del departamento.");
        }

    }

    @Override
    public int obtenerIdCuentaEmpleadoPorClabe(String clabe) throws PersistenciaException {
        int idCuenta = -1;
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                           SELECT id
                           FROM CUENTAS_EMPLEADOS
                           WHERE clabe = ?;
                           """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, clabe);

            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                idCuenta = resultado.getInt("id");
            }

            resultado.close();
            comando.close();
            conexion.close();

            if (idCuenta == -1) {
                throw new PersistenciaException("No se encontró cuenta con la clabe: " + clabe);
            }

            return idCuenta;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Ocurrió un error al buscar el ID de la cuenta del departamento.");
        }
    }
    
    @Override
    public int contarTotalCuentas(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try {
            connection = this.manejadorConexiones.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
            SELECT COUNT(c.id)
            FROM cuentas_empleados AS c
            INNER JOIN empleados AS e ON c.id_empleado = e.id
            WHERE 1=1
        """);

            List<Object> parametros = new ArrayList<>();

            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                String filtroTexto = "%" + filtro.getFiltro() + "%";
                queryBuilder.append("""
                AND (
                    CAST(clabe AS CHAR) LIKE ?
                    OR CAST(nombre_banco AS CHAR) LIKE ?
                    OR CAST(saldo AS CHAR) LIKE ?
                    OR CAST(activo AS CHAR) LIKE ?
                )
            """);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
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
            throw new PersistenciaException("Ocurrió un problema al contar cuentas de empleados: " + e.getMessage());
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
                System.err.println("Error al cerrar recursos en contarTotalCuentas: " + closeEx.getMessage());
            }
        }
    }

    @Override
    public List<TablaCuentasEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            connection = this.manejadorConexiones.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
            SELECT
                c.id,
                c.clabe,
                c.activo,
                c.nombre_banco,
                c.saldo,
                c.id_empleado
            FROM cuentas_empleados AS c
            INNER JOIN empleados AS e ON c.id_empleado = e.id
            WHERE 1=1
        """);

            List<Object> parametros = new ArrayList<>();

            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                String filtroTexto = "%" + filtro.getFiltro() + "%";
                queryBuilder.append("""
                AND (
                    CAST(clabe AS CHAR) LIKE ?
                    OR CAST(nombre_banco AS CHAR) LIKE ?
                    OR CAST(saldo AS CHAR) LIKE ?
                    OR CAST(activo AS CHAR) LIKE ?
                )
            """);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
                parametros.add(filtroTexto);
            }

            queryBuilder.append(" LIMIT ? OFFSET ?");
            parametros.add(filtro.getLimit());
            parametros.add(filtro.getOffset());

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

            List<TablaCuentasEmpleadoDTO> cuentas = new ArrayList<>();
            while (resultset.next()) {
                cuentas.add(this.convertirTablaCuenta(resultset));
            }

            return cuentas;

        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrió un problema al leer cuentas de empleados: " + e.getMessage());
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
    
    private TablaCuentasEmpleadoDTO convertirTablaCuenta(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String clabe = resultado.getString("clabe");
        String activo = resultado.getString("activo");
        String nombrebanco = resultado.getString("nombre_banco");
        float saldo = resultado.getFloat("saldo");
        int idEmpleado = resultado.getInt("id_empleado");
        return new TablaCuentasEmpleadoDTO(id, clabe, activo, nombrebanco, saldo, idEmpleado);
    }
}
