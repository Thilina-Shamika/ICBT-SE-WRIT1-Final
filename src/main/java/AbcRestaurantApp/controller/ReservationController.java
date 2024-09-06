package AbcRestaurantApp.controller;


import AbcRestaurantApp.entity.Reservation;
import AbcRestaurantApp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
}
