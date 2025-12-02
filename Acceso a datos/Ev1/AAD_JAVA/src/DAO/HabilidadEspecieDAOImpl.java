package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.HabilidadEspecie;

public class HabilidadEspecieDAOImpl implements HabilidadEspecieDAO {
    private Connection connection;

    public HabilidadEspecieDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarHabilidad(HabilidadEspecie h) {
        try {
            String sql = "INSERT INTO HabilidadesEspecie (Habilidad, Especie) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, h.getHabilidad());
            stmt.setString(2, h.getEspecie());
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    @Override
    public HabilidadEspecie obtenerHabilidad(String habilidad, String especie) {
        HabilidadEspecie h = null;
        try {
            String sql = "SELECT * FROM HabilidadesEspecie WHERE Habilidad=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, habilidad);
            stmt.setString(2, especie);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) h = new HabilidadEspecie(rs.getString("Habilidad"), rs.getString("Especie"));
        } catch(SQLException e) { e.printStackTrace(); }
        return h;
    }

    @Override
    public List<HabilidadEspecie> obtenerTodasHabilidades() {
        List<HabilidadEspecie> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HabilidadesEspecie");
            while(rs.next()) lista.add(new HabilidadEspecie(rs.getString("Habilidad"), rs.getString("Especie")));
        } catch(SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminarHabilidad(String habilidad, String especie) {
        try {
            String sql = "DELETE FROM HabilidadesEspecie WHERE Habilidad=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, habilidad);
            stmt.setString(2, especie);
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}
