package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;

import java.util.List;

public interface IMascotaService {
    Mascota registrar(Mascota mascota);
    Mascota buscarPorId(Integer id);
    Mascota buscarPorCampo(String campo);
    List<Mascota> traerTodos();
    void actualizar(Mascota mascota);
    void eliminar(Integer id);
}
