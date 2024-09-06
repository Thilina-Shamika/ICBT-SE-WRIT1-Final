package AbcRestaurantApp.controller;


import AbcRestaurantApp.entity.Inquiry;
import AbcRestaurantApp.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/inquiry")
    public Inquiry postInquiry(@RequestBody Inquiry inquiry){
        return inquiryService.postInquiry(inquiry);
    }

    @GetMapping("/allinquiries")
    public List<Inquiry> getAllInquiry(){
        return inquiryService.getAllInquiry();
    }
}
