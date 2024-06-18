package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service;

import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Domicilio;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;

import java.util.List;

public interface IDomicilioService {
    Domicilio registrar(Domicilio domicilio);
    Domicilio buscarPorId(Integer id);
    Domicilio buscarPorCampo(String campo);
    List<Domicilio> traerTodos();
    void actualizar(Domicilio domicilio);
    void eliminar(Integer id);
}
