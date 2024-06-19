package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Usuario;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IUsuarioService;

import java.util.List;
import java.util.Optional;

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
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if(usuario.isPresent()){
            Usuario usuarioARetornar = usuario.get();
            return ResponseEntity.ok(usuarioARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(usuario.getId());
        if (usuarioOptional.isPresent()) {
            usuarioService.actualizar(usuario);
            return ResponseEntity.ok("{\"message\": \"usuario modificado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"usuario no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);
        if (usuarioOptional.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"usuario eliminado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"usuario no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }
}
