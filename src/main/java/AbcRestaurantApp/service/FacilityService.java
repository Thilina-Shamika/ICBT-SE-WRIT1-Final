package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Facility;
import AbcRestaurantApp.entity.Inquiry;
import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.repository.FacilityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import jakarta.persistence.spi.LoadState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public Facility postFacility(Facility facility){
        return facilityRepository.save(facility);
    }

    public List<Facility> getAllFacility(){
        return facilityRepository.findAll();
    }

    public void deleteFacility(Long id){
        if (!facilityRepository.existsById(id)){
            throw new EntityNotFoundException("Facility with id: " + id + "not found");
        }
        facilityRepository.deleteById(id);
    }

    public Facility getFacilityById(Long id){
        return facilityRepository.findById(id).orElse(null);
    }

    public Facility updateFacility(Long id, Facility facility){
        Optional<Facility> optionalFacility= facilityRepository.findById(id);

        if (optionalFacility.isPresent()){
            Facility existingFacility = optionalFacility.get();
            existingFacility.setName(facility.getName());
            existingFacility.setDescription(facility.getDescription());

            return facilityRepository.save(existingFacility);
        }
        return null;
    }

}
