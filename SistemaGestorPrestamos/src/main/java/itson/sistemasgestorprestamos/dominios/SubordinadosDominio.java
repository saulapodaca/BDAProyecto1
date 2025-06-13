/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

/**
 *
 * @author adell
 */
public class SubordinadosDominio {

    private int id;
    private int idJefe;

    public SubordinadosDominio(int id, int idJefe) {
        this.id = id;
        this.idJefe = idJefe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

}
