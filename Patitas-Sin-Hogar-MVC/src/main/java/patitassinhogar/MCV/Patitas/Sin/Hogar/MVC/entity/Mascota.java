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
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String url;
    private LocalDate fechaDeNacimiento;
    private String especie;
    private String colorDePelo;
    private Double pesoKg;
    private Boolean isEsterilizado;
    private String observaciones;
    private Boolean isAdoptado;
    @ManyToOne
    private Usuario usuario;
}
