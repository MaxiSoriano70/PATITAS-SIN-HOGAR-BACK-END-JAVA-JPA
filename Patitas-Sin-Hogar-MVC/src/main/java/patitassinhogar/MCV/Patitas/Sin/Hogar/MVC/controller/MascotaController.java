package patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.model.Mascota;
import patitassinhogar.MCV.Patitas.Sin.Hogar.MVC.service.IMascotaService;

import java.util.List;

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
        Mascota mascota = mascotaService.buscarPorId(id);
        if(mascota != null){
            return ResponseEntity.ok(mascota);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarMascota(@RequestBody Mascota mascota){
        if (mascotaService.buscarPorId(mascota.getId()) == null) {
            return new ResponseEntity<>("{\"message\": \"mascota no encontrada\"}", HttpStatus.NOT_FOUND);
        } else {
            mascotaService.actualizar(mascota);
            return ResponseEntity.ok("{\"message\": \"mascota modificada\"}");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarMascota(@PathVariable Integer id){
        if (mascotaService.buscarPorId(id) == null) {
            return new ResponseEntity<>("{\"message\": \"mascota no encontrada\"}", HttpStatus.NOT_FOUND);
        } else {
            mascotaService.eliminar(id);
            return ResponseEntity.ok("{\"message\": \"mascota eliminada\"}");
        }
    }
}
