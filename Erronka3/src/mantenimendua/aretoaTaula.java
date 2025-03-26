package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.Aretoa;

public class aretoaTaula extends AbstractTableModel {
    private List<Aretoa> lista;
    private String[] columnNames = {"ID", "IZENA", "EDUKIERA"};

    public aretoaTaula(List<Aretoa> lista) {
        this.lista = lista;
    }

    public Aretoa getAretoaAt(int rowIndex) {
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
        Aretoa a = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.getId();
            case 1: return a.getIzena();
            case 2: return a.getEdukiera();
            default: return null;
        }
    }

    public void setLista(List<Aretoa> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }
}
