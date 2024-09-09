package AbcRestaurantApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phoneNumber;
    private int partySize;
    private LocalDateTime reservationTime;
    private LocalDateTime expirationTime;
    private boolean arrived;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "reservation_slot_id")
    private ReservationSlot reservationSlot;
}