package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioService {
    Usuario registrar(Usuario usuario);
    Usuario buscarPorId(Integer id);
    Usuario buscarPorCampo(String campo);
    List<Usuario> traerTodos();
    void actualizar(Usuario usuario);
    void eliminar(Integer id);
}
