package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.db.H2Connection;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Domicilio;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class UsuarioIDaoH2 implements IDao<Usuario> {
    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioIDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO USUARIOS VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
    private static String SQL_SELECT_ID = "SELECT * FROM USUARIOS WHERE idUsuario=?;";
    private static String SQL_SELECT_EMAIL = "SELECT * FROM USUARIOS WHERE email=?;";
    private static String SQL_SELECT_ALL = "SELECT * FROM USUARIOS;";
    private static String SQL_UPDATE = "UPDATE USUARIOS SET email=?, " +
            "nombre=?, " +
            "apellido=?, " +
            "telefono=?, " +
            "fechaDeNacimiento=?, " +
            "contrasenia=?, " +
            "idDomicilio=? WHERE idUsuario=?;";
    private static String SQL_DELETE = "DELETE FROM USUARIOS WHERE idUsuario = ?;";
    @Override
    public Usuario registrar(Usuario usuario) {
        Connection connection = null;
        Usuario usuarioPersistido = null;
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            Domicilio domicilioGuardado = domicilioDaoH2.registrar(usuario.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setString(4, usuario.getTelefono());
            preparedStatement.setDate(5, Date.valueOf(usuario.getFechaDeNacimiento()));
            preparedStatement.setString(6, usuario.getContrasenia());
            preparedStatement.setInt(7, domicilioGuardado.getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                usuarioPersistido = new Usuario(
                        id,usuario.getEmail(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(),
                        usuario.getFechaDeNacimiento(),
                        usuario.getContrasenia(),
                        usuario.getDomicilio()
                );
            }
            LOGGER.info("Usuario Persistido: "+ usuarioPersistido);

            connection.commit();
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

        return usuarioPersistido;
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Connection connection = null;
        Usuario usuarioEncontrado = null;
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                usuarioEncontrado = crearUsuario(resultSet, domicilioDaoH2);
            }
            LOGGER.info("Usuario encontrado: "+ usuarioEncontrado);

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
        return usuarioEncontrado;
    }

    @Override
    public Usuario buscarPorCampo(String campo) {
        Connection connection = null;
        Usuario usuarioEncontrado = null;
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_EMAIL);
            preparedStatement.setString(1, campo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                usuarioEncontrado = crearUsuario(resultSet, domicilioDaoH2);
            }

            LOGGER.info("Usuario encontrado: "+ usuarioEncontrado);

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
        return usuarioEncontrado;
    }

    @Override
    public List<Usuario> traerTodos() {
        Connection connection = null;
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioObtenido = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                usuarioObtenido = crearUsuario(resultSet, domicilioDaoH2);
                usuarios.add(usuarioObtenido);
                LOGGER.info("Usuario Obtenido: "+usuarioObtenido);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario){
        Connection connection = null;
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            domicilioDaoH2.actualizar(usuario.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setString(4, usuario.getTelefono());
            preparedStatement.setDate(5, Date.valueOf(usuario.getFechaDeNacimiento()));
            preparedStatement.setString(6, usuario.getContrasenia());
            preparedStatement.setInt(7, usuario.getDomicilio().getId());
            preparedStatement.setInt(8, usuario.getId());
            preparedStatement.executeUpdate();

            LOGGER.info("Usuario Actualizado");

            connection.commit();
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
        DomicilioIDaoH2 domicilioDaoH2 = new DomicilioIDaoH2();
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Usuario eliminado ");

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

    private Usuario crearUsuario(ResultSet resultSet, DomicilioIDaoH2 domicilioDaoH2) throws SQLException{
        int id = resultSet.getInt(1);
        String email = resultSet.getString(2);
        String nombre = resultSet.getString(3);
        String apellido = resultSet.getString(4);
        String telefono = resultSet.getString(5);
        LocalDate fechaDeNacimiento = resultSet.getDate(6).toLocalDate();
        String contrasenia = resultSet.getString(7);
        int idDomicilio = resultSet.getInt(8);
        Domicilio domicilioEncontrado = domicilioDaoH2.buscarPorId(idDomicilio);
        Usuario usuario = new Usuario(id, email, nombre, apellido, telefono, fechaDeNacimiento, contrasenia, domicilioEncontrado);
        return usuario;
    }
}
