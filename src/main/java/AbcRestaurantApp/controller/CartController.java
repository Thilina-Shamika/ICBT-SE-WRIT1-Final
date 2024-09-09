package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.Cart;
import AbcRestaurantApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCart(id));
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Long id, @RequestParam Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.addToCart(id, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long id, @RequestParam Long productId) {
        Cart updatedCart = cartService.removeFromCart(id, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long id) {
        cartService.clearCart(id);
        return ResponseEntity.ok().build();
    }
}