package fr.dawan.projet_spring_mvc.repositories;

import fr.dawan.projet_spring_mvc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
