/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

/**
 *
 * @author adell
 */
public class FiltroDTO {

    private int limit;
    private int offset;
    private String filtro;
    private Integer idDepartamento;

    public FiltroDTO(int limit, int offset, String filtro) {
        this.limit = limit;
        this.offset = offset;
        this.filtro = filtro;
    }

    public FiltroDTO(int limit, int offset, String filtro, Integer idDepartamento) {
        this.limit = limit;
        this.offset = offset;
        this.filtro = filtro;
        this.idDepartamento = idDepartamento;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    
  

   
    
}
