/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.persistencia;

/**
 *
 * @author adell
 */
public class PersistenciaException extends Exception {
    /**
     * Creacion de las excepciones en el paquete de persistencia
     * @param mensaje  descripcion del error en caso de fallar
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
