package fr.dawan.projet_spring_mvc.controller;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.services.ContactService;
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
    public String getAll(Model model) {
        List<ContactDTO> contacts = contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "display-contact";
    }

    // GET A SINGLE CONTACT VIEW PAGE
    @GetMapping(path = "/getContact/{id}")
    public String getById(Model model, @PathVariable Long id) {
        ContactDTO contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "contact-detail-view";
    }

    // EDIT A SINGLE CONTACT
    @GetMapping(path = "/editContact/{id}")
    public String editById(Model model, @PathVariable String id) {
        ContactDTO contact = contactService.getById(Long.parseLong(id));
        model.addAttribute("contact", contact);
        return "edit-contact";
    }





    @PostMapping(path = "/getAll")
    public String search(@RequestParam String search, Model model) {
        List<ContactDTO> contacts = contactService.getSearch(search);
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

    @PostMapping(path = "/delete")
    public String delete(@ModelAttribute ContactDTO contactDTO) {
        System.out.println(contactDTO);
        //contactService.delete(id);
        return "redirect:/contact/getAll";
    }
}
