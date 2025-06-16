package itson.sistemagestorprestamos.reportes;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import itson.sistemagestorprestamos.fachada.IPrestamoFachada;
import itson.sistemagestorprestamos.fachada.prestamoFachada;
import itson.sistemagestorprestamos.utilidades.SesionIniciada;
import itson.sistemasgestorprestamos.DTO.ReportePrestamoDTO;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Reporte {

    private final String RUTA_JASPER = "/reporteTiposPrestamos.jasper";

    public void generarReporte(filtroPrestamosDTO filtro) throws NegocioException, FileNotFoundException{
        try{
            IPrestamoFachada prestamoFachada = new prestamoFachada();
            List<ReportePrestamoDTO> prestamos = prestamoFachada.obtenerPrestamosFiltrados(filtro);
            //data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(prestamos);
            
            File f = new File("src/main/resources/reporteTiposPrestamos.jasper");
            System.out.println("Existe: " + f.exists());
            System.out.println("Tamaño: " + f.length());
            System.out.println("Cantidad de registros: " + prestamos.size());

            Date fechaInicioDate = Date.from(filtro.getFechaInicio().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Date fechaFinDate = Date.from(filtro.getFechaFin().atStartOfDay(ZoneId.systemDefault()).toInstant());

            
            //mapeo de parametros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaInicio", fechaInicioDate);
            parametros.put("fechaFin", fechaFinDate);
            parametros.put("tipoPrestamos", filtro.getTiposPrestamo());
            parametros.put("departamentos", filtro.getDepartamentos());
            parametros.put("nombreDelUsuarioQueLoMandoImprimir", 
                    SesionIniciada.getInstancia().getEmpleado().getNombres());
 
            InputStream jasperStream = new FileInputStream("src/main/resources/reporteTiposPrestamos.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            jasperStream.close();  // Cierra el stream ya que no lo necesitas más

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | NegocioException  e) {
            e.printStackTrace(); // Importante para depurar
            throw new NegocioException(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
