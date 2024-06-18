package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model;

import java.time.LocalDate;

public class Usuario {
    private Integer id;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate fechaDeNacimiento;
    private String contrasenia;
    private Domicilio domicilio;

    public Usuario(){
    }

    public Usuario(Integer id, String email, String nombre, String apellido, String telefono, LocalDate fechaDeNacimiento, String contrasenia, Domicilio domicilio) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.contrasenia = contrasenia;
        this.domicilio = domicilio;
    }

    public Usuario(String email, String nombre, String apellido, String telefono, LocalDate fechaDeNacimiento, String contrasenia, Domicilio domicilio) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.contrasenia = contrasenia;
        this.domicilio = domicilio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", contrasenia='" + contrasenia + '\'' +
                ", domicilio=" + domicilio;
    }
}
