package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Aretoa;
import util.DatabaseConnection;

public class aretoaKudeatu {

    public List<Aretoa> lortuAretoak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Aretoa> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM aretoa";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                int edukiera = rs.getInt("edukiera");

                Aretoa aretoa = new Aretoa(id, izena, edukiera);
                lista.add(aretoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return lista;
    }

    public List<Aretoa> filtratuAretoak(String irizpidea) {
        List<Aretoa> lista = new ArrayList<>();
        String sql = "SELECT id, izena, edukiera FROM aretoa "
                   + "WHERE CAST(id AS CHAR) LIKE ? "
                   + "OR izena LIKE ? "
                   + "OR CAST(edukiera AS CHAR) LIKE ?";
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

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                int edukiera = rs.getInt("edukiera");

                Aretoa aretoa = new Aretoa(id, izena, edukiera);
                lista.add(aretoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return lista;
    }

    public void sortuAretoa(Aretoa aretoa) {
        String sql = "INSERT INTO aretoa (izena, edukiera) VALUES (?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aretoa.getIzena());
            ps.setInt(2, aretoa.getEdukiera());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuAretoa(Aretoa aretoa) {
        String sql = "UPDATE aretoa SET izena = ?, edukiera = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, aretoa.getIzena());
            ps.setInt(2, aretoa.getEdukiera());
            ps.setInt(3, aretoa.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuAretoa(int id) {
        String sql = "DELETE FROM aretoa WHERE id = ?";
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
