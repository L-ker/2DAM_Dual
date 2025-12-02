package DAO;

import java.util.List;

import Modelo.Usuario;

public interface UsuarioDAO {
    void agregarUsuario(Usuario u);
    Usuario obtenerUsuario(int id);
    List<Usuario> obtenerTodosUsuarios();
    void eliminarUsuario(int id);
}
