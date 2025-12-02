package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.EspecieMovimiento;

public class EspecieMovimientoDAOImpl implements EspecieMovimientoDAO {
    private Connection connection;

    public EspecieMovimientoDAOImpl(Connection connection) { this.connection = connection; }

    @Override
    public void agregarMovimiento(EspecieMovimiento m) {
        try {
            String sql = "INSERT INTO EspecieMovimiento (Especie, Tipo, Forma, Potencia, Movimiento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, m.getEspecie());
            stmt.setString(2, m.getTipo());
            stmt.setString(3, m.getForma());
            stmt.setInt(4, m.getPotencia());
            stmt.setString(5, m.getMovimiento());
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }

    @Override
    public EspecieMovimiento obtenerMovimiento(String movimiento, String especie) {
        EspecieMovimiento m = null;
        try {
            String sql = "SELECT * FROM EspecieMovimiento WHERE Movimiento=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, movimiento);
            stmt.setString(2, especie);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                m = new EspecieMovimiento(
                        rs.getString("Especie"),
                        rs.getString("Tipo"),
                        rs.getString("Forma"),
                        rs.getInt("Potencia"),
                        rs.getString("Movimiento")
                );
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return m;
    }

    @Override
    public List<EspecieMovimiento> obtenerTodosMovimientos() {
        List<EspecieMovimiento> lista = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EspecieMovimiento");
            while(rs.next()) {
                lista.add(new EspecieMovimiento(
                        rs.getString("Especie"),
                        rs.getString("Tipo"),
                        rs.getString("Forma"),
                        rs.getInt("Potencia"),
                        rs.getString("Movimiento")
                ));
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public void eliminarMovimiento(String movimiento, String especie) {
        try {
            String sql = "DELETE FROM EspecieMovimiento WHERE Movimiento=? AND Especie=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, movimiento);
            stmt.setString(2, especie);
            stmt.executeUpdate();
        } catch(SQLException e) { e.printStackTrace(); }
    }
}
