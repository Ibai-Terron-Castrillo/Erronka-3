package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import klaseak.EsOrdutegia;
import util.DatabaseConnection;

public class EsOrdKudeatu {

    public void sortuEsOrdutegia(EsOrdutegia esOrdutegia) {
        String sql = "INSERT INTO eserlekua_ordutegian (id_eserleku, id_ordutegi, beteta) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, esOrdutegia.getIdEserlekua());
            pst.setInt(2, esOrdutegia.getIdOrdutegi());
            pst.setBoolean(3, esOrdutegia.isBeteta());

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EsOrdutegia> lortuEsOrdutegiak() {
        List<EsOrdutegia> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua_ordutegian";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idEserlekua = rs.getInt("id_eserleku");
                int idOrdutegi = rs.getInt("id_ordutegi");
                boolean beteta = rs.getBoolean("beteta");

                EsOrdutegia esOrdutegia = new EsOrdutegia(id, idEserlekua, idOrdutegi, beteta);
                lista.add(esOrdutegia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<EsOrdutegia> filtratuEsOrdutegiak(String irizpidea) {
        List<EsOrdutegia> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua_ordutegian WHERE CAST(id AS CHAR) LIKE ? OR CAST(id_eserleku AS CHAR) LIKE ? OR CAST(id_ordutegi AS CHAR) LIKE ? OR CAST(beteta AS CHAR) LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            String likeIrizpidea = "%" + irizpidea + "%";
            pst.setString(1, likeIrizpidea);
            pst.setString(2, likeIrizpidea);
            pst.setString(3, likeIrizpidea);
            pst.setString(4, likeIrizpidea);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idEserlekua = rs.getInt("id_eserleku");
                    int idOrdutegi = rs.getInt("id_ordutegi");
                    boolean beteta = rs.getBoolean("beteta");

                    EsOrdutegia esOrdutegia = new EsOrdutegia(id, idEserlekua, idOrdutegi, beteta);
                    lista.add(esOrdutegia);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void ezabatuEsOrdutegia(int id) {
        String sql = "DELETE FROM eserlekua_ordutegian WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eguneratuEsOrdutegia(EsOrdutegia esOrdutegia) {
        String sql = "UPDATE eserlekua_ordutegian SET id_eserleku = ?, id_ordutegi = ?, beteta = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, esOrdutegia.getIdEserlekua());
            pst.setInt(2, esOrdutegia.getIdOrdutegi());
            pst.setBoolean(3, esOrdutegia.isBeteta());
            pst.setInt(4, esOrdutegia.getId());

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}