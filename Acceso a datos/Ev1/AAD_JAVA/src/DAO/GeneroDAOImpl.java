package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Genero;

public class GeneroDAOImpl implements GeneroDAO {
    private Connection connection;

    public GeneroDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarGenero(Genero g) {
        try {
            String sql = "INSERT INTO Generos (Genero, Especie) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, g.getGenero());
            stmt.setString(2, g.getEspecie());
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Genero obtenerGenero(String genero, String especie) {
        Genero g = null;
        try {
            String sql = "SELECT * FROM Generos WHERE Genero=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, genero);
            stmt.setString(2, especie);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) g = new Genero(rs.getString("Genero"), rs.getString("Especie"));
        } catch(SQLException e) { e.printStackTrace(); }
        return g;
    }

    @Override
    public List<Genero> obtenerTodosGeneros() {
        List<Genero> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Generos");
            while(rs.next()) lista.add(new Genero(rs.getString("Genero"), rs.getString("Especie")));
        } catch(SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminarGenero(String genero, String especie) {
        try {
            String sql = "DELETE FROM Generos WHERE Genero=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, genero);
            stmt.setString(2, especie);
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}
