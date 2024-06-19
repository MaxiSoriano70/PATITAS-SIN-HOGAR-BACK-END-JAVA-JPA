package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Adopcion;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IAdopcionService;

import java.util.List;
import java.util.Optional;

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
        Optional<Adopcion> adopcion = adopcionService.buscarPorId(id);
        if(adopcion.isPresent()){
            Adopcion adopcionARetornar = adopcion.get();
            return ResponseEntity.ok(adopcionARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarAdopcion(@RequestBody Adopcion adopcion){
        Optional<Adopcion> adopcionOptional = adopcionService.buscarPorId(adopcion.getId());
        if (adopcionOptional.isPresent()) {
            adopcionService.actualizar(adopcion);
            return ResponseEntity.ok("{\"message\": \"adopcion modificada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"adopcion no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarAdopcion(@PathVariable Integer id){
        Optional<Adopcion> adopcionOptional = adopcionService.buscarPorId(id);
        if (adopcionOptional.isPresent()) {
            adopcionService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"adopcion eliminada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"adopcion no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
}
