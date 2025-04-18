package mantenimendua;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import klaseak.ErrHistorikoa;
import util.DatabaseConnection;

public class ErrHistKudeatu {

    // Obtener todos los registros históricos
    public List<ErrHistorikoa> lortuEskaerak() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ErrHistorikoa> lista = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, amaiera_data FROM erreserbak_historikoa";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idBezeroa = rs.getInt("id_bezero");
                int idOrdutegi = rs.getInt("id_ordutegi");
                int kopurua = rs.getInt("kopurua");
                Date amaieraData = rs.getDate("amaiera_data");

                ErrHistorikoa e = new ErrHistorikoa(id, idBezeroa, idOrdutegi, kopurua, amaieraData);
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros históricos por un criterio general
    public List<ErrHistorikoa> filtratuEskaerak(String irizpidea) {
        List<ErrHistorikoa> lista = new ArrayList<>();
        String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, amaiera_data "
                + "FROM erreserbak_historikoa "
                + "WHERE CAST(id AS CHAR) LIKE ? "
                + "OR CAST(id_bezero AS CHAR) LIKE ? "
                + "OR CAST(id_ordutegi AS CHAR) LIKE ? "
                + "OR CAST(kopurua AS CHAR) LIKE ? "
                + "OR CAST(amaiera_data AS CHAR) LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String likeIrizpidea = "%" + irizpidea + "%";
            ps.setString(1, likeIrizpidea);
            ps.setString(2, likeIrizpidea);
            ps.setString(3, likeIrizpidea);
            ps.setString(4, likeIrizpidea);
            ps.setString(5, likeIrizpidea);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idBezeroa = rs.getInt("id_bezero");
                    int idOrdutegi = rs.getInt("id_ordutegi");
                    int kopurua = rs.getInt("kopurua");
                    Date amaieraData = rs.getDate("amaiera_data");

                    ErrHistorikoa e = new ErrHistorikoa(id, idBezeroa, idOrdutegi, kopurua, amaieraData);
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros históricos por ID del cliente (id_bezero)
    public List<ErrHistorikoa> filtratuEskaerakBezeroId(int idBezeroa) {
        List<ErrHistorikoa> lista = new ArrayList<>();
        String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, amaiera_data FROM erreserbak_historikoa WHERE id_bezero = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBezeroa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idOrdutegi = rs.getInt("id_ordutegi");
                    int kopurua = rs.getInt("kopurua");
                    Date amaieraData = rs.getDate("amaiera_data");

                    ErrHistorikoa e = new ErrHistorikoa(id, idBezeroa, idOrdutegi, kopurua, amaieraData);
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar registros históricos por fecha de finalización (amaiera_data)
    public List<ErrHistorikoa> filtratuEskaerakAmaieraData(Date amaieraData) {
        List<ErrHistorikoa> lista = new ArrayList<>();
        String sql = "SELECT id, id_bezero, id_ordutegi, kopurua, amaiera_data FROM erreserbak_historikoa WHERE amaiera_data = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, amaieraData);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idBezeroa = rs.getInt("id_bezero");
                    int idOrdutegi = rs.getInt("id_ordutegi");
                    int kopurua = rs.getInt("kopurua");

                    ErrHistorikoa e = new ErrHistorikoa(id, idBezeroa, idOrdutegi, kopurua, amaieraData);
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Funciones existentes (no modificadas)
    public void sortuEskaera(ErrHistorikoa eskaera) {
        String sql = "INSERT INTO erreserbak_historikoa (id_bezero, id_ordutegi, kopurua, amaiera_data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eskaera.getIdBezeroa());
            ps.setInt(2, eskaera.getIdOrdutegi());
            ps.setInt(3, eskaera.getKopurua());
            ps.setDate(4, eskaera.getAmaieraData());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eguneratuEskaera(ErrHistorikoa eskaera) {
        String sql = "UPDATE erreserbak_historikoa SET id_bezero = ?, id_ordutegi = ?, kopurua = ?, amaiera_data = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eskaera.getIdBezeroa());
            ps.setInt(2, eskaera.getIdOrdutegi());
            ps.setInt(3, eskaera.getKopurua());
            ps.setDate(4, eskaera.getAmaieraData());
            ps.setInt(5, eskaera.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ezabatuEskaera(int id) {
        String sql = "DELETE FROM erreserbak_historikoa WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}