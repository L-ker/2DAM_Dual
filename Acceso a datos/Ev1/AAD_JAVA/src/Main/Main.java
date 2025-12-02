package Main;

import java.sql.*;

import DAO.*;
import Modelo.*;

import java.sql.*;
import java.util.List;

import java.sql.*;
import java.util.List;

import DAO.*;
import Modelo.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pokemon_db";
        String usuario = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, password)) {

            // --- Crear DAOs ---
            TipoDAO tipoDAO = new TipoDAOImpl(conn);
            GeneroDAO generoDAO = new GeneroDAOImpl(conn);
            EspeciePokemonDAO especieDAO = new EspeciePokemonDAOImpl(conn);
            HabilidadEspecieDAO habilidadDAO = new HabilidadEspecieDAOImpl(conn);
            EspecieMovimientoDAO movimientoDAO = new EspecieMovimientoDAOImpl(conn);
            PokemonDAO pokemonDAO = new PokemonDAOImpl(conn);
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl(conn);
            EquipoDAO equipoDAO = new EquipoDAOImpl(conn);

            // --- Consultar Tipos ---
            List<Tipo> tipos = tipoDAO.obtenerTodosTipos();
            System.out.println("Tipos existentes en la base:");
            for (Tipo t : tipos) {
                System.out.println(" - " + t.getTipo());
            }

            // --- Consultar Generos ---
            List<Genero> generos = generoDAO.obtenerTodosGeneros();
            System.out.println("\nGéneros existentes:");
            for (Genero g : generos) {
                System.out.println(" - " + g.getGenero() + " de " + g.getEspecie());
            }

            // --- Consultar Especies ---
            List<EspeciePokemon> especies = especieDAO.obtenerTodasEspecies();
            System.out.println("\nEspecies Pokémon existentes:");
            for (EspeciePokemon e : especies) {
                System.out.println(" - " + e.getEspecie() + " Tipo1: " + e.getTipo1() + ", Tipo2: " + e.getTipo2());
            }

            // --- Consultar Habilidades ---
            List<HabilidadEspecie> habilidades = habilidadDAO.obtenerTodasHabilidades();
            System.out.println("\nHabilidades registradas:");
            for (HabilidadEspecie h : habilidades) {
                System.out.println(" - " + h.getHabilidad() + " de " + h.getEspecie());
            }

            // --- Consultar Movimientos ---
            List<EspecieMovimiento> movimientos = movimientoDAO.obtenerTodosMovimientos();
            System.out.println("\nMovimientos registrados:");
            for (EspecieMovimiento m : movimientos) {
                System.out.println(" - " + m.getMovimiento() + " de " + m.getEspecie() + " (" + m.getTipo() + ")");
            }

            // --- Consultar Pokémon ---
            List<Pokemon> pokes = pokemonDAO.obtenerTodosPokemon();
            System.out.println("\nPokémon en la base:");
            for (Pokemon p : pokes) {
                System.out.println(" - " + p.getEspecie() + " (" + p.getGenero() + "), Habilidad: " + p.getHabilidad());
            }

            // --- Consultar Usuarios ---
            List<Usuario> usuarios = usuarioDAO.obtenerTodosUsuarios();
            System.out.println("\nUsuarios registrados:");
            for (Usuario u : usuarios) {
                System.out.println(" - " + u.getNombre() + " (ID: " + u.getId() + ")");
            }

            // --- Consultar Equipos ---
            List<Equipo> equipos = equipoDAO.obtenerTodosEquipos();
            System.out.println("\nEquipos existentes:");
            for (Equipo e : equipos) {
                System.out.println(" - Equipo ID: " + e.getId() + " Usuario: " + e.getIdUsuario() +
                        " Pokés: [" + e.getIdPokemon1() + "," + e.getIdPokemon2() + "," +
                        e.getIdPokemon3() + "," + e.getIdPokemon4() + "," +
                        e.getIdPokemon5() + "," + e.getIdPokemon6() + "]");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
