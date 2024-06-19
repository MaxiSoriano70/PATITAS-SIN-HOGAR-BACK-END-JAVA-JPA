package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
