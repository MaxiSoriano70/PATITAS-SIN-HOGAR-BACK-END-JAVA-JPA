package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.IDao;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.db.H2Connection;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Domicilio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DomicilioIDaoH2 implements IDao<Domicilio> {
    private static Logger LOGGER = LoggerFactory.getLogger(DomicilioIDaoH2.class);
    private static String SQL_INSERT = "INSERT INTO DOMICILIOS VALUES (DEFAULT, ?, ?, ?, ?);";
    private static String SQL_SELECT_ID = "SELECT * FROM DOMICILIOS WHERE idDomicilio=?;";
    private static String SQL_SELECT_ALL = "SELECT * FROM DOMICILIOS;";
    private static String SQL_UPDATE = "UPDATE DOMICILIOS SET calle=?, " +
            "numero=?, " +
            "localidad=?, " +
            "provincia=? WHERE idDomicilio=?;";
    private static String SQL_DELETE = "DELETE FROM DOMICILIOS WHERE idDomicilio = ?;";

    @Override
    public Domicilio registrar(Domicilio domicilio) {
        Connection connection = null;
        Domicilio domicilioPersistido = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                domicilioPersistido = new Domicilio(id, domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(), domicilio.getProvincia());
                LOGGER.info("Domicilio persistido: "+ domicilioPersistido);
            }

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
        return domicilioPersistido;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        Connection connection = null;
        Domicilio domicilioEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                domicilioEncontrado = new Domicilio(resultSet.getInt(1),resultSet.getNString(2), resultSet.getInt(3), resultSet.getNString(4), resultSet.getNString(5));
            }
            LOGGER.info("Domicilio encontrado: "+domicilioEncontrado);

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
        return domicilioEncontrado;
    }

    @Override
    public Domicilio buscarPorCampo(String campo) {
        return null;
    }

    @Override
    public List<Domicilio> traerTodos() {
        Connection connection = null;
        List<Domicilio> domicilios = new ArrayList<>();
        Domicilio domicilioObtenido = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                domicilioObtenido = crearDomicilio(resultSet);
                domicilios.add(domicilioObtenido);
                LOGGER.info("Domicilio Obtenido: "+domicilioObtenido);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return domicilios;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        Connection connection = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setInt(5, domicilio.getId());
            preparedStatement.executeUpdate();

            LOGGER.info("Domicilio Actualizado");
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
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Domicilio eliminado ");
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

    private Domicilio crearDomicilio(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt(1);
        String calle = resultSet.getString(2);
        int numero = resultSet.getInt(3);
        String localidad = resultSet.getString(4);
        String provincia = resultSet.getString(5);
        Domicilio domicilio = new Domicilio(id, calle, numero, localidad, provincia);
        return domicilio;
    }
}
