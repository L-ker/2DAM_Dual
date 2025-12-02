package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Equipo;

public class EquipoDAOImpl implements EquipoDAO {
    private Connection connection;

    public EquipoDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarEquipo(Equipo e) {
        try {
            String sql = "INSERT INTO Equipo (idUsuario, idPokemon1, idPokemon2, idPokemon3, idPokemon4, idPokemon5, idPokemon6, Descripcion, Version) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, e.getIdUsuario());
            stmt.setInt(2, e.getIdPokemon1());
            stmt.setInt(3, e.getIdPokemon2());
            stmt.setInt(4, e.getIdPokemon3());
            stmt.setInt(5, e.getIdPokemon4());
            stmt.setInt(6, e.getIdPokemon5());
            stmt.setInt(7, e.getIdPokemon6());
            stmt.setString(8, e.getDescripcion());
            stmt.setInt(9, e.getVersion());
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public Equipo obtenerEquipo(int id) {
        Equipo e = null;
        try {
            String sql = "SELECT * FROM Equipo WHERE ID=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                e = new Equipo(
                        rs.getInt("ID"),
                        rs.getInt("idUsuario"),
                        rs.getInt("idPokemon1"),
                        rs.getInt("idPokemon2"),
                        rs.getInt("idPokemon3"),
                        rs.getInt("idPokemon4"),
                        rs.getInt("idPokemon5"),
                        rs.getInt("idPokemon6"),
                        rs.getString("Descripcion"),
                        rs.getInt("Version")
                );
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
        return e;
    }

    @Override
    public List<Equipo> obtenerTodosEquipos() {
        List<Equipo> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Equipo");
            while(rs.next()) {
                lista.add(new Equipo(
                        rs.getInt("ID"),
                        rs.getInt("idUsuario"),
                        rs.getInt("idPokemon1"),
                        rs.getInt("idPokemon2"),
                        rs.getInt("idPokemon3"),
                        rs.getInt("idPokemon4"),
                        rs.getInt("idPokemon5"),
                        rs.getInt("idPokemon6"),
                        rs.getString("Descripcion"),
                        rs.getInt("Version")
                ));
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    @Override
    public void actualizarEquipo(Equipo e) {
        try {
            String sql = "UPDATE Equipo SET idUsuario=?, idPokemon1=?, idPokemon2=?, idPokemon3=?, idPokemon4=?, idPokemon5=?, idPokemon6=?, Descripcion=?, Version=? WHERE ID=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, e.getIdUsuario());
            stmt.setInt(2, e.getIdPokemon1());
            stmt.setInt(3, e.getIdPokemon2());
            stmt.setInt(4, e.getIdPokemon3());
            stmt.setInt(5, e.getIdPokemon4());
            stmt.setInt(6, e.getIdPokemon5());
            stmt.setInt(7, e.getIdPokemon6());
            stmt.setString(8, e.getDescripcion());
            stmt.setInt(9, e.getVersion());
            stmt.setInt(10, e.getId());
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public void eliminarEquipo(int id) {
        try {
            String sql = "DELETE FROM Equipo WHERE ID=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }
}
