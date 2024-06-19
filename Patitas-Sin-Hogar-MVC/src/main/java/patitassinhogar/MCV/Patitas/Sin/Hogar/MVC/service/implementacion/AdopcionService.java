package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;

import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Adopcion;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.repository.IAdopcionRepository;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IAdopcionService;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionService implements IAdopcionService {
    private IAdopcionRepository adopcionRepository;

    public AdopcionService(IAdopcionRepository adopcionRepository) {
        this.adopcionRepository = adopcionRepository;
    }

    @Override
    public Adopcion registrar(Adopcion adopcion) {
        return this.adopcionRepository.save(adopcion);
    }

    @Override
    public Optional<Adopcion> buscarPorId(Integer id) {
        return this.adopcionRepository.findById(id);
    }

    @Override
    public Optional<Adopcion> buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Adopcion> traerTodos() {
        return this.adopcionRepository.findAll();
    }

    @Override
    public void actualizar(Adopcion adopcion) {
        this.adopcionRepository.save(adopcion);
    }

    @Override
    public void eliminar(Integer id) {
        this.adopcionRepository.deleteById(id);
    }
}
