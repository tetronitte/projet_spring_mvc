package fr.dawan.projet_spring_mvc.services;

import fr.dawan.projet_spring_mvc.dto.UserDTO;
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
}
