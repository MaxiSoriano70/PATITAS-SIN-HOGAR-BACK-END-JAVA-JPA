package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao <T>{
    T registrar(T t);
    T buscarPorId(Integer id);
    T buscarPorCampo(String campo);
    List<T> traerTodos();
    void actualizar(T t);
    void eliminar(Integer id);
}
