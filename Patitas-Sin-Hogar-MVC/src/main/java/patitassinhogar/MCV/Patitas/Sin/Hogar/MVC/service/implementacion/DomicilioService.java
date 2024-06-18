package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;

import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Domicilio;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IDomicilioService;

import java.util.List;
@Service
public class DomicilioService implements IDomicilioService {
    private IDao<Domicilio> domicilioIDao;
    public DomicilioService(IDao<Domicilio> domicilioIDao) {
        this.domicilioIDao = domicilioIDao;
    }


    @Override
    public Domicilio registrar(Domicilio domicilio) {
        return this.domicilioIDao.registrar(domicilio);
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return this.domicilioIDao.buscarPorId(id);
    }

    @Override
    public Domicilio buscarPorCampo(String campo) {
        return this.domicilioIDao.buscarPorCampo(campo);
    }

    @Override
    public List<Domicilio> traerTodos() {
        return this.domicilioIDao.traerTodos();
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        this.domicilioIDao.actualizar(domicilio);
    }

    @Override
    public void eliminar(Integer id) {
        this.domicilioIDao.eliminar(id);
    }
}
