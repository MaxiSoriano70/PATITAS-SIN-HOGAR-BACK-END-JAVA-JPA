package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion.MascotaIDaoH2;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.dao.implementacion.UsuarioIDaoH2;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Domicilio;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion.MascotaService;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.implementacion.UsuarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MascotaServiceTest {
    public static Logger LOGGER = LoggerFactory.getLogger(MascotaServiceTest.class);

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/patitassinhogarV1;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
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
    }

    @Test
    @DisplayName("Testear que un Usuario persista en la BD")
    void testearUsuarioEnDB(){
        Domicilio domicilio =  new Domicilio("Calle 5", 1150, "Capital", "Salta");
        Usuario usuario = new Usuario("karen@gmail.com","Karen","Dominguez","3874852369", LocalDate.of(1995, 6, 5), "Karen123", domicilio);
        UsuarioService usuarioService = new UsuarioService(new UsuarioIDaoH2());
        Usuario usuarioPersistido = usuarioService.registrar(usuario);
        assertNotNull(usuarioPersistido);
    }

    @Test
    @DisplayName("Testear que un Usuario buscado por email")
    void testearUsuarioPorEmail(){
        UsuarioService usuarioService = new UsuarioService(new UsuarioIDaoH2());
        String email = "emilia@gmail.com";
        Usuario usuarioEncontrada = usuarioService.buscarPorCampo(email);
        assertEquals("emilia@gmail.com", usuarioEncontrada.getEmail());
    }


    @Test
    @DisplayName("Testear que una mascota pasandole el nombre")
    void testearMascotaPorNombre(){
        MascotaService mascotaService = new MascotaService(new MascotaIDaoH2());
        String nombre = "MARGO";
        Mascota mascotaEncontrada = mascotaService.buscarPorCampo(nombre);

        assertEquals("MARGO", mascotaEncontrada.getNombre());
    }

    @Test
    @DisplayName("Testear que una mascota pasandole el nombre")
    void testearMascotaPorNombreNoEncontrado(){
        MascotaService mascotaService = new MascotaService(new MascotaIDaoH2());
        String nombre = "zeus";
        Mascota mascotaEncontrada = mascotaService.buscarPorCampo(nombre);

        assertNull(mascotaEncontrada);
    }
}