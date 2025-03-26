package mantenimendua;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import klaseak.langilea;

public class LangileakTaula extends AbstractTableModel {
    private List<langilea> lista;
    private String[] columnNames = {"ID", "IZENA", "ABIZENA1", "ABIZENA2", "ERABILTZAILEA", "PASAHITZA", "EMAIL", "TELEFONOA", "NAN", "HELBIDEA", "ADMIN"};

    public LangileakTaula(List<langilea> lista) {
        this.lista = lista;
    }

    public langilea getLangileaAt(int rowIndex) {
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
        langilea b = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return b.getIdLangilea();
            case 1:
                return b.getIzena();
            case 2:
                return b.getAbizena1();
                case 3:
                return b.getAbizena2();
            case 4:
                return b.getErabiltzailea();
            case 5:
                return b.getPasahitza();
            case 6:
                return b.getEmail();
            case 7:
                return b.getTelefonoa();
            case 8:
                return b.getNan();
            case 9:
                return b.getHelbidea();
            
            case 10:
                return b.isAdmin();
            default:
                return null;
        }
    }
}

