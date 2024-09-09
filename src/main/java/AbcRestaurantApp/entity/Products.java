package AbcRestaurantApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Products {

    //trying to add an image. undo to this point if it doesn't work

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prodName;
    private String description;
    private Integer price;
    private String imageName;

    @Transient
    public String getImageUrl() {
        if (imageName == null || id == null) return null;
        return "/uploads/" + imageName;
    }
}

