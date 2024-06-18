package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    public IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioARetornar = usuarioService.registrar(usuario);
        if(usuarioARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioARetornar);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){
        return ResponseEntity.ok(usuarioService.traerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscarPorId(id);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario){
        if (usuarioService.buscarPorId(usuario.getId()) == null) {
            return new ResponseEntity<>("{\"message\": \"usuario no encontrado\"}", HttpStatus.NOT_FOUND);
        } else {
            usuarioService.actualizar(usuario);
            return ResponseEntity.ok("{\"message\": \"usuario modificado\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id){
        if (usuarioService.buscarPorId(id) == null) {
            return new ResponseEntity<>("{\"message\": \"usuario no encontrado\"}", HttpStatus.NOT_FOUND);
        } else {
            usuarioService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"usuario eliminado\"}");
        }
    }
}
