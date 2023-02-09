package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserController {





    @GetMapping("/addContact")
    public String displayAddContactForm(Model model){

        ContactDTO contactDTO = new ContactDTO();
        model.addAttribute("contact", contactDTO);

        return "addcontact";
    }


    @PostMapping(path="/addContact") // Map ONLY POST Requests
    public String contactSubmitted(ContactDTO contactDTO) {
        contactDTO.setFirstname();
        contactDTO.setLastname();
        contactDTO.setEmail();
        contactDTO.setPicture();
        contactDTO.setPhone();
        contactDTO.setBirthday();
        championService.save(championDTO);
        return "redirect:/contact/display-contact";
    }
}
