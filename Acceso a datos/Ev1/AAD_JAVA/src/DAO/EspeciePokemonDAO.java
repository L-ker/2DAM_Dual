package DAO;

import java.util.List;

import Modelo.EspeciePokemon;

public interface EspeciePokemonDAO {
    void agregarEspecie(EspeciePokemon e);
    EspeciePokemon obtenerEspecie(String especie);
    List<EspeciePokemon> obtenerTodasEspecies();
    void actualizarEspecie(EspeciePokemon e);
    void eliminarEspecie(String especie);
}
