package com.mintic_usa.AlquilerMotos.Service;

import com.mintic_usa.AlquilerMotos.Model.Reservation;
import com.mintic_usa.AlquilerMotos.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> aux = reservationRepository.getReservation(reservation.getIdReservation());
            if(aux.isPresent()){
                return reservation;
            }else{
                return reservationRepository.save(reservation);
            }
        }
    }
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() != null){
            Optional<Reservation> r = reservationRepository.getReservation(reservation.getIdReservation());
            if(r.isPresent()){
                if(reservation.getStartDate() != null){
                    r.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate() != null){
                    r.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus() != null){
                    r.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(r.get());
                return r.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Reservation> r = reservationRepository.getReservation(id);
        if(r.isPresent()){
            reservationRepository.delete(r.get());
        }
        return flag;
    }
}
