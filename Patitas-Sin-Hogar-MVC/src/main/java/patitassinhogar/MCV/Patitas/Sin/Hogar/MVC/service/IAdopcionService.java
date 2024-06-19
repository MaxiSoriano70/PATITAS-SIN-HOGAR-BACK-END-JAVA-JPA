package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Adopcion;

import java.util.List;
import java.util.Optional;

public interface IAdopcionService {
    Adopcion registrar(Adopcion adopcion);
    Optional<Adopcion> buscarPorId(Integer id);
    Optional<Adopcion> buscarPorCampo(String campo);
    List<Adopcion> traerTodos();
    void actualizar(Adopcion adopcion);
    void eliminar(Integer id);
}
