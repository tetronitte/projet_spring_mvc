package fr.dawan.projet_spring_mvc.repositories;

import fr.dawan.projet_spring_mvc.entities.ContactAffiliate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactAffilateRepository extends JpaRepository<ContactAffiliate, Long> {

   List<ContactAffiliate> findAllByContactId(Long id);

   List<ContactAffiliate> findAllByContactAffiliatedId(Long id);
}
