package AbcRestaurantApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`") // Use backticks to quote the table name
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private LocalDateTime orderTime;
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id") // This will create a foreign key in the order_item table
    private List<OrderItem> items = new ArrayList<>();
}