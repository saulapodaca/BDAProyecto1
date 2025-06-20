/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

import java.time.LocalDateTime;

/**
 *
 * @author adell
 */
public class EstatusDominio {
    
    private int id;
    private Estatus nombreEstatus;
    private LocalDateTime fechaHora;
    private int idJefe;
    private int idPrestamo;
    /**
     * constructor por ausencia
     */
    public EstatusDominio() {
    }
    /**
     * constructor de un estatus dominio
     * @param id para el id del estatus
     * @param nombreEstatus para el nombre del estatus
     * @param fechaHora para la fecha de un estatus
     * @param idJefe para el id jefe el cual manejo el estatus
     * @param idPrestamo  para el id prestamo en el que esta el estatus
     */
    public EstatusDominio(int id, Estatus nombreEstatus, LocalDateTime fechaHora, int idJefe, int idPrestamo) {
        this.id = id;
        this.nombreEstatus = nombreEstatus;
        this.fechaHora = fechaHora;
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

    public Estatus getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(Estatus nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }
}
