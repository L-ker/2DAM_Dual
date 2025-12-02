package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Tipo;

public class TipoDAOImpl implements TipoDAO {
    private Connection connection;

    public TipoDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarTipo(Tipo t) {
        try {
            String sql = "INSERT INTO Tipos (Tipo) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, t.getTipo());
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Tipo obtenerTipo(String tipo) {
        Tipo t = null;
        try {
            String sql = "SELECT * FROM Tipos WHERE Tipo=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) t = new Tipo(rs.getString("Tipo"));
        } catch(SQLException e) { e.printStackTrace(); }
        return t;
    }

    @Override
    public List<Tipo> obtenerTodosTipos() {
        List<Tipo> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tipos");
            while(rs.next()) lista.add(new Tipo(rs.getString("Tipo")));
        } catch(SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminarTipo(String tipo) {
        try {
            String sql = "DELETE FROM Tipos WHERE Tipo=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}
