package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Bezeroa;
import util.DatabaseConnection;

public class BezeroakKudeatu {

    public List<Bezeroa> lortuBezeroak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Bezeroa> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM bezeroa";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String izena = rs.getString("izena");
                String abizena1 = rs.getString("abizena1");
                String abizena2 = rs.getString("abizena2");
                String nan = rs.getString("nan");
                String email = rs.getString("email");
                String pasahitza = rs.getString("pasahitza");

                Bezeroa b = new Bezeroa(id, izena, abizena1, abizena2, nan, email, pasahitza);
                lista.add(b);
            }
        } catch (Exception e) {
            System.out.println("Errorea");
        }
        return lista;
    }
    
    

    public List<Bezeroa> lortuBezeroak1() {
        List<Bezeroa> lista = new ArrayList<>();
        String sql = "SELECT * FROM bezeroa";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Bezeroa bezeroa = new Bezeroa(
                    rs.getInt("id"),
                    rs.getString("izena"),
                    rs.getString("abizena1"),
                    rs.getString("abizena2"),
                    rs.getString("nan"),
                    rs.getString("email"),
                    rs.getString("pasahitza")
                );
                lista.add(bezeroa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar bezeroak por nombre (Izena)
    public List<Bezeroa> filtratuBezeroakIzena(String izena) {
        List<Bezeroa> lista = new ArrayList<>();
        String sql = "SELECT * FROM bezeroa WHERE izena LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + izena + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bezeroa bezeroa = new Bezeroa(
                        rs.getInt("id"),
                        rs.getString("izena"),
                        rs.getString("abizena1"),
                        rs.getString("abizena2"),
                        rs.getString("nan"),
                        rs.getString("email"),
                        rs.getString("pasahitza")
                    );
                    lista.add(bezeroa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar bezeroak por NAN
    public List<Bezeroa> filtratuBezeroakNan(String nan) {
        List<Bezeroa> lista = new ArrayList<>();
        String sql = "SELECT * FROM bezeroa WHERE nan LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nan + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bezeroa bezeroa = new Bezeroa(
                        rs.getInt("id"),
                        rs.getString("izena"),
                        rs.getString("abizena1"),
                        rs.getString("abizena2"),
                        rs.getString("nan"),
                        rs.getString("email"),
                        rs.getString("pasahitza")
                    );
                    lista.add(bezeroa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar bezeroak por email
    public List<Bezeroa> filtratuBezeroakEmail(String email) {
        List<Bezeroa> lista = new ArrayList<>();
        String sql = "SELECT * FROM bezeroa WHERE email LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + email + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bezeroa bezeroa = new Bezeroa(
                        rs.getInt("id"),
                        rs.getString("izena"),
                        rs.getString("abizena1"),
                        rs.getString("abizena2"),
                        rs.getString("nan"),
                        rs.getString("email"),
                        rs.getString("pasahitza")
                    );
                    lista.add(bezeroa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void sortuBezeroa(Bezeroa bezeroa) {
        String sql = "INSERT INTO bezeroa (izena, abizena1, abizena2, nan, email, pasahitza) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bezeroa.getIzena());
            ps.setString(2, bezeroa.getAbizena1());
            ps.setString(3, bezeroa.getAbizena2());
            ps.setString(4, bezeroa.getNan());
            ps.setString(5, bezeroa.getEmail());
            ps.setString(6, bezeroa.getPasahitza());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuBezeroa(Bezeroa bezeroa) {
        String sql = "UPDATE bezeroa SET izena = ?, abizena1 = ?, abizena2 = ?, nan = ?, email = ?, pasahitza = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bezeroa.getIzena());
            ps.setString(2, bezeroa.getAbizena1());
            ps.setString(3, bezeroa.getAbizena2());
            ps.setString(4, bezeroa.getNan());
            ps.setString(5, bezeroa.getEmail());
            ps.setString(6, bezeroa.getPasahitza());
            ps.setInt(7, bezeroa.getIdBezeroa());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuBezeroa(int id) {
        String sql = "DELETE FROM bezeroa WHERE id = ?";
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