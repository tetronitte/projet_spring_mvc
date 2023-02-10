package fr.dawan.projet_spring_mvc.dto;

import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.entities.User;
import fr.dawan.projet_spring_mvc.tools.Regex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank
    @Pattern(regexp = "^[\\s,.'\\-\\pL]+$", message = "Firstname eror")
    private String firstname;
    @NotBlank
    @Pattern(regexp = "^[\\s,.'\\-\\pL]+$", message = "Firstname eror")
    private String lastname;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "email not valid")
    private String email;
    private String picture;
    private String password;
    private List<Contact> contacts;

    public static UserDTO convertFromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPicture(user.getPicture());
        userDTO.setPassword(user.getPassword());
        userDTO.setContacts(user.getContacts());
        return userDTO;
    }
}
