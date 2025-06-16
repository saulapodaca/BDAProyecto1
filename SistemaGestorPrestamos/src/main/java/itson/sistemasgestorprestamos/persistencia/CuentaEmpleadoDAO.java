package itson.sistemasgestorprestamos.persistencia;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarCuentaEmpleadoDTO;
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
}
