package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model;

import java.time.LocalDate;

public class Mascota {
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
    private Usuario usuario;

    public Mascota() {
    }

    public Mascota(Integer id, String nombre, String url, LocalDate fechaDeNacimiento, String especie, String colorDePelo, Double pesoKg, Boolean isEsterilizado, String observaciones, Boolean isAdoptado, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.especie = especie;
        this.colorDePelo = colorDePelo;
        this.pesoKg = pesoKg;
        this.isEsterilizado = isEsterilizado;
        this.observaciones = observaciones;
        this.isAdoptado = isAdoptado;
        this.usuario = usuario;
    }

    public Mascota(String nombre, String url, LocalDate fechaDeNacimiento, String especie, String colorDePelo, Double pesoKg, Boolean isEsterilizado, String observaciones, Boolean isAdoptado, Usuario usuario) {
        this.nombre = nombre;
        this.url = url;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.especie = especie;
        this.colorDePelo = colorDePelo;
        this.pesoKg = pesoKg;
        this.isEsterilizado = isEsterilizado;
        this.observaciones = observaciones;
        this.isAdoptado = isAdoptado;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getColorDePelo() {
        return colorDePelo;
    }

    public void setColorDePelo(String colorDePelo) {
        this.colorDePelo = colorDePelo;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Boolean getEsterilizado() {
        return isEsterilizado;
    }

    public void setEsterilizado(Boolean esterilizado) {
        isEsterilizado = esterilizado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getAdoptado() {
        return isAdoptado;
    }

    public void setAdoptado(Boolean adoptado) {
        isAdoptado = adoptado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Mascota" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", especie='" + especie + '\'' +
                ", colorDePelo='" + colorDePelo + '\'' +
                ", pesoKg=" + pesoKg +
                ", isEsterilizado=" + isEsterilizado +
                ", observaciones='" + observaciones + '\'' +
                ", isAdoptado=" + isAdoptado +
                ", usuario=" + usuario;
    }
}
