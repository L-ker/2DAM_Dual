package DAO;

import java.util.List;

import Modelo.Tipo;

public interface TipoDAO {
    void agregarTipo(Tipo t);
    Tipo obtenerTipo(String tipo);
    List<Tipo> obtenerTodosTipos();
    void eliminarTipo(String tipo);
}
