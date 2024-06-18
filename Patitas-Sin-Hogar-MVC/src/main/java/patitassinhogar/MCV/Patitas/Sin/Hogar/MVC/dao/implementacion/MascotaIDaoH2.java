package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.db.H2Connection;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class MascotaIDaoH2 implements IDao<Mascota> {
    public static Logger LOGGER = LoggerFactory.getLogger(MascotaIDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO MASCOTAS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static String SQL_SELECT_ID = "SELECT * FROM MASCOTAS WHERE idMascota=?;";
    private static String SQL_SELECT_NOMBRE = "SELECT * FROM MASCOTAS WHERE nombre=?;";
    private static String SQL_SELECT_ALL = "SELECT * FROM MASCOTAS;";
    private static String SQL_UPDATE = "UPDATE MASCOTAS SET nombre=?, " +
            "urlFoto=?," +
            "fechaDeNacimiento=?, " +
            "especie=?, " +
            "colorDePelo=?, " +
            "pesoKg=?, " +
            "isEsterilizado=?, " +
            "observaciones=?, " +
            "isAdoptado=?, " +
            "idUsuario=? WHERE idMascota = ?";
    private static String SQL_DELETE = "DELETE FROM MASCOTAS WHERE idMascota = ?;";

    @Override
    public Mascota registrar(Mascota mascota) {
        Connection connection = null;
        Mascota mascotaPersistida = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        try{
            /*CONEXION*/
            connection = H2Connection.getConnection();
            /*DESACTIVAMOS EL AUTOCOMMIT*/
            connection.setAutoCommit(false);
            Usuario usuario = usuarioDaoH2.buscarPorId(mascota.getUsuario().getId());
            /*PASAMOS LA CUNSULTA Y SUS PARAMETROS PARA REGISTRAR*/
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, mascota.getNombre());
            preparedStatement.setString(2, mascota.getUrl());
            preparedStatement.setDate(3, Date.valueOf(mascota.getFechaDeNacimiento()));
            preparedStatement.setString(4, mascota.getEspecie());
            preparedStatement.setString(5, mascota.getColorDePelo());
            preparedStatement.setDouble(6, mascota.getPesoKg());
            preparedStatement.setBoolean(7, mascota.getEsterilizado());
            preparedStatement.setString(8, mascota.getObservaciones());
            preparedStatement.setBoolean(9, mascota.getAdoptado());
            preparedStatement.setInt(10, usuario.getId());

            /*EJECUTAMOS LA CONSULTA*/
            preparedStatement.executeUpdate();

            /*VERIFICAMOS SI SE HIZO EL INSERT*/
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                mascotaPersistida = new Mascota(id,
                        mascota.getNombre(),
                        mascota.getUrl(),
                        mascota.getFechaDeNacimiento(),
                        mascota.getEspecie(),
                        mascota.getColorDePelo(),
                        mascota.getPesoKg(),
                        mascota.getEsterilizado(),
                        mascota.getObservaciones(),
                        mascota.getAdoptado(),
                        usuario
                );
            }

            LOGGER.info("Mascotas persistida o registrada = "+ mascotaPersistida);

            /*EJECUTA EL COMMIT*/
            connection.commit();
            /*ACTIVAR COMMIT*/
            connection.setAutoCommit(true);
        }catch (Exception e){
            if(connection!=null){
                try{
                    connection.rollback();
                }catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return mascotaPersistida;
    }

    @Override
    public Mascota buscarPorId(Integer id) {
        Connection connection = null;
        Mascota mascotaEncontrada = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                mascotaEncontrada = crearMascota(resultSet , usuarioDaoH2);
            }
            LOGGER.info("Mascota encontrada: "+ mascotaEncontrada);

        }catch (Exception e){

            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return mascotaEncontrada;
    }

    @Override
    public Mascota buscarPorCampo(String campo) {
        Connection connection = null;
        Mascota mascotaEncontrada = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_NOMBRE);
            preparedStatement.setString(1, campo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                mascotaEncontrada = crearMascota(resultSet , usuarioDaoH2);
            }
            LOGGER.info("Mascota encontrada: "+ mascotaEncontrada);

        }catch (Exception e){

            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return mascotaEncontrada;
    }

    @Override
    public List<Mascota> traerTodos() {
        Connection connection = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        List<Mascota> mascotas = new ArrayList<>();
        Mascota mascotaObtenida = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                mascotaObtenida = crearMascota(resultSet, usuarioDaoH2);
                mascotas.add(mascotaObtenida);
                LOGGER.info("Mascota Obtenida: "+mascotaObtenida);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return mascotas;
    }

    @Override
    public void actualizar(Mascota mascota) {
        Connection connection = null;
        Mascota mascotaPersistida = null;
        UsuarioIDaoH2 usuarioDaoH2 = new UsuarioIDaoH2();
        try{
            /*CONEXION*/
            connection = H2Connection.getConnection();
            /*DESACTIVAMOS EL AUTOCOMMIT*/
            connection.setAutoCommit(false);
            Usuario usuario = usuarioDaoH2.buscarPorCampo(mascota.getUsuario().getEmail());
            /*PASAMOS LA CUNSULTA Y SUS PARAMETROS PARA REGISTRAR*/
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, mascota.getNombre());
            preparedStatement.setString(2, mascota.getUrl());
            preparedStatement.setDate(3, Date.valueOf(mascota.getFechaDeNacimiento()));
            preparedStatement.setString(4, mascota.getEspecie());
            preparedStatement.setString(5, mascota.getColorDePelo());
            preparedStatement.setDouble(6, mascota.getPesoKg());
            preparedStatement.setBoolean(7, mascota.getEsterilizado());
            preparedStatement.setString(8, mascota.getObservaciones());
            preparedStatement.setBoolean(9, mascota.getAdoptado());
            preparedStatement.setInt(10, usuario.getId());
            preparedStatement.setInt(11, mascota.getId());

            /*EJECUTAMOS LA CONSULTA*/
            preparedStatement.executeUpdate();


            LOGGER.info("Mascotas Actualizada");

            /*EJECUTA EL COMMIT*/
            connection.commit();
            /*ACTIVAR COMMIT*/
            connection.setAutoCommit(true);
        }catch (Exception e){
            if(connection!=null){
                try{
                    connection.rollback();
                }catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
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
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Mascota eliminado ");
            connection.commit();
            connection.setAutoCommit(true);
        }catch (Exception e){
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Mascota crearMascota(ResultSet resultSet, UsuarioIDaoH2 usuarioDaoH2) throws SQLException{
        int id = resultSet.getInt(1);
        String nombre = resultSet.getString(2);
        String url = resultSet.getString(3);
        LocalDate fechaDeNacimiento =  resultSet.getDate(4).toLocalDate();
        String especie = resultSet.getString(5);
        String colorDePelo = resultSet.getString(6);
        Double pesoKg = resultSet.getDouble(7);
        Boolean isEsterilizado = resultSet.getBoolean(8);
        String observaciones = resultSet.getString(9);
        Boolean isAdoptado = resultSet.getBoolean(10);
        int idUsuario = resultSet.getInt(11);
        Usuario usuarioEncontrado = usuarioDaoH2.buscarPorId(idUsuario);

        Mascota mascota = new Mascota(
                id,
                nombre,
                url,
                fechaDeNacimiento,
                especie,
                colorDePelo,
                pesoKg,
                isEsterilizado,
                observaciones,
                isAdoptado,
                usuarioEncontrado
                );

        return mascota;
    }
}
