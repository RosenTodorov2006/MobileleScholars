package bg.soft_uni.mobilelelele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return  "index";
    }
//    @ExceptionHandler(NotFountException.class)
//    public ModelAndView notFount(NotFountException notFountException){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("message", notFountException.getMessage());
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }
}