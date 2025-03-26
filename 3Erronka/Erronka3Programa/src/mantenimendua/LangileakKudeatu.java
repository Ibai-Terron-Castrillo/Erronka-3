package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.langilea;
import util.DatabaseConnection;

public class LangileakKudeatu {

    public langilea lortuLangilea(langilea lan) {

        langilea langilea = null;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "select * from langilea where erabiltzailea=? and pasahitza=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, lan.getErabiltzailea());
            pst.setString(2, lan.getPasahitza());

            rs = pst.executeQuery();

            while (rs.next()) {
                langilea = new langilea(rs.getInt("Id"), rs.getString("izena"), rs.getString("abizena1"), rs.getString("abizena2"),
                        rs.getString("erabiltzailea"), rs.getString("pasahitza"), rs.getString("email"), rs.getString("telefonoa"), 
                        rs.getString("nan"), rs.getString("helbidea"), rs.getBoolean("admin"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return langilea;
    }

    public List<langilea> lortuLangileak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<langilea> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Langilea";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String izena = rs.getString("izena");
                String abizena1 = rs.getString("abizena1");
                String abizena2 = rs.getString("abizena2");
                String erabiltzailea = rs.getString("erabiltzailea");
                String pasahitza = rs.getString("pasahitza");
                String email = rs.getString("email");
                String telefonoa = rs.getString("telefonoa");
                String nan = rs.getString("nan");
                String helbidea = rs.getString("helbidea");
                Boolean admin = rs.getBoolean("admin");

                langilea b = new langilea(id, izena, abizena1, abizena2, erabiltzailea, pasahitza, email, telefonoa, nan,
                        helbidea, admin);
                lista.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<langilea> filtratuLangileak(String irizpidea) {
        List<langilea> lista = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM langilea " + "WHERE CAST(Id AS CHAR) LIKE ? " + "OR izena LIKE ? "
                + "OR abizena1 LIKE ? "+ "OR abizena2 LIKE ? " + "OR erabiltzailea LIKE ? " + "OR email LIKE ? " + "OR telefonoa LIKE ? " + "OR nan LIKE ?"
                + "OR helbidea LIKE ?" + "OR CAST(admin AS CHAR) LIKE ?";
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
            ps.setString(7, likeIrizpidea);
            ps.setString(8, likeIrizpidea);
            ps.setString(9, likeIrizpidea);
            ps.setString(10, likeIrizpidea);

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String izena = rs.getString("izena");
                String abizena1 = rs.getString("abizena1");
                String abizena2 = rs.getString("abizena2");
                String erabiltzailea = rs.getString("erabiltzailea");
                String pasahitza = rs.getString("pasahitza");
                String email = rs.getString("email");
                String telefonoa = rs.getString("telefonoa");
                String nan = rs.getString("nan");
                String helbidea = rs.getString("helbidea");
                Boolean admin = rs.getBoolean("admin");

                langilea b = new langilea(id, izena, abizena1, abizena2, erabiltzailea, pasahitza, email, telefonoa, nan,
                        helbidea, admin);
                lista.add(b);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void sortuLangilea(langilea Langilea) {
        String sql = "INSERT INTO Langilea (izena, abizena1, abizena2, erabiltzailea, pasahitza, email, telefonoa, nan, helbidea, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Langilea.getIzena());
            ps.setString(2, Langilea.getAbizena1());
            ps.setString(3, Langilea.getAbizena2());
            ps.setString(4, Langilea.getErabiltzailea());
            ps.setString(5, Langilea.getPasahitza());
            ps.setString(6, Langilea.getEmail());
            ps.setString(7, Langilea.getTelefonoa());
            ps.setString(8, Langilea.getNan());
            ps.setString(9, Langilea.getHelbidea());
            ps.setBoolean(10, Langilea.isAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuLangilea(langilea Langilea) {
        String sql = "UPDATE Langilea SET izena = ?, abizena1 = ?, abizena2 = ?, erabiltzailea = ?, pasahitza = ?, email = ?, telefonoa = ?, nan = ?, helbidea = ?, admin = ? WHERE Id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Langilea.getIzena());
            ps.setString(2, Langilea.getAbizena1());
            ps.setString(3, Langilea.getAbizena2());
            ps.setString(4, Langilea.getErabiltzailea());
            ps.setString(5, Langilea.getPasahitza());
            ps.setString(6, Langilea.getEmail());
            ps.setString(7, Langilea.getTelefonoa());
            ps.setString(8, Langilea.getNan());
            ps.setString(9, Langilea.getHelbidea());
            ps.setBoolean(10, Langilea.isAdmin());
            ps.setInt(11, Langilea.getIdLangilea());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuLangilea(int Id) {
        String sql = "DELETE FROM Langilea WHERE Id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
