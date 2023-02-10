package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.UserDTO;
import fr.dawan.projet_spring_mvc.entities.User;
import fr.dawan.projet_spring_mvc.services.UserService;
import fr.dawan.projet_spring_mvc.tools.PasswordHasher;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null) return "redirect:/contact/getAll";
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        Optional<UserDTO> user = userService.authenticate(email, password);
        if(user.isPresent()) {
            session.setAttribute("user", user.get());
            session.setMaxInactiveInterval(10*60);
            return "redirect:/user/edit";
        }
        else {
            model.addAttribute("error","Identifiants incorrects");
            return "redirect:/user/login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/profil")
    public String profil(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        model.addAttribute("user",user);
        return "profile-user";
    }

    @GetMapping("/edit")
    public String edit(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        model.addAttribute("user",user);
        return "edit-profile-user";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute UserDTO editUser, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";
        if (!editUser.getPassword().equals("")) editUser.setPassword(userService.encodePassword(editUser.getPassword()));
        userService.updateUser(editUser, user);
        return "redirect:/user/profil";
    }
}
