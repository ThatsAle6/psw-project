package ecommerce.com.pswproject.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/homepage")
public class HomepageC {
    
    @GetMapping
    public ModelAndView home(){
        return new ModelAndView("Homepage.html", "null", null);
    }

    @GetMapping("/shop")
    public ModelAndView prodotti(){
        return new ModelAndView("ProductView.html", "null", null);
    }

    @GetMapping("/signUp")
    public ModelAndView registra(){
        return new ModelAndView("SignUp.hmtl", "null", null);
    }
}
