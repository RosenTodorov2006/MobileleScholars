package bg.soft_uni.mobilelelele.web;

import bg.soft_uni.mobilelelele.models.dtos.OfferExportDto;
import bg.soft_uni.mobilelelele.services.ExRateService;
import bg.soft_uni.mobilelelele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OffersController {
    private final ExRateService exRateService;
    private final OfferService offerService;

    public OffersController(ExRateService exRateService, OfferService offerService) {
        this.exRateService = exRateService;
        this.offerService = offerService;
    }

    @GetMapping("/offers/all")
    public String viewOffers(Model model){
        List<OfferExportDto> offers = offerService.findAllOffers();
        model.addAttribute("offers", offers);
        model.addAttribute("currencyNames", this.exRateService.getAllRatesNames());
        return "offers";
    }
    @GetMapping("/offers/{id}")
    public String details(@PathVariable Long id, Model model){
        OfferExportDto offerExportDto = this.offerService.findOfferById(id);
        model.addAttribute("offer", offerExportDto);
        return "details";
    }

}
