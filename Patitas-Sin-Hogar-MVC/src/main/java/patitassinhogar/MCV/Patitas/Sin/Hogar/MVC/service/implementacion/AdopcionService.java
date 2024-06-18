package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;

import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Adopcion;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IAdopcionService;

import java.util.List;
@Service
public class AdopcionService implements IAdopcionService {
    private IDao<Adopcion> adopcionIDao;

    public AdopcionService(IDao<Adopcion> adopcionIDao) {
        this.adopcionIDao = adopcionIDao;
    }

    @Override
    public Adopcion registrar(Adopcion adopcion) {
        return this.adopcionIDao.registrar(adopcion);
    }

    @Override
    public Adopcion buscarPorId(Integer id) {
        return this.adopcionIDao.buscarPorId(id);
    }

    @Override
    public Adopcion buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Adopcion> traerTodos() {
        return this.adopcionIDao.traerTodos();
    }

    @Override
    public void actualizar(Adopcion adopcion) {
        this.adopcionIDao.actualizar(adopcion);
    }

    @Override
    public void eliminar(Integer id) {
        this.adopcionIDao.eliminar(id);
    }
}
