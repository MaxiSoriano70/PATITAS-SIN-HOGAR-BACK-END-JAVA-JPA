package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;


import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IMascotaService;

import java.util.List;

@Service
public class MascotaService implements IMascotaService {
    private IDao<Mascota> mascotaIDao;

    public MascotaService(IDao<Mascota> mascotaIDao) {
        this.mascotaIDao = mascotaIDao;
    }

    @Override
    public Mascota registrar(Mascota mascota) {
        return this.mascotaIDao.registrar(mascota);
    }

    @Override
    public Mascota buscarPorId(Integer id) {
        return this.mascotaIDao.buscarPorId(id);
    }

    @Override
    public Mascota buscarPorCampo(String campo) {
        return this.mascotaIDao.buscarPorCampo(campo);
    }

    @Override
    public List<Mascota> traerTodos() {
        return this.mascotaIDao.traerTodos();
    }

    @Override
    public void actualizar(Mascota mascota) {
        this.mascotaIDao.actualizar(mascota);
    }

    @Override
    public void eliminar(Integer id) {
        this.mascotaIDao.eliminar(id);
    }
}
