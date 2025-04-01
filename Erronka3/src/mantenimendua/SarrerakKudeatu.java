package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Sarrerak;
import util.DatabaseConnection;

public class SarrerakKudeatu {

    public List<Sarrerak> lortuSarrerak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Sarrerak> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM sarrera";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idErreserba = rs.getInt("id_erreserba");
                double prezioa = rs.getDouble("prezioa");
                int idEserleku = rs.getInt("id_eserleku");

                Sarrerak sarrera = new Sarrerak(id, idErreserba, prezioa, idEserleku);
                lista.add(sarrera);
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

    public List<Sarrerak> filtratuSarrerak(String irizpidea) {
        List<Sarrerak> lista = new ArrayList<>();
        String sql = "SELECT id, id_erreserba, prezioa, id_eserleku FROM sarrera "
                   + "WHERE CAST(id AS CHAR) LIKE ? "
                   + "OR CAST(id_erreserba AS CHAR) LIKE ? "
                   + "OR CAST(prezioa AS CHAR) LIKE ? "
                   + "OR CAST(id_eserleku AS CHAR) LIKE ?";
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

            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idErreserba = rs.getInt("id_erreserba");
                double prezioa = rs.getDouble("prezioa");
                int idEserleku = rs.getInt("id_eserleku");

                Sarrerak sarrera = new Sarrerak(id, idErreserba, prezioa, idEserleku);
                lista.add(sarrera);
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

    // Filtro: ID Erreserbaren arabera
    public List<Sarrerak> filtratuSarrerakErreserbaId(int idErreserba) {
        List<Sarrerak> lista = new ArrayList<>();
        String sql = "SELECT * FROM sarrera WHERE id_erreserba = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idErreserba);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    double prezioa = rs.getDouble("prezioa");
                    int idEserleku = rs.getInt("id_eserleku");

                    Sarrerak sarrera = new Sarrerak(id, idErreserba, prezioa, idEserleku);
                    lista.add(sarrera);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtro: Prezio tartearen arabera
    public List<Sarrerak> filtratuSarrerakPrezioTartea(double prezioMin, double prezioMax) {
        List<Sarrerak> lista = new ArrayList<>();
        String sql = "SELECT * FROM sarrera WHERE prezioa BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, prezioMin);
            ps.setDouble(2, prezioMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idErreserba = rs.getInt("id_erreserba");
                    double prezioa = rs.getDouble("prezioa");
                    int idEserleku = rs.getInt("id_eserleku");

                    Sarrerak sarrera = new Sarrerak(id, idErreserba, prezioa, idEserleku);
                    lista.add(sarrera);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtro: ID Eserlekuaren arabera
    public List<Sarrerak> filtratuSarrerakEserlekuId(int idEserleku) {
        List<Sarrerak> lista = new ArrayList<>();
        String sql = "SELECT * FROM sarrera WHERE id_eserleku = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEserleku);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idErreserba = rs.getInt("id_erreserba");
                    double prezioa = rs.getDouble("prezioa");

                    Sarrerak sarrera = new Sarrerak(id, idErreserba, prezioa, idEserleku);
                    lista.add(sarrera);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eguneratuSarrera(Sarrerak sarrera) {
        String sql = "UPDATE sarrera SET id_erreserba = ?, prezioa = ?, id_eserleku = ? WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sarrera.getIdErreserba());
            ps.setDouble(2, sarrera.getPrezioa());
            ps.setInt(3, sarrera.getIdEserleku());
            ps.setInt(4, sarrera.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuSarrera(int id) {
        String sql = "DELETE FROM sarrera WHERE id = ?";
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