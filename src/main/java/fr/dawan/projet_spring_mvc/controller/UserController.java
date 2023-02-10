package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.UserDTO;
import fr.dawan.projet_spring_mvc.services.PictureService;
import fr.dawan.projet_spring_mvc.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    @GetMapping("/addUser")
    public String displayAddUserForm(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null) return "redirect:/contact/getAll";
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping(path="/addUser")
    public String userSubmitted(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("user", new UserDTO());
            return "register";
        }
        if (userDTO.getPicture().equals("")) userDTO.setPicture("99-998739_dale-engen-person-placeholder-hd-png-download.png");
        pictureService.savePicture(userDTO.getPictureFile());
        userDTO.setPicture(userDTO.getPictureFile().getOriginalFilename());
        userDTO.setPassword(userService.encodePassword(userDTO.getPassword()));
        userService.save(userDTO);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        Optional<UserDTO> user = userService.authenticate(email, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            session.setMaxInactiveInterval(10*60);
            return "redirect:/contact/getAll";
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

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
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
    public String edit(@Valid @ModelAttribute UserDTO editUser, BindingResult result, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "redirect:/user/login";
        if (result.hasErrors()) {
            model.addAttribute("user",user);
            return "edit-profile-user";
        }
        if (editUser.getPassword().equals("")) editUser.setPassword(user.getPassword());
        else if (!userService.regexPassword(editUser.getPassword())) {
            model.addAttribute("user",user);
            return "edit-profile-user";
        }
        else editUser.setPassword(userService.encodePassword(editUser.getPassword()));
        userService.updateUser(editUser, user);
        return "redirect:/user/profile";
    }
}
