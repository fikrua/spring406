package springboot_401.demospringboot_401;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller

public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String showRegistration(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user, BindingResult result,Model model){
        model.addAttribute("user",user);
        if(result.hasErrors()){
            return "register";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message","User Account Created");
        }
        return "index";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/secure")
    public String adminpage(Principal principal, Model model){
        User myuser=((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("myuser",myuser);
        return "secure";
    }
}
