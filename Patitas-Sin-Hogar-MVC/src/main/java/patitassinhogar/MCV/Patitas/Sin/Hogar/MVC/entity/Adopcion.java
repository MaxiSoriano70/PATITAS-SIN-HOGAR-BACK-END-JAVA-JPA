package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "adopciones")
public class Adopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Usuario adoptante;
    private Mascota mascota;
    private LocalDate fechaDeAdopcion;
}
