/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

/**
 *
 * @author Camila Zubía
 */
public enum Estatus {
    Creado,
    Rechazado,
    Autorizado,
    Abonado,
    Pagado,
    Completado;
    
    public static Estatus fromString(String estatusStr) {
        for (Estatus estatus : values()) {
            if (estatus.name().equalsIgnoreCase(estatusStr)) {
                return estatus;
            }
        }
        throw new IllegalArgumentException("Estatus inválido: " + estatusStr);
    }
}
