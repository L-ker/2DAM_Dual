package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Pokemon;

public class PokemonDAOImpl implements PokemonDAO {

    private Connection connection;

    public PokemonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarPokemon(Pokemon p) {
        try {
            String sql = "INSERT INTO Pokemon (Especie, Objeto, Naturaleza, Habilidad, Genero, Movimiento1, Movimiento2, Movimiento3, Movimiento4, " +
                    "IvVida, IvAtaque, IvAtaqueEspecial, IvDefensa, IvDefensaEspecial, IvVelocidad, " +
                    "EvVida, EvAtaque, EvAtaqueEspecial, EvDefensa, EvDefensaEspecial, EvVelocidad, Descripcion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getEspecie());
            stmt.setString(2, p.getObjeto());
            stmt.setString(3, p.getNaturaleza());
            stmt.setString(4, p.getHabilidad());
            stmt.setString(5, p.getGenero());
            stmt.setString(6, p.getMovimiento1());
            stmt.setString(7, p.getMovimiento2());
            stmt.setString(8, p.getMovimiento3());
            stmt.setString(9, p.getMovimiento4());
            stmt.setInt(10, p.getIvVida());
            stmt.setInt(11, p.getIvAtaque());
            stmt.setInt(12, p.getIvAtaqueEspecial());
            stmt.setInt(13, p.getIvDefensa());
            stmt.setInt(14, p.getIvDefensaEspecial());
            stmt.setInt(15, p.getIvVelocidad());
            stmt.setInt(16, p.getEvVida());
            stmt.setInt(17, p.getEvAtaque());
            stmt.setInt(18, p.getEvAtaqueEspecial());
            stmt.setInt(19, p.getEvDefensa());
            stmt.setInt(20, p.getEvDefensaEspecial());
            stmt.setInt(21, p.getEvVelocidad());
            stmt.setString(22, p.getDescripcion());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon obtenerPokemon(int id) {
        Pokemon p = null;
        try {
            String sql = "SELECT * FROM Pokemon WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Pokemon(
                        rs.getInt("id"),
                        rs.getString("Especie"),
                        rs.getString("Objeto"),
                        rs.getString("Naturaleza"),
                        rs.getString("Habilidad"),
                        rs.getString("Genero"),
                        rs.getString("Movimiento1"),
                        rs.getString("Movimiento2"),
                        rs.getString("Movimiento3"),
                        rs.getString("Movimiento4"),
                        rs.getInt("IvVida"),
                        rs.getInt("IvAtaque"),
                        rs.getInt("IvAtaqueEspecial"),
                        rs.getInt("IvDefensa"),
                        rs.getInt("IvDefensaEspecial"),
                        rs.getInt("IvVelocidad"),
                        rs.getInt("EvVida"),
                        rs.getInt("EvAtaque"),
                        rs.getInt("EvAtaqueEspecial"),
                        rs.getInt("EvDefensa"),
                        rs.getInt("EvDefensaEspecial"),
                        rs.getInt("EvVelocidad"),
                        rs.getString("Descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Pokemon> obtenerTodosPokemon() {
        List<Pokemon> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Pokemon";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Pokemon(
                        rs.getInt("id"),
                        rs.getString("Especie"),
                        rs.getString("Objeto"),
                        rs.getString("Naturaleza"),
                        rs.getString("Habilidad"),
                        rs.getString("Genero"),
                        rs.getString("Movimiento1"),
                        rs.getString("Movimiento2"),
                        rs.getString("Movimiento3"),
                        rs.getString("Movimiento4"),
                        rs.getInt("IvVida"),
                        rs.getInt("IvAtaque"),
                        rs.getInt("IvAtaqueEspecial"),
                        rs.getInt("IvDefensa"),
                        rs.getInt("IvDefensaEspecial"),
                        rs.getInt("IvVelocidad"),
                        rs.getInt("EvVida"),
                        rs.getInt("EvAtaque"),
                        rs.getInt("EvAtaqueEspecial"),
                        rs.getInt("EvDefensa"),
                        rs.getInt("EvDefensaEspecial"),
                        rs.getInt("EvVelocidad"),
                        rs.getString("Descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizarPokemon(Pokemon p) {
        try {
            String sql = "UPDATE Pokemon SET Especie=?, Objeto=?, Naturaleza=?, Habilidad=?, Genero=?, " +
                    "Movimiento1=?, Movimiento2=?, Movimiento3=?, Movimiento4=?, " +
                    "IvVida=?, IvAtaque=?, IvAtaqueEspecial=?, IvDefensa=?, IvDefensaEspecial=?, IvVelocidad=?, " +
                    "EvVida=?, EvAtaque=?, EvAtaqueEspecial=?, EvDefensa=?, EvDefensaEspecial=?, EvVelocidad=?, " +
                    "Descripcion=? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getEspecie());
            stmt.setString(2, p.getObjeto());
            stmt.setString(3, p.getNaturaleza());
            stmt.setString(4, p.getHabilidad());
            stmt.setString(5, p.getGenero());
            stmt.setString(6, p.getMovimiento1());
            stmt.setString(7, p.getMovimiento2());
            stmt.setString(8, p.getMovimiento3());
            stmt.setString(9, p.getMovimiento4());
            stmt.setInt(10, p.getIvVida());
            stmt.setInt(11, p.getIvAtaque());
            stmt.setInt(12, p.getIvAtaqueEspecial());
            stmt.setInt(13, p.getIvDefensa());
            stmt.setInt(14, p.getIvDefensaEspecial());
            stmt.setInt(15, p.getIvVelocidad());
            stmt.setInt(16, p.getEvVida());
            stmt.setInt(17, p.getEvAtaque());
            stmt.setInt(18, p.getEvAtaqueEspecial());
            stmt.setInt(19, p.getEvDefensa());
            stmt.setInt(20, p.getEvDefensaEspecial());
            stmt.setInt(21, p.getEvVelocidad());
            stmt.setString(22, p.getDescripcion());
            stmt.setInt(23, p.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPokemon(int id) {
        try {
            String sql = "DELETE FROM Pokemon WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
