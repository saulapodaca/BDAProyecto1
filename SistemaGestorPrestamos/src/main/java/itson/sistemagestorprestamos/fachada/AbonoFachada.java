/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.fachada;

import itson.sistemasgestorprestamos.DTO.FiltroDTO;
import itson.sistemasgestorprestamos.DTO.RegistrarAbonoDTO;
import itson.sistemasgestorprestamos.DTO.TablaAbonosDTO;
import itson.sistemasgestorprestamos.Negocio.AbonoNegocio;
import itson.sistemasgestorprestamos.Negocio.IAbonoNegocio;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import itson.sistemasgestorprestamos.dominios.AbonoDominio;
import itson.sistemasgestorprestamos.persistencia.AbonoDAO;
import itson.sistemasgestorprestamos.persistencia.ConexionBD;
import itson.sistemasgestorprestamos.persistencia.IAbonoDAO;
import itson.sistemasgestorprestamos.persistencia.IConexionBD;
import java.util.List;

/**
 *
 * @author Camila Zubía
 */
public class AbonoFachada implements IAbonoFachada{
    
    private IAbonoNegocio abonoNegocio;

    public AbonoFachada() {
        IConexionBD conexion = new ConexionBD();
        IAbonoDAO abonoDAO = new AbonoDAO(conexion);
        this.abonoNegocio = new AbonoNegocio(abonoDAO);
    }

    @Override
    public AbonoDominio registrarAbono(RegistrarAbonoDTO abono) throws NegocioException {
        return this.abonoNegocio.registrarAbono(abono);
    }

    @Override
    public List<TablaAbonosDTO> buscarTabla(FiltroDTO filtro) throws NegocioException {
        return this.abonoNegocio.buscarTabla(filtro);
    }

    @Override
    public AbonoDominio buscarPorID(int id) throws NegocioException {
        return this.abonoNegocio.buscarPorID(id);
    }
    
}
