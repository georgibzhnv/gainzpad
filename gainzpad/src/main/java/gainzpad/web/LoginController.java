package gainzpad.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET}
        )
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
