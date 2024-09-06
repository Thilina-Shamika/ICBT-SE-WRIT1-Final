package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Reservation;
import AbcRestaurantApp.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation postReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public  void deleteReservation(Long id){
        if (!reservationRepository.existsById(id)){
            throw new EntityNotFoundException("Reservation not found with id: " + id);
        }
        reservationRepository.deleteById(id);
    }
}
