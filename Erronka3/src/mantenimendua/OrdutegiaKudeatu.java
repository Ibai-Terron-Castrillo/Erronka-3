package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.Ordutegiak;
import util.DatabaseConnection;

public class OrdutegiaKudeatu {

    public List<Ordutegiak> lortuOrdutegia() {
        List<Ordutegiak> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordutegia";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idPelikula = rs.getInt("id_pelikula");
                int idAreto = rs.getInt("id_areto");
                String eguna = rs.getString("eguna");
                String ordua = rs.getString("ordua");
                boolean amaitua = rs.getBoolean("amaitua");

                Ordutegiak ordutegia = new Ordutegiak(id, idPelikula, idAreto, eguna, ordua, amaitua);
                lista.add(ordutegia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros por una hora exacta
    public List<Ordutegiak> filtratuOrdutegiaOrdua(String ordua) {
        List<Ordutegiak> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordutegia WHERE ordua = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, ordua);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idPelikula = rs.getInt("id_pelikula");
                    int idAreto = rs.getInt("id_areto");
                    String eguna = rs.getString("eguna");
                    boolean amaitua = rs.getBoolean("amaitua");

                    Ordutegiak ordutegia = new Ordutegiak(id, idPelikula, idAreto, eguna, ordua, amaitua);
                    lista.add(ordutegia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros por un día exacto
    public List<Ordutegiak> filtratuOrdutegiaEguna(String eguna) {
        List<Ordutegiak> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordutegia WHERE eguna = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, eguna);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idPelikula = rs.getInt("id_pelikula");
                    int idAreto = rs.getInt("id_areto");
                    String ordua = rs.getString("ordua");
                    boolean amaitua = rs.getBoolean("amaitua");

                    Ordutegiak ordutegia = new Ordutegiak(id, idPelikula, idAreto, eguna, ordua, amaitua);
                    lista.add(ordutegia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros por un rango de fechas
    public List<Ordutegiak> filtratuOrdutegiaRangoEguna(String egunaHasiera, String egunaAmaiera) {
        List<Ordutegiak> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordutegia WHERE eguna BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, egunaHasiera);
            pst.setString(2, egunaAmaiera);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idPelikula = rs.getInt("id_pelikula");
                    int idAreto = rs.getInt("id_areto");
                    String eguna = rs.getString("eguna");
                    String ordua = rs.getString("ordua");
                    boolean amaitua = rs.getBoolean("amaitua");

                    Ordutegiak ordutegia = new Ordutegiak(id, idPelikula, idAreto, eguna, ordua, amaitua);
                    lista.add(ordutegia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void sortuOrdutegia(Ordutegiak ordutegia) {
        String sql = "INSERT INTO ordutegia (id_pelikula, id_areto, eguna, ordua, amaitua) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

    public void eguneratuOrdutegia(Ordutegiak ordutegia) {
        String sql = "UPDATE ordutegia SET id_pelikula = ?, id_areto = ?, eguna = ?, ordua = ?, amaitua = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}