package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.ReservationSlot;
import AbcRestaurantApp.repository.ReservationSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationSlotService {

    @Autowired
    private ReservationSlotRepository reservationSlotRepository;

    public List<ReservationSlot> getReservationSlotsByDate(LocalDate date) {
        return reservationSlotRepository.findByDate(date);
    }

    public ReservationSlot createReservationSlot(ReservationSlot reservationSlot) {
        return reservationSlotRepository.save(reservationSlot);
    }

    public ReservationSlot updateReservationSlot(Long id, ReservationSlot reservationSlot) {
        if (reservationSlotRepository.existsById(id)) {
            reservationSlot.setId(id);
            return reservationSlotRepository.save(reservationSlot);
        }
        return null; // Or throw an exception
    }

    public void deleteReservationSlot(Long id) {
        reservationSlotRepository.deleteById(id);
    }

    public ReservationSlot getReservationSlotById(Long id) {
        return reservationSlotRepository.findById(id).orElse(null);
    }

    public List<ReservationSlot> getAllReservationSlots() {
        return reservationSlotRepository.findAll();
    }
}
