/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemasgestorprestamos.DTO;

/**
 *
 * @author Camila Zubía
 */
public class GuardarPrestamoDTO {
    private float monto;
    private int tipoPrestamo;
    private int cuentaDepartamento;
    private int cuentaEmpleado;

    public GuardarPrestamoDTO(float monto, int tipoPrestamo, int cuentaDepartamento, int cuentaEmpleado) {
        this.monto = monto;
        this.tipoPrestamo = tipoPrestamo;
        this.cuentaDepartamento = cuentaDepartamento;
        this.cuentaEmpleado = cuentaEmpleado;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(int tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public int getCuentaDepartamento() {
        return cuentaDepartamento;
    }

    public void setCuentaDepartamento(int cuentaDepartamento) {
        this.cuentaDepartamento = cuentaDepartamento;
    }

    public int getCuentaEmpleado() {
        return cuentaEmpleado;
    }

    public void setCuentaEmpleado(int cuentaEmpleado) {
        this.cuentaEmpleado = cuentaEmpleado;
    }
}
