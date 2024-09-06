package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Inquiry;
import AbcRestaurantApp.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public Inquiry postInquiry(Inquiry inquiry){
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> getAllInquiry(){
        return inquiryRepository.findAll();
    }
}
