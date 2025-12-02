package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
    private Connection connection;

    public UsuarioDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarUsuario(Usuario u) {
        try {
            String sql = "INSERT INTO Usuarios (Nombre) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, u.getNombre());
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        Usuario u = null;
        try {
            String sql = "SELECT * FROM Usuarios WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) u = new Usuario(rs.getInt("id"), rs.getString("Nombre"));
        } catch(SQLException e) { e.printStackTrace(); }
        return u;
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios");
            while(rs.next()) lista.add(new Usuario(rs.getInt("id"), rs.getString("Nombre")));
        } catch(SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminarUsuario(int id) {
        try {
            String sql = "DELETE FROM Usuarios WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}
