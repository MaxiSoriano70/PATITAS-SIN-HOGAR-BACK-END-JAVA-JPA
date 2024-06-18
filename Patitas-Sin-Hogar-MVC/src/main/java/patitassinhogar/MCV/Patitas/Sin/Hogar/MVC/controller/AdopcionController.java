package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Adopcion;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IAdopcionService;

import java.util.List;

@RestController
@RequestMapping("/adopcion")
public class AdopcionController {
    public IAdopcionService adopcionService;
    public AdopcionController(IAdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }
    @PostMapping
    public ResponseEntity<Adopcion> registrarAdopcion(@RequestBody Adopcion adopcion){
        Adopcion adopcionARetornar = adopcionService.registrar(adopcion);
        if(adopcionARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(adopcionARetornar);
        }
    }
    @GetMapping
    public ResponseEntity<List<Adopcion>> buscarTodos(){
        return ResponseEntity.ok(adopcionService.traerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> buscarAdopcionPorId(@PathVariable Integer id){
        Adopcion adopcion = adopcionService.buscarPorId(id);
        if(adopcion != null){
            return ResponseEntity.ok(adopcion);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarAdopcion(@RequestBody Adopcion adopcion){
        if (adopcionService.buscarPorId(adopcion.getId()) == null) {
            return new ResponseEntity<>("{\"message\": \"adopcion no encontrada\"}", HttpStatus.NOT_FOUND);
        } else {
            adopcionService.actualizar(adopcion);
            return ResponseEntity.ok("{\"message\": \"adopcion modificada\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarAdopcion(@PathVariable Integer id){
        if (adopcionService.buscarPorId(id) == null) {
            return new ResponseEntity<>("{\"message\": \"adopcion no encontrada\"}", HttpStatus.NOT_FOUND);
        } else {
            adopcionService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"adopcion eliminada\"}");
        }
    }
}
