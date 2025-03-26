package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Ordutegia;
import util.DatabaseConnection;

public class OrdutegiaKudeatu {

    public List<Ordutegia> lortuOrdutegia() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Ordutegia> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM ordutegia";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int id_pelikula = rs.getInt("id_pelikula");
                int id_areto = rs.getInt("id_areto");
                String eguna = rs.getString("eguna");
                String ordua = rs.getString("ordua");
                boolean amaitua = rs.getBoolean("amaitua");

                Ordutegia b = new Ordutegia(id, id_pelikula, id_areto, eguna, ordua, amaitua);
                lista.add(b);
            }
        } catch (Exception e) {
            System.out.println("Errorea");
        }
        return lista;
    }

    public List<Ordutegia> filtratuOrdutegia(String irizpidea) {
        List<Ordutegia> lista = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM ordutegia " + "WHERE CAST(id AS CHAR) LIKE ? " + "OR CAST(id_pelikula AS CHAR) LIKE ? "
                + "OR CAST(id_areto AS CHAR) LIKE ? " + "OR eguna LIKE ? " + "OR ordua LIKE ? "
                + "OR CAST(amaitua AS CHAR) LIKE ?";
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
            ps.setString(6, likeIrizpidea);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_pelikula = rs.getInt("id_pelikula");
                int id_areto = rs.getInt("id_areto");
                String eguna = rs.getString("eguna");
                String ordua = rs.getString("ordua");
                boolean amaitua = rs.getBoolean("amaitua");

                Ordutegia b = new Ordutegia(id, id_pelikula, id_areto, eguna, ordua, amaitua);
                lista.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void sortuOrdutegia(Ordutegia ordutegia) {
        String sql = "INSERT INTO ordutegia (id_pelikula, id_areto, eguna, ordua, amaitua) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordutegia.getIdPelikula());
            ps.setInt(2, ordutegia.getIdAreto());
            ps.setString(3, ordutegia.getEguna());
            ps.setString(4, ordutegia.getOrdua());
            ps.setBoolean(5, ordutegia.isAmaitua());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuOrdutegia(Ordutegia ordutegia) {
        String sql = "UPDATE ordutegia SET id_pelikula = ?, id_areto = ?, eguna = ?, ordua = ?, amaitua = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ordutegia.getIdPelikula());
            ps.setInt(2, ordutegia.getIdAreto());
            ps.setString(3, ordutegia.getEguna());
            ps.setString(4, ordutegia.getOrdua());
            ps.setBoolean(5, ordutegia.isAmaitua());
            ps.setInt(6, ordutegia.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuOrdutegia(int id) {
        String sql = "DELETE FROM ordutegia WHERE id = ?";
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