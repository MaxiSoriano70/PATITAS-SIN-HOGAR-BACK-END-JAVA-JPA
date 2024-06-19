package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion;

import org.springframework.stereotype.Service;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.repository.IUsuarioRepository;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IUsuarioService;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    private IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Usuario> traerTodos() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public void actualizar(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        this.usuarioRepository.deleteById(id);
    }
}
