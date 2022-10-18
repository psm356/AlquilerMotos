package com.mintic_usa.AlquilerMotos.Service;

import com.mintic_usa.AlquilerMotos.Model.Motorbike;
import com.mintic_usa.AlquilerMotos.Repository.MotorbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorbikeService {
    @Autowired
    private MotorbikeRepository motorbikeRepository;

    public List<Motorbike> getAll(){
        return motorbikeRepository.getAll();
    }
    public Optional<Motorbike> getMotorbike(int id){
        return motorbikeRepository.getMotorbike(id);
    }
    public Motorbike save(Motorbike motorbike){
        if(motorbike.getId() == null){
            return motorbikeRepository.save(motorbike);
        }else{
            Optional<Motorbike> aux = motorbikeRepository.getMotorbike(motorbike.getId());
            if(aux.isPresent()){
                return motorbike;
            }else{
                return motorbikeRepository.save(motorbike);
            }
        }
    }
    public Motorbike update(Motorbike motorbike){
        if(motorbike.getId() != null){
            Optional<Motorbike> b = motorbikeRepository.getMotorbike(motorbike.getId());
            if(b.isPresent()){
                if(motorbike.getName() != null){
                    b.get().setName(motorbike.getName());
                }
                if(motorbike.getBrand() != null){
                    b.get().setBrand(motorbike.getBrand());
                }
                if(motorbike.getYear() != null){
                    b.get().setYear(motorbike.getYear());
                }
                if(motorbike.getDescription() != null){
                    b.get().setDescription(motorbike.getDescription());
                }
                if(motorbike.getMessages() != null){
                    b.get().setMessages(motorbike.getMessages());
                }
                if(motorbike.getReservations() != null){
                    b.get().setReservations(motorbike.getReservations());
                }
                motorbikeRepository.save(b.get());
                return b.get();
            }else{
                return motorbike;
            }
        }else{
            return motorbike;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Motorbike> b = motorbikeRepository.getMotorbike(id);
        if(b.isPresent()){
            motorbikeRepository.delete(b.get());
        }
        return flag;
    }
}
