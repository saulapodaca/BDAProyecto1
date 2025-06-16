package itson.sistemagestorprestamos.reportes;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import itson.sistemagestorprestamos.fachada.IPrestamoFachada;
import itson.sistemagestorprestamos.fachada.prestamoFachada;
import itson.sistemagestorprestamos.utilidades.SesionIniciada;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Reporte {

    private final String RUTA_JASPER = "src/main/resources/reporteTiposPrestamos.jrxml";

    public void generarReporte(filtroPrestamosDTO filtro) throws NegocioException{
        try{
            IPrestamoFachada prestamoFachada = new prestamoFachada();
            List<ReportePrestamoDTO> prestamos = prestamoFachada.obtenerPrestamosFiltrados(filtro);
            //data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(prestamos);
            
            //mapeo de parametros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", filtro.getFechaInicio());
            parametros.put("fechaFin", filtro.getFechaFin());
            parametros.put("listaTipoDepartamentos", filtro.getTiposPrestamo());
            parametros.put("listaDepartamentos", filtro.getDepartamentos());
            parametros.put("nombreDelUsuarioQueLoMandoImprimir", 
                    SesionIniciada.getInstancia().getEmpleado().getNombres());
            
            //llenao del reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(RUTA_JASPER, parametros, dataSource);
            
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | NegocioException e){
            throw new NegocioException(e.getMessage());
        }
    }
}
