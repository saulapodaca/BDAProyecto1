/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.GuardarEmpleadoDTO;
import itson.sistemasgestorprestamos.dominios.EmpleadosDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void guardar(GuardarEmpleadoDTO empleado) throws PersistenciaException {
        try {
            Connection connection = this.conexion.crearConexion();
            //inicio de la transaccion
            connection.setAutoCommit(false);
            String query = """
                           INSERT INTO `finanzasglobales`.`empleados`
                           (
                           `nombres`,
                           `apellidoPaterno`,
                           `apellidoMaterno`,
                           `estatus`,
                           `usuario`,
                           `contraseña`,
                           `id_departamento`)
                           VALUES
                           (?,?,?,?,?,?,?)            
                           """;
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, empleado.getNombres());
            preparedStatement.setString(2, empleado.getApellidoPaterno());
            preparedStatement.setString(3, empleado.getApellidoMaterno());
            preparedStatement.setBoolean(4, empleado.isEstatus());
            preparedStatement.setString(5, empleado.getUsuario());
            preparedStatement.setString(6, empleado.getContraseña());
            preparedStatement.setInt(7, empleado.getIdDepartamento());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se insertion el empleado");
            }

            //preparedStatement = parametros de forma segura evitando inyecciones
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // final de la transaccion
            connection.commit();
            System.out.println("Empleado creado");
        } catch (SQLException e) {
            throw new PersistenciaException("Ocurrio un error al guardar" + e.getMessage());

        }
    }

}
