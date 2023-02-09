package fr.dawan.projet_spring_mvc.repositories;

import fr.dawan.projet_spring_mvc.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "contact", path = "contact")
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public List<Contact> findByFirstnameContainingOrLastnameContainingOrEmailContainingOrPhoneContaining(String firstname, String lastname, String email, String phone);
}
