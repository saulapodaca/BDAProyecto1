/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

import java.time.LocalDateTime;

/**
 * Clase que hace referencia a un Abono
 * @author adell
 */
public class AbonoDominio {
    private int id;
    private LocalDateTime fechaHora;
    private float monto;
    private int idJefe;
    private int idPrestamo;
    
    /**
     * constructor por ausencia
     */
    public AbonoDominio() {
        
    }
    
    /**
     * constructor de objetos abonodominio
     * @param id para la id
     * @param fechaHora para la fecha y hora
     * @param monto para el monto del abono
     * @param idJefe para el id del jefe
     * @param idPrestamo  para el id del prestamo
     */
    public AbonoDominio(int id, LocalDateTime fechaHora, float monto, int idJefe, int idPrestamo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.idJefe = idJefe;
        this.idPrestamo = idPrestamo;
    }
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    
    
}
