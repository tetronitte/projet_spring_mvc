package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.dto.UserDTO;
import fr.dawan.projet_spring_mvc.entities.User;
import fr.dawan.projet_spring_mvc.services.ContactService;
import fr.dawan.projet_spring_mvc.services.PictureService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private PictureService pictureService;

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
    public String contactChanged(@Valid @ModelAttribute ContactDTO contact, BindingResult result, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        contact.setUser(User.convertFromDTO(user));
        if (result.hasErrors())
        {
            model.addAttribute("contact", contact);
            return "edit-contact";
        }
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
    public String contactSubmitted(@ModelAttribute ContactDTO contactDTO, BindingResult result, HttpSession session, Model model) throws IOException {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user == null) return "redirect:/user/login";
        if (result.hasErrors()) {
            model.addAttribute("contact", contactDTO);
            return "add-contact";
        }
        contactDTO.setUser(User.convertFromDTO(user));
        if (contactDTO.getPicture().equals("")) contactDTO.setPicture("99-998739_dale-engen-person-placeholder-hd-png-download.png");
        pictureService.savePicture(contactDTO.getPictureFile());
        contactDTO.setPicture(contactDTO.getPictureFile().getOriginalFilename());
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
