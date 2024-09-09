package AbcRestaurantApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}