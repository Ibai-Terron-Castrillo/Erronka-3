package mantenimendua;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import klaseak.Eserlekua;

public class EserlekuaTaula extends AbstractTableModel {
    private List<Eserlekua> lista;
    private String[] columnNames = {"ID", "ID_ARETO", "ZENBAKIA", "BETETA"};

    public EserlekuaTaula(List<Eserlekua> lista) {
        this.lista = lista;
    }

    public Eserlekua getEserlekuaAt(int rowIndex) {
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
        Eserlekua e = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return e.getId();
            case 1: return e.getIdAreto();
            case 2: return e.getZenbakia();
            case 3: return e.isBeteta();
            default: return null;
        }
    }

    public void setLista(List<Eserlekua> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }
}
