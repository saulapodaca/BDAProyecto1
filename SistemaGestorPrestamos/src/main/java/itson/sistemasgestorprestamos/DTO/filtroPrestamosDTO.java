package itson.sistemasgestorprestamos.DTO;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.time.LocalDate;
import java.util.List;


public class filtroPrestamosDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<String> tiposPrestamo;
    private List<String> departamentos;

    public filtroPrestamosDTO(LocalDate fechaInicio, LocalDate fechaFin, List<String> tiposPrestamo, List<String> departamentos) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tiposPrestamo = tiposPrestamo;
        this.departamentos = departamentos;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setTiposPrestamo(List<String> tiposPrestamo) {
        this.tiposPrestamo = tiposPrestamo;
    }

    public void setDepartamentos(List<String> departamentos) {
        this.departamentos = departamentos;
    }

    public filtroPrestamosDTO() {
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public List<String> getTiposPrestamo() {
        return tiposPrestamo;
    }

    public List<String> getDepartamentos() {
        return departamentos;
    }
    
    
}
