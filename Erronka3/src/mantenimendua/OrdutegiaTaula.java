package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.Ordutegiak;

public class OrdutegiaTaula extends AbstractTableModel {
    private List<Ordutegiak> lista;
    private String[] columnNames = {"ID", "ID_PELIKULA", "ID_ARETO", "EGUNA", "ORDUA", "AMAITUA"};

    public OrdutegiaTaula(List<Ordutegiak> lista) {
        this.lista = lista;
    }

    public Ordutegiak getOrdutegiaAt(int rowIndex) {
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
        Ordutegiak b = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return b.getId();
            case 1:
                return b.getIdPelikula();
            case 2:
                return b.getIdAreto();
            case 3:
                return b.getEguna();
            case 4:
                return b.getOrdua();
            case 5:
                return b.isAmaitua();
            default:
                return null;
        }
    }
}

