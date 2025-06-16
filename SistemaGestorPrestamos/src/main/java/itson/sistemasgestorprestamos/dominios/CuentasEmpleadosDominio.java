/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.dominios;

/**
 *
 * @author adell
 */
public class CuentasEmpleadosDominio {
    private int id;
    private String clabe;
    private String estatus;
    private String nombreBanco;
    private float saldo;
    private int idEmpleado;

    public CuentasEmpleadosDominio() {
    }

    public CuentasEmpleadosDominio(int id, String clabe, String estatus, 
            String nombreBanco,float saldo, int idEmpleado) {
        this.id = id;
        this.clabe = clabe;
        this.estatus = estatus;
        this.nombreBanco = nombreBanco;
        this.idEmpleado = idEmpleado;
    }

    public int getId() {
        return id;
    }

    public String getClabe() {
        return clabe;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuentasEmpleadosDominio{");
        sb.append("id=").append(id);
        sb.append(", clabe=").append(clabe);
        sb.append(", estatus=").append(estatus);
        sb.append(", nombreBanco=").append(nombreBanco);
        sb.append(", saldo=").append(saldo);
        sb.append(", idEmpleado=").append(idEmpleado);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
