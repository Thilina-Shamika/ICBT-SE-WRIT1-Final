package AbcRestaurantApp.controller;


import AbcRestaurantApp.entity.Reservation;
import AbcRestaurantApp.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.LongSummaryStatistics;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation")
    private Reservation postReservation(@RequestBody Reservation reservation){
        return reservationService.postReservation(reservation);
    }

    @GetMapping("/viewreservation")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id){
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>("User with id: " +id+ "successfully deleted", HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
