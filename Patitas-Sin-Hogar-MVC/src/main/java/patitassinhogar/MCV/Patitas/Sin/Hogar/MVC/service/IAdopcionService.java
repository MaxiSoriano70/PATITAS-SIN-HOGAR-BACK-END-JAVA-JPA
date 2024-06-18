package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Adopcion;

import java.util.List;

public interface IAdopcionService {
    Adopcion registrar(Adopcion adopcion);
    Adopcion buscarPorId(Integer id);
    Adopcion buscarPorCampo(String campo);
    List<Adopcion> traerTodos();
    void actualizar(Adopcion adopcion);
    void eliminar(Integer id);
}
