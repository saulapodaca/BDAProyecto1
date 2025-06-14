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
    private String nombre;
    private LocalDateTime fechaHora;
    private int idJefe;
    private int idPrestamo;

    public EstatusDominio() {
    }

    public EstatusDominio(int id, String nombre, LocalDateTime fechaHora, int idJefe, int idPrestamo) {
        this.id = id;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
}
