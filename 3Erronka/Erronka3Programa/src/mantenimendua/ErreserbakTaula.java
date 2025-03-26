package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.Erreserba;

public class ErreserbakTaula extends AbstractTableModel {
    private List<Erreserba> lista;
    private String[] columnNames = {"ID", "ID Bezeroa", "ID Ordutegi", "Kopurua", "Egoera"};

    public ErreserbakTaula(List<Erreserba> lista) {
        this.lista = lista;
    }

    public Erreserba getErreserbaAt(int rowIndex) {
        return lista.get(rowIndex);
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
        Erreserba b = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return b.getId();
            case 1:
                return b.getIdBezeroa();
            case 2:
                return b.getIdOrdutegi();
            case 3:
                return b.getKopurua();
            case 4:
                return b.getEgoera();
            default:
                return null;
        }
    }
}

