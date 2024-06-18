package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.db.H2Connection;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Adopcion;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class AdopcionIDaoH2 implements IDao<Adopcion> {
    private static Logger LOGGER = LoggerFactory.getLogger(AdopcionIDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO ADOPCIONES VALUES (DEFAULT, ?, ?, ?);";
    private static String SQL_SELECT_ID = "SELECT * FROM ADOPCIONES WHERE idAdopcion = ?;";
    private static String SQL_SELECT_ALL = "SELECT * FROM ADOPCIONES;";
    private static String SQL_UPDATE = "UPDATE ADOPCIONES SET idUsuario = ?, " +
            "idMascota = ?, " +
            "fechaDeAdopcion = ? " +
            "WHERE idAdopcion = ?;";
    private static String SQL_DELETE = "DELETE FROM ADOPCIONES WHERE idAdopcion = ?;";

    @Override
    public Adopcion registrar(Adopcion adopcion) {
        Connection connection = null;
        Adopcion adopcionARetornar = null;
        MascotaIDaoH2 mascotaIDaoH2 = new MascotaIDaoH2();
        UsuarioIDaoH2 usuarioIDaoH2 = new UsuarioIDaoH2();
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            Mascota mascotaAdoptada = mascotaIDaoH2.buscarPorId(adopcion.getMascota().getId());
            Usuario usuarioAdoptante = usuarioIDaoH2.buscarPorId(adopcion.getMascota().getId());


            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, adopcion.getAdoptante().getId());
            preparedStatement.setInt(2, adopcion.getMascota().getId());
            preparedStatement.setDate(3, Date.valueOf(adopcion.getFechaDeAdopcion()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                adopcionARetornar = new Adopcion(
                        id,
                        usuarioAdoptante,
                        mascotaAdoptada,
                        adopcion.getFechaDeAdopcion());
            }

            mascotaAdoptada.setAdoptado(true);
            LOGGER.info("Adopcion Persistido: " + adopcionARetornar);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return adopcionARetornar;
    }

    @Override
    public Adopcion buscarPorId(Integer id) {
        Connection connection = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        MascotaIDaoH2 mascotaIDaoH2 = new MascotaIDaoH2();
        Adopcion mascotaAdoptada = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mascotaAdoptada = crearAdopcion(resultSet, usuarioDaoH2, mascotaIDaoH2);
            }
            LOGGER.info("Adopcion Obtenida: " + mascotaAdoptada);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return mascotaAdoptada;
    }

    @Override
    public Adopcion buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Adopcion> traerTodos() {
        Connection connection = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        MascotaIDaoH2 mascotaIDaoH2 = new MascotaIDaoH2();
        List<Adopcion> mascotasAdoptadas = new ArrayList<>();
        Adopcion mascotaAdoptada = null;
        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                mascotaAdoptada = crearAdopcion(resultSet, usuarioDaoH2, mascotaIDaoH2);
                mascotasAdoptadas.add(mascotaAdoptada);
                LOGGER.info("Adopcion Obtenida: " + mascotaAdoptada);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return mascotasAdoptadas;
    }

    @Override
    public void actualizar(Adopcion adopcion) {
        Connection connection = null;
        MascotaIDaoH2 mascotaIDaoH2 = new MascotaIDaoH2();
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            Mascota mascotaAdoptada = mascotaIDaoH2.buscarPorId(adopcion.getMascota().getId());


            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, adopcion.getAdoptante().getId());
            preparedStatement.setInt(2, adopcion.getMascota().getId());
            preparedStatement.setDate(3, Date.valueOf(adopcion.getFechaDeAdopcion()));
            preparedStatement.setInt(4, adopcion.getId());
            preparedStatement.executeUpdate();

            LOGGER.info("Adopción Actualizada");

            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        MascotaIDaoH2 mascotaIDaoH2 = new MascotaIDaoH2();
        AdopcionIDaoH2 adopcionIDaoH2 = new AdopcionIDaoH2();
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            Adopcion adopcion = adopcionIDaoH2.buscarPorId(id);
            adopcion.getMascota().setAdoptado(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Adopción eliminada");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Adopcion crearAdopcion(ResultSet resultSet, UsuarioIDaoH2 usuarioDaoH2, MascotaIDaoH2 mascotaIDaoH2) throws SQLException{
        int id = resultSet.getInt(1);
        int idUsuario = resultSet.getInt(2);
        Usuario usuarioEncontrado = usuarioDaoH2.buscarPorId(idUsuario);
        int idMascota = resultSet.getInt(3);
        Mascota mascotaEncontrada = mascotaIDaoH2.buscarPorId(idMascota);
        LocalDate fechaDeAdopcion =  resultSet.getDate(4).toLocalDate();
        Adopcion adopcion = new Adopcion(id, usuarioEncontrado, mascotaEncontrada, fechaDeAdopcion);

        return adopcion;
    }
}
