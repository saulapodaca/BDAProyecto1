package itson.sistemasgestorprestamos.persistencia;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaDepartamentoDTO;
import itson.sistemasgestorprestamos.DTO.TablaCuentasDepartamentoDTO;
import itson.sistemasgestorprestamos.dominios.CuentasDepartamentosDominio;
import itson.sistemasgestorprestamos.dominios.Estatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CuentaDepartamentoDAO implements ICuentaDepartamentoDAO {

    private IConexionBD manejadorConexiones;

    public CuentaDepartamentoDAO(IConexionBD manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public CuentasDepartamentosDominio registrarCuenta(RegistrarCuentaDepartamentoDTO cuentaDepartamento) throws PersistenciaException {
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            String codigoSQL = """
                               INSERT INTO CUENTAS_DEPARTAMENTOS
                               (numero_cuenta, clabe, nombre_banco, saldo, id_departamento)
                               VALUES (?,?,?,?,?);
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, cuentaDepartamento.getClabe());
            comando.setString(2, cuentaDepartamento.getBanco());
            comando.setString(3, cuentaDepartamento.getNumeroCuenta());
            comando.setFloat(4, cuentaDepartamento.getSaldo());
            comando.setInt(5, cuentaDepartamento.getIdDepartamento());

            int filasAfectadas = comando.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("Error al registrar la cuenta del empleado");
            }

            ResultSet resultado = comando.getGeneratedKeys();
            int id = resultado.getInt(1);
            CuentasDepartamentosDominio cuenta = buscarCuentaPorId(id);
            resultado.close();
            comando.close();
            conexion.close();

            return cuenta;
        } catch (SQLException e) {
            throw new PersistenciaException("La cuenta del departamento no se pudo ingresar: " + e.getMessage());
        }
    }

    public List<CuentasDepartamentosDominio> buscarCuentasDepartamentosPorId(FiltroDTO filtro) throws PersistenciaException {
        List<CuentasDepartamentosDominio> listaCuentas = new LinkedList<>();
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT 
                               id, 
                               numero_cuenta, 
                               clabe, 
                               nombre_banco,
                               saldo,
                               id_departamento
                               FROM cuentas_departamentos
                               WHERE id_departamento = ?
                               LIMIT ?
                               OFFSET ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, Integer.parseInt(filtro.getFiltro()));
            comando.setInt(2, filtro.getLimit());
            comando.setInt(3, filtro.getOffset());

            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                CuentasDepartamentosDominio cuenta = convertirCuentaDominio(resultado);
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
            throw new PersistenciaException("Ocurrió un error al buscar la cuenta : " + e.getMessage());
        }
    }

    public void eliminarCuentaPorId(int id) throws PersistenciaException {
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               UPDATE CUENTAS_DEPARTAMENTOS
                               SET ESTATUS = 'INACTIVA'
                               WHERE idCuentaDepartamento = ?;
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

    public CuentasDepartamentosDominio buscarCuentaPorId(int id) throws PersistenciaException {
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            String codigoSQL = """
                               SELECT
                               idCuentaDepartamento,
                               clabe,
                               estatus,
                               banco,
                               numeroCuenta,
                               saldo,
                               idDepartamento
                               FROM CUENTAS_DEPARTAMENTOS
                               WHERE idCuentaDepartamento = ?;
                               """;
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            CuentasDepartamentosDominio cuenta = convertirCuentaDominio(resultado);
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
            FROM cuentas_departamentos AS c
            INNER JOIN departamentos AS d
            ON c.id_departamento = d.id
            WHERE 1=1
            """);

            List<Object> parametros = new ArrayList<>();

            String filtroTexto = "%" + filtro.getFiltro() + "%";
            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                queryBuilder.append("""
                AND (CAST(numero_cuenta AS CHAR) LIKE ?
                    OR CAST(clabe AS CHAR) LIKE ?
                    OR CAST(nombre_banco AS CHAR) LIKE ?
                    OR CAST(saldo AS CHAR) LIKE ?)
                 """);
                parametros.add(filtroTexto);
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
            throw new PersistenciaException("Ocurrió un problema al contar cuentas: " + e.getMessage());
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
    public List<TablaCuentasDepartamentoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;

        try {
            connection = this.manejadorConexiones.crearConexion();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
                SELECT
                    id,
                    numero_cuenta,
                    clabe,
                    nombre_banco,
                    saldo,
                    id_departamento
                FROM cuentas_departamentos AS c
                INNER JOIN departamentos as d 
                ON c.id_departamento = d.id
                WHERE 1=1
                """);
            List<Object> parametros = new ArrayList<>();

            String filtroTexto = "%" + filtro.getFiltro() + "%";
            if (filtro.getFiltro() != null && !filtro.getFiltro().trim().isEmpty()) {
                queryBuilder.append("""
                AND (CAST(numero_cuenta AS CHAR) LIKE ?
                                OR CAST(clabe AS CHAR) LIKE ?
                                OR CAST(nombre_banco AS CHAR) LIKE ?
                                OR CAST(saldo AS CHAR) LIKE ?)
                             """);
                parametros.add(filtroTexto);
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

            List<TablaCuentasDepartamentoDTO> cuentas = new ArrayList<>();
            while (resultset.next()) {
                cuentas.add(this.convertirTablaCuenta(resultset));
            }

            return cuentas;

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
    
    private CuentasDepartamentosDominio convertirCuentaDominio(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String numerocuenta = resultado.getString("numero_cuenta");
        String clabe = resultado.getString("clabe");
        String nombrebanco = resultado.getString("nombre_banco");
        float saldo = resultado.getFloat("saldo");
        int idDepartamento = resultado.getInt("id_departamento");
        return new CuentasDepartamentosDominio(id,numerocuenta,clabe,nombrebanco,saldo,idDepartamento);
    }

    private TablaCuentasDepartamentoDTO convertirTablaCuenta(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("id");
        String numerocuenta = resultado.getString("numero_cuenta");
        String clabe = resultado.getString("clabe");
        String nombrebanco = resultado.getString("nombre_banco");
        float saldo = resultado.getFloat("saldo");
        int idDepartamento = resultado.getInt("id_departamento");
        return new TablaCuentasDepartamentoDTO(id,numerocuenta,clabe,nombrebanco,saldo,idDepartamento);
    }
}
