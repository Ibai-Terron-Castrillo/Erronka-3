package mantenimendua;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import klaseak.ErrHistorikoa;

public class ErrHistTaula extends AbstractTableModel {
    private List<ErrHistorikoa> lista;
    private String[] columnNames = {"ID", "ID_Bezero", "ID_Ordutegi", "Kopurua", "Amaiera Data"};

    public ErrHistTaula(List<ErrHistorikoa> lista) {
        this.lista = lista;
    }

    public ErrHistorikoa getErrHistorikoaAt(int rowIndex) {
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
        ErrHistorikoa e = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return e.getId();
            case 1:
                return e.getIdBezeroa();
            case 2:
                return e.getIdOrdutegi();
            case 3:
                return e.getKopurua();
            case 4:
                return e.getAmaieraData();
            default:
                return null;
        }
    }

    public void setLista(List<ErrHistorikoa> nuevaLista) {
        this.lista = nuevaLista;
        fireTableDataChanged();
    }
}
