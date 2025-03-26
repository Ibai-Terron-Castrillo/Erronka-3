package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Pelikula;
import util.DatabaseConnection;

public class PelikulakKudeatu {

    public List<Pelikula> lortuHornitzaileak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Pelikula> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Hornitzailea";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idHornitzailea");
                String Izena = rs.getString("Izena");
                String Deskripzioa = rs.getString("Deskripzioa");
                String Telefonoa = rs.getString("Telefonoa");
                String Email = rs.getString("Email");

                
            }
        } catch (Exception e) {
            System.out.println("Errorea");
        }
        return lista;
    }

   

      

    public void sortuHornitzailea(Pelikula Hornitzailea) {
        String sql = "INSERT INTO Hornitzailea (Izena, Deskripzioa, Telefonoa, Email) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Hornitzailea.getIzena());
          
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuHornitzailea(Pelikula Hornitzailea) {
        String sql = "UPDATE Hornitzailea SET Izena = ?, Deskripzioa = ?, Telefonoa = ?, Email = ? WHERE idHornitzailea = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Hornitzailea.getIzena());
           
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuHornitzailea(int idHornitzailea) {
        String sql = "DELETE FROM Hornitzailea WHERE idHornitzailea = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idHornitzailea);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pelikula> lortuPelikulak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Pelikula> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra FROM pelikula";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                int iraunaldia = rs.getInt("iraunaldia");
                String generoa = rs.getString("generoa");
                String sailkapena = rs.getString("sailkapena");
                String sinopsia = rs.getString("sinopsia");
                String aktoreak = rs.getString("aktoreak");
                String zuzendaria = rs.getString("zuzendaria");
                String kartela = rs.getString("kartela");
                String trailerra = rs.getString("trailerra");

                Pelikula p = new Pelikula(id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra);
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Errorea");
        }
        return lista;
    }

    public List<Pelikula> filtratuPelikulak(String irizpidea) {
        List<Pelikula> lista = new ArrayList<>();
        String sql = "SELECT id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra "
                + "FROM pelikula " + "WHERE CAST(id AS CHAR) LIKE ? " + "OR izena LIKE ? " + "OR generoa LIKE ? "
                + "OR sailkapena LIKE ? " + "OR sinopsia LIKE ? " + "OR aktoreak LIKE ? " + "OR zuzendaria LIKE ? "
                + "OR kartela LIKE ? " + "OR trailerra LIKE ?";
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

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                int iraunaldia = rs.getInt("iraunaldia");
                String generoa = rs.getString("generoa");
                String sailkapena = rs.getString("sailkapena");
                String sinopsia = rs.getString("sinopsia");
                String aktoreak = rs.getString("aktoreak");
                String zuzendaria = rs.getString("zuzendaria");
                String kartela = rs.getString("kartela");
                String trailerra = rs.getString("trailerra");

                Pelikula p = new Pelikula(id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra);
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Errorea");
        }
        return lista;
    }

    public void sortuPelikula(Pelikula pelikula) {
        String sql = "INSERT INTO pelikula (izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelikula.getIzena());
            ps.setInt(2, pelikula.getIraunaldia());
            ps.setString(3, pelikula.getGeneroa());
            ps.setString(4, pelikula.getSailkapena());
            ps.setString(5, pelikula.getSinopsia());
            ps.setString(6, pelikula.getAktoreak());
            ps.setString(7, pelikula.getZuzendaria());
            ps.setString(8, pelikula.getKartela());
            ps.setString(9, pelikula.getTrailerra());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuPelikula(Pelikula pelikula) {
        String sql = "UPDATE pelikula SET izena = ?, iraunaldia = ?, generoa = ?, sailkapena = ?, sinopsia = ?, aktoreak = ?, zuzendaria = ?, kartela = ?, trailerra = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pelikula.getIzena());
            ps.setInt(2, pelikula.getIraunaldia());
            ps.setString(3, pelikula.getGeneroa());
            ps.setString(4, pelikula.getSailkapena());
            ps.setString(5, pelikula.getSinopsia());
            ps.setString(6, pelikula.getAktoreak());
            ps.setString(7, pelikula.getZuzendaria());
            ps.setString(8, pelikula.getKartela());
            ps.setString(9, pelikula.getTrailerra());
            ps.setInt(10, pelikula.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuPelikula(int id) {
        String sql = "DELETE FROM pelikula WHERE id = ?";
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