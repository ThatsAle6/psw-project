package ecommerce.com.pswproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    
    @GetMapping("/homepage")
    public ModelAndView Homepage(){
        return new ModelAndView("Homepage", "null", null);
    }
}
