/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.GuardarPrestamoDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaPrestamosDTO;
import itson.sistemasgestorprestamos.dominios.Estatus;
import itson.sistemasgestorprestamos.dominios.PrestamosDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class PrestamoDAO implements IPrestamoDAO{
    private IConexionBD conexion;

    public PrestamoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public PrestamosDominio guardar(GuardarPrestamoDTO prestamo) throws PersistenciaException {
        Connection connection = null;
        try{
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

            
        }catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el abono: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public PrestamosDominio abonarPrestamo(RegistrarAbonoDTO abono) throws PersistenciaException {
        
        return null;
        
    }

    @Override
    public PrestamosDominio cambiarEstatus(Estatus estatus) throws PersistenciaException {
        
        return null;
        
    }

    @Override
    public PrestamosDominio buscarPorId(int idPrestamo) throws PersistenciaException {
        Connection connection = null;
        try{
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
            
        }catch (SQLException ex) {
            throw new PersistenciaException("Ocurrió un error al buscar el abono: " + ex.getMessage());
        }
    }

    @Override
    public List<TablaPrestamosDTO> buscarTabla(FiltroDTO filtro) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
