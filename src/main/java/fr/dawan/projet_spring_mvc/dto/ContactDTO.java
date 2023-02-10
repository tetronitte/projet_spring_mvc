package fr.dawan.projet_spring_mvc.dto;

import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.entities.ContactAffiliate;
import fr.dawan.projet_spring_mvc.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactDTO {

    private Long id;
    private User user;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String phone;
    private LocalDate birthday;
    private List<ContactAffiliate> contactAffiliateList;

    public static ContactDTO convertFromEntity(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setUser(contact.getUser());
        contactDTO.setFirstname(contact.getFirstname());
        contactDTO.setLastname(contact.getLastname());
        contactDTO.setPicture(contact.getPicture());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setBirthday(contact.getBirthday());
        contactDTO.setContactAffiliateList(contact.getContactAffiliates());
        return contactDTO;
    }
}