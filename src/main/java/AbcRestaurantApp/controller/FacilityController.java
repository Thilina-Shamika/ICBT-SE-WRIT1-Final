package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.Facility;
import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.service.FacilityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/facility")
    public Facility postFacility(@RequestBody Facility facility){
        return facilityService.postFacility(facility);
    }

    @GetMapping("/viewfacility")
    public List<Facility> getAllFacility(){
        return facilityService.getAllFacility();
    }

    @DeleteMapping("/facility/{id}")
    public ResponseEntity<?> deleteFacility(@PathVariable Long id){
        try {
            facilityService.deleteFacility(id);
            return new ResponseEntity<>("Facility with id: " + id + "deleted successfully", HttpStatus.OK);

        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/facility/{id}")
    public ResponseEntity<?> getFacilityById(@PathVariable Long id){
        Facility facility = facilityService.getFacilityById(id);
        if (facility == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(facility);
    }

    @PutMapping("/facility/{id}")
    public ResponseEntity<?> updateFacility(@PathVariable Long id, @RequestBody Facility facility){
        Facility updatedFacility = facilityService.updateFacility(id, facility);

        if (updatedFacility == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedFacility);
    }

}
