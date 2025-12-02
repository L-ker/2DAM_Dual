package DAO;

import java.util.List;

import Modelo.Genero;

public interface GeneroDAO {
    void agregarGenero(Genero g);
    Genero obtenerGenero(String genero, String especie);
    List<Genero> obtenerTodosGeneros();
    void eliminarGenero(String genero, String especie);
}
