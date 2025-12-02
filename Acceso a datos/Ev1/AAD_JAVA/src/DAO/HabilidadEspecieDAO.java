package DAO;

import java.util.List;

import Modelo.HabilidadEspecie;

public interface HabilidadEspecieDAO {
    void agregarHabilidad(HabilidadEspecie h);
    HabilidadEspecie obtenerHabilidad(String habilidad, String especie);
    List<HabilidadEspecie> obtenerTodasHabilidades();
    void eliminarHabilidad(String habilidad, String especie);
}
