package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import klaseak.Erreserba;
import util.DatabaseConnection;

public class ErreserbakKudeatu {

    private static final Statement DatabaseConnection = null;

	public List<Erreserba> lortuEskaerak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Erreserba> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, egoera FROM erreserba";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idBezeroa = rs.getInt("id_bezero");
                int idOrdutegi = rs.getInt("id_ordutegi");
                int kopurua = rs.getInt("kopurua");
                String egoera = rs.getString("egoera");

                Erreserba b = new Erreserba(id, idBezeroa, idOrdutegi, kopurua, egoera);
                lista.add(b);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Erreserba> filtratuEskaerak(String irizpidea) {
        List<Erreserba> lista = new ArrayList<>();
        String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, egoera "
                + "FROM erreserba " + "WHERE CAST(id AS CHAR) LIKE ? " + "OR CAST(id_bezero AS CHAR) LIKE ? "
                + "OR CAST(id_ordutegi AS CHAR) LIKE ? " + "OR CAST(kopurua AS CHAR) LIKE ? " + "OR egoera LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement(sql);

            String likeIrizpidea = "%" + irizpidea + "%";
            ps.setString(1, likeIrizpidea);
            ps.setString(2, likeIrizpidea);
            ps.setString(3, likeIrizpidea);
            ps.setString(4, likeIrizpidea);
            ps.setString(5, likeIrizpidea);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idBezeroa = rs.getInt("id_bezero");
                int idOrdutegi = rs.getInt("id_ordutegi");
                int kopurua = rs.getInt("kopurua");
                String egoera = rs.getString("egoera");

                Erreserba b = new Erreserba(id, idBezeroa, idOrdutegi, kopurua, egoera);
                lista.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void sortuEskaera(Erreserba eskaera) {
        String sql = "INSERT INTO erreserba (id_bezero, id_ordutegi, kopurua) VALUES (?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eskaera.getIdBezeroa());
            ps.setInt(2, eskaera.getIdOrdutegi());
            ps.setInt(3, eskaera.getKopurua());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuEskaera(Erreserba eskaera) {
        String sql = "UPDATE erreserba SET id_bezero = ?, id_ordutegi = ?, kopurua = ?, egoera = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eskaera.getIdBezeroa());
            ps.setInt(2, eskaera.getIdOrdutegi());
            ps.setInt(3, eskaera.getKopurua());
            ps.setString(4, eskaera.getEgoera());
            ps.setInt(5, eskaera.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuEskaera(int id) {
        String sql = "DELETE FROM erreserba WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}