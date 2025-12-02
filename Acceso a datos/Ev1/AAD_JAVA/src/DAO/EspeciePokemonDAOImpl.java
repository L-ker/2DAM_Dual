package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.EspeciePokemon;

public class EspeciePokemonDAOImpl implements EspeciePokemonDAO {
    private Connection connection;

    public EspeciePokemonDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarEspecie(EspeciePokemon e) {
        try {
            String sql = "INSERT INTO EspeciePokemon (Especie, Peso, Tipo1, Tipo2, VidaBase, AtaqueBase, AtaqueEspecialBase, DefensaBase, DefensaEspecialBase, VelocidadBase, Genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, e.getEspecie());
            stmt.setInt(2, e.getPeso());
            stmt.setString(3, e.getTipo1());
            stmt.setString(4, e.getTipo2());
            stmt.setInt(5, e.getVidaBase());
            stmt.setInt(6, e.getAtaqueBase());
            stmt.setInt(7, e.getAtaqueEspecialBase());
            stmt.setInt(8, e.getDefensaBase());
            stmt.setInt(9, e.getDefensaEspecialBase());
            stmt.setInt(10, e.getVelocidadBase());
            stmt.setString(11, e.getGenero());
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public EspeciePokemon obtenerEspecie(String especie) {
        EspeciePokemon e = null;
        try {
            String sql = "SELECT * FROM EspeciePokemon WHERE Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, especie);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                e = new EspeciePokemon(
                        rs.getString("Especie"),
                        rs.getInt("Peso"),
                        rs.getString("Tipo1"),
                        rs.getString("Tipo2"),
                        rs.getInt("VidaBase"),
                        rs.getInt("AtaqueBase"),
                        rs.getInt("AtaqueEspecialBase"),
                        rs.getInt("DefensaBase"),
                        rs.getInt("DefensaEspecialBase"),
                        rs.getInt("VelocidadBase"),
                        rs.getString("Genero")
                );
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
        return e;
    }

    @Override
    public List<EspeciePokemon> obtenerTodasEspecies() {
        List<EspeciePokemon> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EspeciePokemon");
            while(rs.next()) {
                lista.add(new EspeciePokemon(
                        rs.getString("Especie"),
                        rs.getInt("Peso"),
                        rs.getString("Tipo1"),
                        rs.getString("Tipo2"),
                        rs.getInt("VidaBase"),
                        rs.getInt("AtaqueBase"),
                        rs.getInt("AtaqueEspecialBase"),
                        rs.getInt("DefensaBase"),
                        rs.getInt("DefensaEspecialBase"),
                        rs.getInt("VelocidadBase"),
                        rs.getString("Genero")
                ));
            }
        } catch(SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    @Override
    public void actualizarEspecie(EspeciePokemon e) {
        try {
            String sql = "UPDATE EspeciePokemon SET Peso=?, Tipo1=?, Tipo2=?, VidaBase=?, AtaqueBase=?, AtaqueEspecialBase=?, DefensaBase=?, DefensaEspecialBase=?, VelocidadBase=?, Genero=? WHERE Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, e.getPeso());
            stmt.setString(2, e.getTipo1());
            stmt.setString(3, e.getTipo2());
            stmt.setInt(4, e.getVidaBase());
            stmt.setInt(5, e.getAtaqueBase());
            stmt.setInt(6, e.getAtaqueEspecialBase());
            stmt.setInt(7, e.getDefensaBase());
            stmt.setInt(8, e.getDefensaEspecialBase());
            stmt.setInt(9, e.getVelocidadBase());
            stmt.setString(10, e.getGenero());
            stmt.setString(11, e.getEspecie());
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }

    @Override
    public void eliminarEspecie(String especie) {
        try {
            String sql = "DELETE FROM EspeciePokemon WHERE Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, especie);
            stmt.executeUpdate();
        } catch(SQLException ex) { ex.printStackTrace(); }
    }
}
