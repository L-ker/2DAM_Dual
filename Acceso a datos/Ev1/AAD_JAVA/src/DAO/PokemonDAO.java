package DAO;

import java.util.List;

import Modelo.Pokemon;

import java.util.List;

public interface PokemonDAO {
    void agregarPokemon(Pokemon p);
    Pokemon obtenerPokemon(int id);
    List<Pokemon> obtenerTodosPokemon();
    void actualizarPokemon(Pokemon p);
    void eliminarPokemon(int id);
}
