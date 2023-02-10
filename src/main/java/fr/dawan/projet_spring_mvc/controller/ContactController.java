package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.dto.UserDTO;
import fr.dawan.projet_spring_mvc.entities.User;
import fr.dawan.projet_spring_mvc.services.ContactService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/getAll")
    public String getAll(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        List<ContactDTO> contacts = contactService.getAllByUserId(user.getId());
        model.addAttribute("contacts", contacts);
        return "display-contact";
    }

    @PostMapping(path = "/getAll")
    public String search(@RequestParam String search, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        List<ContactDTO> contacts = contactService.getSearch(search);
        contacts.stream().forEach(c -> System.out.println(c.getPicture()));
        model.addAttribute("contacts", contacts);
        return "display-contact";
    }

    // GET A SINGLE CONTACT VIEW PAGE
    @GetMapping(path = "/getContact/{id}")
    public String getById(Model model, @PathVariable Long id, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        ContactDTO contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "contact-detail-view";
    }

    // EDIT A SINGLE CONTACT
    @GetMapping(path = "/editContact/{id}")
    public String editById(Model model, @PathVariable String id, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        ContactDTO contact = contactService.getById(Long.parseLong(id));
        model.addAttribute("contact", contact);
        return "edit-contact";
    }

    @PostMapping("/editContact/{id}")
    public String contactChanged(@ModelAttribute ContactDTO contact, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        contactService.save(contact);
        return "redirect:/contact/getAll";
    }

   // POST FORM TO ADD CONTACT
    @GetMapping("/addContact")
    public String displayAddContactForm(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        model.addAttribute("contact", new ContactDTO());
        return "add-contact";
    }

    @PostMapping(path="/addContact") // Map ONLY POST Requests
    public String contactSubmitted(@ModelAttribute ContactDTO contactDTO, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        contactDTO.setUser(User.convertFromDTO(user));
        if (contactDTO.getPicture().equals("")) contactDTO.setPicture(contactService.randomPicture());
        contactService.save(contactDTO);
        return "redirect:/contact/getAll";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam("contact") Long id, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        contactService.delete(id);
        return "redirect:/contact/getAll";
    }
}
