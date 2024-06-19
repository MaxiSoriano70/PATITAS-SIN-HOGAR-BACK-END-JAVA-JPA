package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Mascota;

public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {
}
