package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.entity.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IMascotaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascota")
public class MascotaController {
    public IMascotaService mascotaService;
    public MascotaController(IMascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }
    @PostMapping
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota){
        Mascota mascotaARetornar = mascotaService.registrar(mascota);
        if(mascotaARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(mascotaARetornar);
        }
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> buscarTodos(){
        return ResponseEntity.ok(mascotaService.traerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscarMascotaPorId(@PathVariable Integer id){
        Optional<Mascota> mascota = mascotaService.buscarPorId(id);
        if(mascota.isPresent()){
            Mascota mascotaARetornar = mascota.get();
            return ResponseEntity.ok(mascotaARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarMascota(@RequestBody Mascota mascota){
        Optional<Mascota> mascotaOptional = mascotaService.buscarPorId(mascota.getId());
        if (mascotaOptional.isPresent()) {
            mascotaService.actualizar(mascota);
            return ResponseEntity.ok("{\"message\": \"mascota modificada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"mascota no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMascota(@PathVariable Integer id){
        Optional<Mascota> mascotaOptional = mascotaService.buscarPorId(id);
        if (mascotaOptional.isPresent()) {
            mascotaService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"mascota eliminada\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"mascota no encontrada\"}", HttpStatus.NOT_FOUND);
        }
    }
}
