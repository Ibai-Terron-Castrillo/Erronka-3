package mantenimendua;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import klaseak.EsOrdutegia;

public class EsOrdTaula extends AbstractTableModel {
    private List<EsOrdutegia> lista;
    private String[] columnNames = {"ID", "ID_ESERLEKU", "ID_ORDUTEGI", "BETETA"};

    public EsOrdTaula(List<EsOrdutegia> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EsOrdutegia eo = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return eo.getId();
            case 1: return eo.getIdEserlekua();
            case 2: return eo.getIdOrdutegi();
            case 3: return eo.isBeteta();
            default: return null;
        }
    }

    public EsOrdutegia getEsOrdutegiaAt(int rowIndex) {
        return lista.get(rowIndex);
    }

    public void setLista(List<EsOrdutegia> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }

    public static class CustomCellRenderer extends JTextArea implements TableCellRenderer {

        public CustomCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");

            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) < getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }

            // Cambiar el color de fondo de la fila seleccionada
            if (isSelected) {
                setBackground(new Color(200, 200, 200)); // Color más oscuro para la fila seleccionada
            } else {
                setBackground(Color.WHITE); // Color predeterminado para filas no seleccionadas
            }

            return this;
        }
    }
}
