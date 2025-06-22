package gainzpad.web;

import gainzpad.model.dto.UserRegisterDTO;
import gainzpad.repository.UserRepository;
import gainzpad.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("userRegisterDTO",new UserRegisterDTO());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO,
                               Model model){

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            model.addAttribute("passwordError", "Passwords do not match!");
            return "register";
        }
        boolean success = userService.registerUser(userRegisterDTO);
        if (!success){
            model.addAttribute("emailError","Email is already in use!");
            return "register";
        }
        return "redirect:/login?registered";
    }
}
