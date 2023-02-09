package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/getAll")
    public String getAll(Model model) {
        List<ContactDTO> contacts = contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "display-contact";
    }




   // POST FORM TO ADD CONTACT
    @GetMapping("/addContact")
    public String displayAddContactForm(Model model){

        ContactDTO contactDTO = new ContactDTO();
        model.addAttribute("contact", contactDTO);

        return "add-contact";
    }


    @PostMapping(path="/addContact") // Map ONLY POST Requests
    public String contactSubmitted(@ModelAttribute ContactDTO contactDTO) {
        contactService.save(contactDTO);
        return "redirect:/contact/getAll";
    }
}
