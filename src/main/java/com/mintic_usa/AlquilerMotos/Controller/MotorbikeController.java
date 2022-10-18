package com.mintic_usa.AlquilerMotos.Controller;

import com.mintic_usa.AlquilerMotos.Model.Motorbike;
import com.mintic_usa.AlquilerMotos.Service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/Motorbike")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MotorbikeController {
    @Autowired
    private MotorbikeService motorbikeService;

    @GetMapping("/all")
    public List<Motorbike> getMotorbikes(){
        return motorbikeService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Motorbike> getMotorbike(@PathVariable("id") int id){
        return motorbikeService.getMotorbike(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Motorbike save(@RequestBody Motorbike motorbike){
        return motorbikeService.save(motorbike);
    }
    @PutMapping("/update")
    public Motorbike update(@RequestBody Motorbike motorbike){
        return motorbikeService.update(motorbike);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id){
        return motorbikeService.delete(id);
    }
}

