package mk.ukim.finki.wp.ewarehouse.web;

import mk.ukim.finki.wp.ewarehouse.model.User;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.ewarehouse.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.ewarehouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try {
            user = this.userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } catch (InvalidUsernameOrPasswordException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String repeatedPassword, @RequestParam String role) {
        try {
            this.userService.register(username, password, repeatedPassword, role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidUsernameOrPasswordException |
                UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
