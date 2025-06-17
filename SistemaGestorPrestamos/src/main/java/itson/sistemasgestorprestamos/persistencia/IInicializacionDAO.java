/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

/**
 *
 * @author saula
 */
public interface IInicializacionDAO {

    public void insertarDatosMasivos() throws PersistenciaException;

    public boolean existenDatosIniciales() throws PersistenciaException;

}
