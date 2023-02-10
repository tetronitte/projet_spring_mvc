package fr.dawan.projet_spring_mvc.dto;

import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.entities.ContactAffiliate;
import fr.dawan.projet_spring_mvc.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactDTO {

    private Long id;
    private User user;
    @NotBlank
    @Pattern(regexp = "^[\\s,.'\\-\\pL]+$", message = "Firstname error")
    private String firstname;
    @NotBlank
    @Pattern(regexp = "^[\\s,.'\\-\\pL]+$", message = "Lastname error")
    private String lastname;
    private String email;
    private String picture;
    @NotBlank
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "invalid phone number")
    private String phone;
    private LocalDate birthday;
    private List<ContactAffiliate> contactAffiliateList;
    private MultipartFile pictureFile;

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