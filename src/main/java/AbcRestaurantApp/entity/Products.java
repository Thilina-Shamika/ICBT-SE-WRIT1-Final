package AbcRestaurantApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prodName;
    private String description;
    private Integer price;
    private String imagePath;


}
