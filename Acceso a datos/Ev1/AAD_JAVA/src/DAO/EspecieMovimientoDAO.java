package DAO;

import java.util.List;

import Modelo.EspecieMovimiento;

public interface EspecieMovimientoDAO {
    void agregarMovimiento(EspecieMovimiento m);
    EspecieMovimiento obtenerMovimiento(String movimiento, String especie);
    List<EspecieMovimiento> obtenerTodosMovimientos();
    void eliminarMovimiento(String movimiento, String especie);
}
