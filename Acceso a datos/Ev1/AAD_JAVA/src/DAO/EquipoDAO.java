package DAO;

import java.util.List;

import Modelo.Equipo;

public interface EquipoDAO {
    void agregarEquipo(Equipo e);
    Equipo obtenerEquipo(int id);
    List<Equipo> obtenerTodosEquipos();
    void actualizarEquipo(Equipo e);
    void eliminarEquipo(int id);
}
