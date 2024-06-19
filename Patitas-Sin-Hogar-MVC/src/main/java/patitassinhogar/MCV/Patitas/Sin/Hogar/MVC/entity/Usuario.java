package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate fechaDeNacimiento;
    private String contrasenia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Domicilio domicilio;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    //@JsonIgnore
    private Set<Mascota> mascotaSet = new HashSet<>();
}
