package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Mascota;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {
    Mascota registrar(Mascota mascota);
    Optional<Mascota> buscarPorId(Integer id);
    Optional<Mascota> buscarPorCampo(String campo);
    List<Mascota> traerTodos();
    void actualizar(Mascota mascota);
    void eliminar(Integer id);
}
