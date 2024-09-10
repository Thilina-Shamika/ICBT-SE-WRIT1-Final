package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.ReservationSlot;
import AbcRestaurantApp.service.ReservationSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservation-slots")
@CrossOrigin("*")
public class ReservationSlotController {

    @Autowired
    private ReservationSlotService reservationSlotService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReservationSlot>> getReservationSlotsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ReservationSlot> slots = reservationSlotService.getReservationSlotsByDate(date);
        return ResponseEntity.ok(slots);
    }

    @PostMapping
    public ResponseEntity<ReservationSlot> createReservationSlot(@RequestBody ReservationSlot reservationSlot) {
        ReservationSlot createdSlot = reservationSlotService.createReservationSlot(reservationSlot);
        return ResponseEntity.ok(createdSlot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationSlot> updateReservationSlot(@PathVariable Long id, @RequestBody ReservationSlot reservationSlot) {
        ReservationSlot updatedSlot = reservationSlotService.updateReservationSlot(id, reservationSlot);
        return updatedSlot != null ? ResponseEntity.ok(updatedSlot) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationSlot(@PathVariable Long id) {
        reservationSlotService.deleteReservationSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationSlot> getReservationSlotById(@PathVariable Long id) {
        ReservationSlot slot = reservationSlotService.getReservationSlotById(id);
        return slot != null ? ResponseEntity.ok(slot) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationSlot>> getAllReservationSlots() {
        List<ReservationSlot> slots = reservationSlotService.getAllReservationSlots();
        return ResponseEntity.ok(slots);
    }
}