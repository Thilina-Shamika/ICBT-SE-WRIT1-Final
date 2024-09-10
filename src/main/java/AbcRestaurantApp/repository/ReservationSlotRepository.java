package AbcRestaurantApp.repository;

import AbcRestaurantApp.entity.ReservationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationSlotRepository extends JpaRepository<ReservationSlot, Long> {
    List<ReservationSlot> findByDate(LocalDate date);
}