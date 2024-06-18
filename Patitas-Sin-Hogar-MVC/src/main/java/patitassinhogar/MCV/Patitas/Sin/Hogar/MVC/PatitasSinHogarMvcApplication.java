package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.db.H2Connection;

@SpringBootApplication
public class PatitasSinHogarMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(PatitasSinHogarMvcApplication.class, args);
		H2Connection.crearTablas();
	}
}
