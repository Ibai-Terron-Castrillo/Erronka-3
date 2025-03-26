package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.Sarrerak;

public class sarrerakTaula extends AbstractTableModel {
    private List<Sarrerak> lista;
    private String[] columnNames = {"ID", "ID_ERRESERBA", "PREZIOA", "ID_ESERLEKU"};

    public sarrerakTaula(List<Sarrerak> lista) {
        this.lista = lista;
    }

    public Sarrerak getSarrerakAt(int rowIndex) {
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
        Sarrerak s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getId();
            case 1: return s.getIdErreserba();
            case 2: return s.getPrezioa();
            case 3: return s.getIdEserleku();
            default: return null;
        }
    }

    public void setLista(List<Sarrerak> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }
}