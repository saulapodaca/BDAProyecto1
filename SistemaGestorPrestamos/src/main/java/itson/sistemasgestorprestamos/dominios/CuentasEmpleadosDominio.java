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
    
    /**
     * constructor por ausencia
     */
    public CuentasEmpleadosDominio() {
    }
    
    /**
     * constructor de una cuenta empleado dominio
     * @param id para el id de la cuenta empleado dominio
     * @param clabe para la clabe de la cuentaempleado dominio
     * @param estatus para el estatus de una cuenta empleado dominio
     * @param nombreBanco para el nombre del banco a la cual pertenece la cuenta
     * @param saldo para el saldo de la cuenta
     * @param idEmpleado  para el id empleado al que pertenece la cuenta
     */
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
       return clabe;
    }
    
    
    
    
}
