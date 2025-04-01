package mantenimendua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import klaseak.Eserlekua;
import util.DatabaseConnection;

public class EserlekuaKudeatu {

    public void sortuEserlekua(Eserlekua eserlekua) {
        String sql = "INSERT INTO eserlekua (id_areto, zenbakia, beteta) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, eserlekua.getIdAreto());
            pst.setInt(2, eserlekua.getZenbakia());
            pst.setBoolean(3, eserlekua.isBeteta());

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Eserlekua> lortuEserlekuak() {
        List<Eserlekua> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idAreto = rs.getInt("id_areto");
                int zenbakia = rs.getInt("zenbakia");
                boolean beteta = rs.getBoolean("beteta");

                Eserlekua eserlekua = new Eserlekua(id, idAreto, zenbakia, beteta);
                lista.add(eserlekua);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Eserlekua> filtratuEserlekuak(String irizpidea) {
        List<Eserlekua> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua WHERE CAST(id AS CHAR) LIKE ? OR CAST(id_areto AS CHAR) LIKE ? OR CAST(zenbakia AS CHAR) LIKE ? OR CAST(beteta AS CHAR) LIKE ?";

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
                    int idAreto = rs.getInt("id_areto");
                    int zenbakia = rs.getInt("zenbakia");
                    boolean beteta = rs.getBoolean("beteta");

                    Eserlekua eserlekua = new Eserlekua(id, idAreto, zenbakia, beteta);
                    lista.add(eserlekua);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar por ID del areto
    public List<Eserlekua> filtratuEserlekuakAretoId(int idAreto) {
        List<Eserlekua> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua WHERE id_areto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idAreto);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int zenbakia = rs.getInt("zenbakia");
                    boolean beteta = rs.getBoolean("beteta");

                    Eserlekua eserlekua = new Eserlekua(id, idAreto, zenbakia, beteta);
                    lista.add(eserlekua);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Filtrar por estado (beteta)
    public List<Eserlekua> filtratuEserlekuakBeteta(boolean beteta) {
        List<Eserlekua> lista = new ArrayList<>();
        String sql = "SELECT * FROM eserlekua WHERE beteta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setBoolean(1, beteta);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int idAreto = rs.getInt("id_areto");
                    int zenbakia = rs.getInt("zenbakia");

                    Eserlekua eserlekua = new Eserlekua(id, idAreto, zenbakia, beteta);
                    lista.add(eserlekua);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void ezabatuEserlekua(int id) {
        String sql = "DELETE FROM eserlekua WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eguneratuEserlekua(Eserlekua eserlekua) {
        String sql = "UPDATE eserlekua SET id_areto = ?, zenbakia = ?, beteta = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, eserlekua.getIdAreto());
            pst.setInt(2, eserlekua.getZenbakia());
            pst.setBoolean(3, eserlekua.isBeteta());
            pst.setInt(4, eserlekua.getId());

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}