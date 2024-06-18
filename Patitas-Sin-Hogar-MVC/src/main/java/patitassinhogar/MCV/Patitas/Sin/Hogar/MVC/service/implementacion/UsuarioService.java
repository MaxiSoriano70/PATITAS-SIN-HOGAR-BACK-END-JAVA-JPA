package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;

import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IUsuarioService;


import java.util.List;
@Service
public class UsuarioService implements IUsuarioService {
    private IDao<Usuario> usuarioIDao;

    public UsuarioService(IDao<Usuario> usuarioIDao) {
        this.usuarioIDao = usuarioIDao;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        return this.usuarioIDao.registrar(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return this.usuarioIDao.buscarPorId(id);
    }

    @Override
    public Usuario buscarPorCampo(String campo) {
        return this.usuarioIDao.buscarPorCampo(campo);
    }

    @Override
    public List<Usuario> traerTodos() {
        return this.usuarioIDao.traerTodos();
    }

    @Override
    public void actualizar(Usuario usuario){
        this.usuarioIDao.actualizar(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        this.usuarioIDao.eliminar(id);
    }
}
