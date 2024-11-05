package bg.soft_uni.mobilelelele.web;

import bg.soft_uni.mobilelelele.models.dtos.AddOfferDto;
import bg.soft_uni.mobilelelele.services.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddOfferController {
    private final OfferService offerService;

    public AddOfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/add")
    public String newOffer(Model model){
        if(!model.containsAttribute("addOfferDto")) {
            model.addAttribute(new AddOfferDto());
        }
        return "offer-add";
    }
    @PostMapping("/offers/add")
    public String addOffer(@Valid AddOfferDto addOfferDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDto", bindingResult);
            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto);
            return "redirect:/offers/add";
        }
        offerService.addOffer(addOfferDto);
        return "redirect:/";
    }
}
