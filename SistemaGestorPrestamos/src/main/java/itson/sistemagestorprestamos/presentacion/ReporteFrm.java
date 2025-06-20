/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.sistemagestorprestamos.presentacion;

import itson.sistemagestorprestamos.reportes.Reporte;
import itson.sistemasgestorprestamos.DTO.filtroPrestamosDTO;
import itson.sistemasgestorprestamos.Negocio.NegocioException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author saula
 */
public class ReporteFrm extends javax.swing.JFrame {

    private MenuJefeForm menuJefeForm;
    /**
     * Creates new form ReporteFrm
     */
    public ReporteFrm(MenuJefeForm menuJefeForm) {
        initComponents();
        this.menuJefeForm = menuJefeForm;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegresar1 = new javax.swing.JButton();
        dateChooserFechaInicio = new datechooser.beans.DateChooserCombo();
        dateChooserFechaFin = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        checkBoxPersonal = new javax.swing.JCheckBox();
        checkBoxHipotecario = new javax.swing.JCheckBox();
        checkBoxAutomotriz = new javax.swing.JCheckBox();
        checkBoxEstudiantil = new javax.swing.JCheckBox();
        checkBoxNomina = new javax.swing.JCheckBox();
        checkBoxAcademico = new javax.swing.JCheckBox();
        checkBoxInnovacion = new javax.swing.JCheckBox();
        checkBoxTecnologia = new javax.swing.JCheckBox();
        checkBoxRH = new javax.swing.JCheckBox();
        checkBoxFinanzas = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("REPORTE");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        btnRegresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flechaanterior2.png"))); // NOI18N
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Fecha Inicio");

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Fecha Fin");

        checkBoxPersonal.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxPersonal.setText("Personal");
        checkBoxPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxPersonalActionPerformed(evt);
            }
        });

        checkBoxHipotecario.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxHipotecario.setText("Hipotecario");
        checkBoxHipotecario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxHipotecarioActionPerformed(evt);
            }
        });

        checkBoxAutomotriz.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxAutomotriz.setText("AutoMotriz");
        checkBoxAutomotriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAutomotrizActionPerformed(evt);
            }
        });

        checkBoxEstudiantil.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxEstudiantil.setText("Estudiantil");
        checkBoxEstudiantil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxEstudiantilActionPerformed(evt);
            }
        });

        checkBoxNomina.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxNomina.setText("Nomina");
        checkBoxNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxNominaActionPerformed(evt);
            }
        });

        checkBoxAcademico.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxAcademico.setText("Académico");
        checkBoxAcademico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxAcademicoActionPerformed(evt);
            }
        });

        checkBoxInnovacion.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxInnovacion.setText("Inovacion");
        checkBoxInnovacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxInnovacionActionPerformed(evt);
            }
        });

        checkBoxTecnologia.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxTecnologia.setText("Tecnologia");
        checkBoxTecnologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxTecnologiaActionPerformed(evt);
            }
        });

        checkBoxRH.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxRH.setText("Recursos Humanos");
        checkBoxRH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxRHActionPerformed(evt);
            }
        });

        checkBoxFinanzas.setForeground(new java.awt.Color(0, 0, 0));
        checkBoxFinanzas.setText("Finanzas");
        checkBoxFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxFinanzasActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Tipo de prestamo");

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Departamento");

        btnGenerarReporte.setBackground(new java.awt.Color(51, 204, 0));
        btnGenerarReporte.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnGenerarReporte.setText("GENERAR REPORTE");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxHipotecario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxAutomotriz, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxEstudiantil, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBoxFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxInnovacion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxTecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxRH))
                .addGap(251, 251, 251))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(277, 277, 277))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(dateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(dateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel2)
                        .addGap(305, 305, 305)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkBoxPersonal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxHipotecario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxAutomotriz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxEstudiantil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxNomina))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkBoxRH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxFinanzas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxAcademico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxInnovacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxTecnologia)))
                .addGap(78, 78, 78)
                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        // TODO add your handling code here:
        menuJefeForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    private void checkBoxPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxPersonalActionPerformed

    private void checkBoxHipotecarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxHipotecarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxHipotecarioActionPerformed

    private void checkBoxAutomotrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAutomotrizActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxAutomotrizActionPerformed

    private void checkBoxEstudiantilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxEstudiantilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxEstudiantilActionPerformed

    private void checkBoxNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxNominaActionPerformed

    private void checkBoxAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxAcademicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxAcademicoActionPerformed

    private void checkBoxInnovacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxInnovacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxInnovacionActionPerformed

    private void checkBoxTecnologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxTecnologiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxTecnologiaActionPerformed

    private void checkBoxRHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxRHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxRHActionPerformed

    private void checkBoxFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxFinanzasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxFinanzasActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
            try {
        // Construir DTO del filtro
        filtroPrestamosDTO filtro = new filtroPrestamosDTO();

        // Fechas
        Date fechaInicio = dateChooserFechaInicio.getSelectedDate().getTime();
        Date fechaFin = dateChooserFechaFin.getSelectedDate().getTime();
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (inicio.isAfter(fin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha fin.");
            return;
        }

        filtro.setFechaInicio(inicio);
        filtro.setFechaFin(fin);

        // Tipos de préstamo
        List<String> tipos = new ArrayList<>();
        if (checkBoxPersonal.isSelected()) tipos.add("Préstamo personal");
        if (checkBoxHipotecario.isSelected()) tipos.add("Préstamo hipotecario");
        if (checkBoxAutomotriz.isSelected()) tipos.add("Préstamo automotriz");
        if (checkBoxEstudiantil.isSelected()) tipos.add("Préstamo estudiantil");
        if (checkBoxNomina.isSelected()) tipos.add("Préstamo de nómina");
        filtro.setTiposPrestamo(tipos);

        // Departamentos
        List<String> departamentos = new ArrayList<>();
        if (checkBoxAcademico.isSelected()) departamentos.add("Académico");
        if (checkBoxInnovacion.isSelected()) departamentos.add("Inovación");
        if (checkBoxTecnologia.isSelected()) departamentos.add("Tecnología");
        if (checkBoxRH.isSelected()) departamentos.add("Recursos Humanos");
        if (checkBoxFinanzas.isSelected()) departamentos.add("Finanzas");
        filtro.setDepartamentos(departamentos);

        // Generar reporte
        Reporte reporte = new Reporte();
        reporte.generarReporte(filtro);
        

    } catch (NegocioException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JCheckBox checkBoxAcademico;
    private javax.swing.JCheckBox checkBoxAutomotriz;
    private javax.swing.JCheckBox checkBoxEstudiantil;
    private javax.swing.JCheckBox checkBoxFinanzas;
    private javax.swing.JCheckBox checkBoxHipotecario;
    private javax.swing.JCheckBox checkBoxInnovacion;
    private javax.swing.JCheckBox checkBoxNomina;
    private javax.swing.JCheckBox checkBoxPersonal;
    private javax.swing.JCheckBox checkBoxRH;
    private javax.swing.JCheckBox checkBoxTecnologia;
    private datechooser.beans.DateChooserCombo dateChooserFechaFin;
    private datechooser.beans.DateChooserCombo dateChooserFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
