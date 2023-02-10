package fr.dawan.projet_spring_mvc.services;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.dto.UserDTO;
import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.entities.User;
import fr.dawan.projet_spring_mvc.repositories.UserRepository;
import fr.dawan.projet_spring_mvc.tools.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHasher passwordHasher;

    public Optional<UserDTO> authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (passwordHasher.matches(password, user.getPassword())) return Optional.of(UserDTO.convertFromEntity(user));
        return Optional.empty();
    }

    public UserDTO save(UserDTO userDTO){
        return UserDTO.convertFromEntity(userRepository.save(User.ConvertFromDTO(userDTO)));
    }

    public String encodePassword(String password) {
        return passwordHasher.encode(password);
    }

    public UserDTO updateUser(UserDTO editUser, UserDTO user) {
        user.setFirstname(editUser.getFirstname());
        user.setLastname(editUser.getLastname());
        user.setEmail(editUser.getEmail());
        user.setPicture(editUser.getPicture());
        user.setPassword(editUser.getPassword());
        return UserDTO.convertFromEntity(userRepository.save(User.convertFromDTO(user)));
    }
}
