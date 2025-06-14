/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.sistemagestorprestamos.utilidades;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Camila Zubía
 */
public class Editor extends AbstractCellEditor implements TableCellEditor{

    private Boolean valorActual;
    private JButton button;
    private JTable table;
    private int row;
    
    public Editor(JTable table, String text) {
        this.table = table;
        button = new JButton(text);
        //dentro de este constructor va a ir un action listener mas adelante 
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return valorActual;
    }
    
}
