/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.TablaEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.LoginEmpleadoDTO;
import itson.sistemasgestorprestamos.DTO.SesionEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adell
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    private IConexionBD conexion;

    public EmpleadoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public EmpleadosDominio guardar(GuardarEmpleadoDTO empleado) throws PersistenciaException {
        try {
            Connection connection = this.conexion.crearConexion();
            connection.setAutoCommit(false);
            String query = """
                           INSERT INTO `finanzasglobales`.`empleados`
                           (
                           `nombres`,
                           `apellidoPaterno`,
                           `apellidoMaterno`,    
                           `usuario`,
                           `contraseña`,
                           `id_departamento`)
                           VALUES
                           (?,?,?,?,?,?)            
                           """;
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, empleado.getNombres());
            preparedStatement.setString(2, empleado.getApellidoPaterno());
            preparedStatement.setString(3, empleado.getApellidoMaterno());
            preparedStatement.setString(4, empleado.getUsuario());
            preparedStatement.setString(5, empleado.getContraseña());
            preparedStatement.setInt(6, empleado.getIdDepartamento());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se insertion el empleado");
            }
            int id = 0;
            //preparedStatement = parametros de forma segura evitando inyecciones
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // final de la transaccion
            connection.commit();
            System.out.println("Empleado creado:");

            EmpleadosDominio empleadobuscado = null;
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                empleadobuscado = this.buscarEmpleadoPorId(id);
            }

            return empleadobuscado;
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrio un error al guardar" + e.getMessage());

        }
    }

    private TablaEmpleadoDTO converTablaEmpleado(ResultSet resulset) throws SQLException {
        int id = resulset.getInt("id");
        String nombre = resulset.getString("nombres");
        String apellidoPaterno = resulset.getString("apellidoPaterno");
        String apellidoMaterno = resulset.getString("apellidoMaterno");
        int idDepartamento = resulset.getInt("id_departamento");
        String nombreDepartamento = resulset.getString("nombre");

        return new TablaEmpleadoDTO(id, nombre, apellidoPaterno, apellidoMaterno, idDepartamento, nombreDepartamento);
    }

    @Override
    public List<TablaEmpleadoDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        try {
            Connection connection = this.conexion.crearConexion();

            String query = """
                         SELECT
                            em.id,
                            nombres,
                            apellidoPaterno,
                            apellidoMaterno,
                            id_departamento,  
                            de.nombre
                         FROM empleados as em
                         INNER JOIN departamentos as de
                         on em.id_departamento = de.id    
                           WHERE
                             em.nombres LIKE ?
                             or em.apellidoPaterno LIKE ?
                             or em.apellidoMaterno LIKE ?
                             or de.nombre LIKE ?
                         LIMIT ?
                         OFFSET ?;
                         """;
            String filtroConLike = "%" + filtro.getFiltro() + "%";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, filtroConLike);
            preparedStatement.setString(2, filtroConLike);
            preparedStatement.setString(3, filtroConLike);
            preparedStatement.setString(4, filtroConLike);
            preparedStatement.setInt(5, filtro.getLimit());
            preparedStatement.setInt(6, filtro.getOffset());

            ResultSet resultset = preparedStatement.executeQuery();

            List<TablaEmpleadoDTO> empleados = null;

            while (resultset.next()) {
                if (empleados == null) {
                    empleados = new ArrayList<>();
                }

                empleados.add(this.converTablaEmpleado(resultset));
            }

            resultset.close();
            preparedStatement.close();
            connection.close();

            if (empleados == null) {
                throw new PersistenciaException("No se encontrar empleados");
            }
            return empleados;
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrio un problema al leer un empleado" + e.getMessage());
        }
    }

    @Override
    public EmpleadosDominio buscarEmpleadoPorId(int id) throws PersistenciaException {
        try {
            Connection connection = this.conexion.crearConexion();
            String select = """
                         SELECT
                            id,               
                            nombres,
                            apellidoPaterno,
                            apellidoMaterno,
                            estatus,          
                            usuario,          
                            contraseña,       
                            id_departamento   
                         FROM `finanzasglobales`.`empleados`
                         WHERE `id` = ?;      
                         """;
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                EmpleadosDominio empleado = this.convertirEmpleadosDominio(resultSet);
                System.out.println(empleado.getNombres() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno());
                return empleado;
            } else {
                System.out.println("No se encontró ningún empleado con ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrio un error al buscar el empleado: " + e.getMessage());
        } finally {
        }
    }

    @Override
    public SesionEmpleadoDTO buscarPorUsuarioYContraseña(LoginEmpleadoDTO empleado) throws PersistenciaException {
        try {
            Connection conn = conexion.crearConexion();
            String query = """
                           SELECT * 
                           FROM empleados 
                           WHERE usuario = ? 
                           AND contraseña = ?
                           """;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, empleado.getUsuario());
            statement.setString(2, empleado.getContraseña());

            ResultSet set = statement.executeQuery();
            SesionEmpleadoDTO em = null;
            while (set.next()) {
                em = this.convertirSesionEmpleado(set);
            }

            set.close();
            statement.close();

            if (em == null) {
                throw new PersistenciaException("No se encontró el empleado");
            }

            return em;

        } catch (SQLException ex) {
            throw new PersistenciaException("Ocurrio un error al hacer login " + ex.getMessage());
        }
    }

    private EmpleadosDominio convertirEmpleadosDominio(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        String nombres = set.getString("nombres");
        String apellidoPaterno = set.getString("apellidoPaterno");
        String apellidoMaterno = set.getString("apellidoMaterno");
        boolean estatus = set.getBoolean("estatus");
        String usuario = set.getString("usuario");
        String contraseña = set.getString("contraseña");
        int idDepartamento = set.getInt("id_departamento");
        return new EmpleadosDominio(id, nombres, apellidoPaterno, apellidoMaterno, estatus, usuario, contraseña, idDepartamento);
    }

    private SesionEmpleadoDTO convertirSesionEmpleado(ResultSet set) throws SQLException {
        int id = set.getInt("id");
        String nombres = set.getString("nombres");
        String apellidoPaterno = set.getString("apellidoPaterno");
        String apellidoMaterno = set.getString("apellidoMaterno");
        int idDepartamento = set.getInt("id_departamento");
        return new SesionEmpleadoDTO(id, nombres, apellidoPaterno, apellidoMaterno, idDepartamento);
    }
}
