/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

/**
 *Interfaz de la dao de inicializacion de la bd
 * @author saula
 */
public interface IInicializacionDAO {

    /**
     * Metodo para insertar masivamente los datos en la bd
     * @throws PersistenciaException 
     */
    public void insertarDatosMasivos() throws PersistenciaException;

    /**
     * metodo para validar la existencia de datos en la bd
     * @return
     * @throws PersistenciaException 
     */
    public boolean existenDatosIniciales() throws PersistenciaException;

}
