package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model;

import java.time.LocalDate;

public class Adopcion {
    private Integer id;
    private Usuario adoptante;
    private Mascota mascota;
    private LocalDate fechaDeAdopcion;

    public Adopcion() {
    }
    public Adopcion(Integer id, Usuario adoptante, Mascota mascota, LocalDate fechaDeAdopcion) {
        this.id = id;
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fechaDeAdopcion = fechaDeAdopcion;
    }
    public Adopcion(Usuario adoptante, Mascota mascota, LocalDate fechaDeAdopcion) {
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fechaDeAdopcion = fechaDeAdopcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Usuario adoptante) {
        this.adoptante = adoptante;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public LocalDate getFechaDeAdopcion() {
        return fechaDeAdopcion;
    }

    public void setFechaDeAdopcion(LocalDate fechaDeAdopcion) {
        this.fechaDeAdopcion = fechaDeAdopcion;
    }

    @Override
    public String toString() {
        return "Adopcion" +
                "id=" + id +
                ", adoptante=" + adoptante +
                ", mascota=" + mascota +
                ", fechaDeAdopcion=" + fechaDeAdopcion;
    }
}
