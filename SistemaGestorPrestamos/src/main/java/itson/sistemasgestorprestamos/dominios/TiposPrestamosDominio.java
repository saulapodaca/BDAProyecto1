/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

/**
 *
 * @author adell
 */
public class TiposPrestamosDominio {
    private int id;
    private String nombreDescriptivo;
    private int parcialidadesMax;

    /**
     * constructor de tipo prestamo dominio
     * @param id identificador del tipo prestamo
     * @param nombreDescriptivo nombre del tipo de prestamo
     * @param parcialidadesMax  cantidades en cuales se va a pagar el prestamo
     */
    public TiposPrestamosDominio(int id, String nombreDescriptivo, int parcialidadesMax) {
        this.id = id;
        this.nombreDescriptivo = nombreDescriptivo;
        this.parcialidadesMax = parcialidadesMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDescriptivo() {
        return nombreDescriptivo;
    }

    public void setNombreDescriptivo(String nombreDescriptivo) {
        this.nombreDescriptivo = nombreDescriptivo;
    }

    public int getParcialidadesMax() {
        return parcialidadesMax;
    }

    public void setParcialidadesMax(int parcialidadesMax) {
        this.parcialidadesMax = parcialidadesMax;
    }
    
    
    
}
