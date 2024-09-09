package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.ReservationSlot;
import AbcRestaurantApp.service.ReservationSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservation-slots")
@CrossOrigin("*")
public class ReservationSlotController {

    @Autowired
    private ReservationSlotService reservationSlotService;

    @PostMapping
    public ResponseEntity<ReservationSlot> createReservationSlot(@RequestBody ReservationSlot reservationSlot) {
        return ResponseEntity.ok(reservationSlotService.createReservationSlot(reservationSlot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationSlot> updateReservationSlot(@PathVariable Long id, @RequestBody ReservationSlot reservationSlot) {
        return ResponseEntity.ok(reservationSlotService.updateReservationSlot(id, reservationSlot));
    }

    @GetMapping
    public ResponseEntity<List<ReservationSlot>> getAllReservationSlots() {
        return ResponseEntity.ok(reservationSlotService.getAllReservationSlots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationSlot> getReservationSlotById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationSlotService.getReservationSlotById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationSlot(@PathVariable Long id) {
        reservationSlotService.deleteReservationSlot(id);
        return ResponseEntity.noContent().build();
    }
}