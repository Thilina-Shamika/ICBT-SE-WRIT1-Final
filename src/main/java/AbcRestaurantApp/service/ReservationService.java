package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Reservation;
import AbcRestaurantApp.entity.ReservationSlot;
import AbcRestaurantApp.repository.ReservationRepository;
import AbcRestaurantApp.repository.ReservationSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationSlotRepository reservationSlotRepository;

    public Reservation createReservation(Reservation reservation) {
        ReservationSlot slot = reservationSlotRepository.findById(reservation.getReservationSlot().getId())
                .orElseThrow(() -> new RuntimeException("Reservation slot not found"));

        if (!slot.isAvailable()) {
            throw new RuntimeException("Reservation slot is not available");
        }

        reservation.setExpirationTime(reservation.getReservationTime().plusMinutes(30));
        reservation.setArrived(false);
        reservation.setExpired(false);

        return reservationRepository.save(reservation);
    }

    public Reservation markAsArrived(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setArrived(true);
        return reservationRepository.save(reservation);
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void expireReservations() {
        List<Reservation> expiredReservations = reservationRepository.findByExpirationTimeBefore(LocalDateTime.now());
        for (Reservation reservation : expiredReservations) {
            if (!reservation.isArrived()) {
                reservation.setExpired(true);
                reservationRepository.save(reservation);
            }
        }
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
