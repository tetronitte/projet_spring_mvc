package fr.dawan.projet_spring_mvc.dto;

import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String pictures;
    private String password;
    private List<Contact> contacts;

    public static UserDTO convertFromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPictures(user.getPictures());
        userDTO.setPassword(user.getPassword());
        userDTO.setContacts(user.getContacts());
        return userDTO;
    }
}
