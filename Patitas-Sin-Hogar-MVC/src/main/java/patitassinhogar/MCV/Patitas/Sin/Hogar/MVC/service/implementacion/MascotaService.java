package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;


import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.repository.IMascotaRepository;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IMascotaService;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {
    private IMascotaRepository mascotaRepository;

    public MascotaService(IMascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Mascota registrar(Mascota mascota) {
        return this.mascotaRepository.save(mascota);
    }

    @Override
    public Optional<Mascota> buscarPorId(Integer id) {
        return this.mascotaRepository.findById(id);
    }

    @Override
    public Optional<Mascota> buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Mascota> traerTodos() {
        return this.mascotaRepository.findAll();
    }

    @Override
    public void actualizar(Mascota mascota) {
        this.mascotaRepository.save(mascota);
    }

    @Override
    public void eliminar(Integer id) {
        this.mascotaRepository.deleteById(id);
    }
}
