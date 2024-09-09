package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.ReservationSlot;
import AbcRestaurantApp.repository.ReservationSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationSlotService {

    @Autowired
    private ReservationSlotRepository reservationSlotRepository;

    public ReservationSlot createReservationSlot(ReservationSlot reservationSlot) {
        return reservationSlotRepository.save(reservationSlot);
    }

    public ReservationSlot updateReservationSlot(Long id, ReservationSlot reservationSlot) {
        ReservationSlot existingSlot = reservationSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation slot not found"));

        existingSlot.setDate(reservationSlot.getDate());  // Update date
        existingSlot.setStartTime(reservationSlot.getStartTime());
        existingSlot.setEndTime(reservationSlot.getEndTime());
        existingSlot.setCapacity(reservationSlot.getCapacity());
        existingSlot.setAvailable(reservationSlot.isAvailable());

        return reservationSlotRepository.save(existingSlot);
    }

    public List<ReservationSlot> getAllReservationSlots() {
        return reservationSlotRepository.findAll();
    }

    public ReservationSlot getReservationSlotById(Long id) {
        return reservationSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation slot not found"));
    }

    public void deleteReservationSlot(Long id) {
        reservationSlotRepository.deleteById(id);
    }
}