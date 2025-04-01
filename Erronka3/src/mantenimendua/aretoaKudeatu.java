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

    // Filtrar por nombre (izena)
    public List<Aretoa> filtratuAretoakIzena(String izena) {
        List<Aretoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM aretoa WHERE izena LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + izena + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String aretoIzena = rs.getString("izena");
                    int edukiera = rs.getInt("edukiera");

                    Aretoa aretoa = new Aretoa(id, aretoIzena, edukiera);
                    lista.add(aretoa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar por capacidad (edukiera)
    public List<Aretoa> filtratuAretoakKapazitatea(int kapazitatea) {
        List<Aretoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM aretoa WHERE edukiera >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, kapazitatea);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String izena = rs.getString("izena");
                    int edukiera = rs.getInt("edukiera");

                    Aretoa aretoa = new Aretoa(id, izena, edukiera);
                    lista.add(aretoa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtener un areto por su ID
    public Aretoa lortuAretoaById(int id) {
        Aretoa aretoa = null;
        String sql = "SELECT * FROM aretoa WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String izena = rs.getString("izena");
                    int edukiera = rs.getInt("edukiera");

                    aretoa = new Aretoa(id, izena, edukiera);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aretoa;
    }

    // Eliminar un areto por su ID
    public boolean ezabatuAretoa(int id) {
        String sql = "DELETE FROM aretoa WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar un areto
    public boolean eguneratuAretoa(Aretoa aretoa) {
        String sql = "UPDATE aretoa SET izena = ?, edukiera = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aretoa.getIzena());
            ps.setInt(2, aretoa.getEdukiera());
            ps.setInt(3, aretoa.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Agregar un nuevo areto
    public boolean sortuAretoa(Aretoa aretoa) {
        String sql = "INSERT INTO aretoa (izena, edukiera) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aretoa.getIzena());
            ps.setInt(2, aretoa.getEdukiera());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}