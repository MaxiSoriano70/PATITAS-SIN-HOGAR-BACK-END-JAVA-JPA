package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Usuario registrar(Usuario usuario);
    Optional<Usuario> buscarPorId(Integer id);
    Optional<Usuario> buscarPorCampo(String campo);
    List<Usuario> traerTodos();
    void actualizar(Usuario usuario);
    void eliminar(Integer id);
}
