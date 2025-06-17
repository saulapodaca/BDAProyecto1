/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.sistemasgestorprestamos.Negocio;

/**
 *
 * @author saula
 */
public interface IInicializacionNegocio {

    public void insertarDatosMasivos() throws NegocioException;
    public boolean existenDatosIniciales() throws NegocioException;


}
