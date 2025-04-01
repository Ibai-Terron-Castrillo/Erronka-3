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

   

      

    public List<String> lortuGeneroDistinct() {
	    List<String> generos = new ArrayList<>();
	    String sql = "SELECT DISTINCT generoa FROM pelikula"; // Consulta para obtener géneros únicos
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            generos.add(rs.getString("generoa")); // Agregar cada género a la lista
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return generos;
	}

	// Método para filtrar películas por género
	public List<Pelikula> filtratuPelikulakGenero(String genero) {
	    List<Pelikula> pelikulak = new ArrayList<>();
	    String sql = "SELECT id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra FROM pelikula WHERE generoa = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, genero); // Establecer el parámetro del género
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                // Crear un objeto Pelikula con los datos obtenidos
	                Pelikula pelikula = new Pelikula(
	                    rs.getInt("id"),
	                    rs.getString("izena"),
	                    rs.getInt("iraunaldia"),
	                    rs.getString("generoa"),
	                    rs.getString("sailkapena"),
	                    rs.getString("sinopsia"),
	                    rs.getString("aktoreak"),
	                    rs.getString("zuzendaria"),
	                    rs.getString("kartela"),
	                    rs.getString("trailerra")
	                );
	                pelikulak.add(pelikula); // Agregar la película a la lista
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return pelikulak;
	}
	
	public List<String> lortuDistinctSailkapena() {
	    List<String> sailkapenak = new ArrayList<>();
	    String sql = "SELECT DISTINCT sailkapena FROM pelikula"; // Consulta para obtener las calificaciones únicas
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            sailkapenak.add(rs.getString("sailkapena")); // Agregar cada calificación a la lista
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sailkapenak;
	}
	public List<Pelikula> filtratuPelikulakSailkapena(String sailkapena) {
	    List<Pelikula> pelikulak = new ArrayList<>();
	    String sql = "SELECT id, izena, iraunaldia, generoa, sailkapena, sinopsia, aktoreak, zuzendaria, kartela, trailerra FROM pelikula WHERE sailkapena = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, sailkapena); // Establecer el parámetro del sailkapena
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                // Crear un objeto Pelikula con los datos obtenidos
	                Pelikula pelikula = new Pelikula(
	                    rs.getInt("id"),
	                    rs.getString("izena"),
	                    rs.getInt("iraunaldia"),
	                    rs.getString("generoa"),
	                    rs.getString("sailkapena"),
	                    rs.getString("sinopsia"),
	                    rs.getString("aktoreak"),
	                    rs.getString("zuzendaria"),
	                    rs.getString("kartela"),
	                    rs.getString("trailerra")
	                );
	                pelikulak.add(pelikula); // Agregar la película a la lista
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return pelikulak;
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