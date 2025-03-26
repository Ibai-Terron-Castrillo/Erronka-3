package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.Pelikula;

public class PelikulakTaula extends AbstractTableModel {
    private List<Pelikula> lista;
    private String[] columnNames = {"ID", "IZENA", "IRAUNALDIA", "GENEROA", "SAILKAPENA", "SINOPSIA", "AKTOREAK", "ZUZENDARIA", "KARTELA", "TRAILERRA"};

    public PelikulakTaula(List<Pelikula> lista) {
        this.lista = lista;
    }

    public Pelikula getPelikulaAt(int rowIndex) {
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
        Pelikula b = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return b.getId();
            case 1:
                return b.getIzena();
            case 2:
                return b.getIraunaldia();
            case 3:
                return b.getGeneroa();
            case 4:
                return b.getSailkapena();
            case 5:
                return b.getSinopsia();
            case 6:
                return b.getAktoreak();
            case 7:
                return b.getZuzendaria();
            case 8:
                return b.getKartela();
            case 9:
                return b.getTrailerra();
            default:
                return null;
        }
    }
}

