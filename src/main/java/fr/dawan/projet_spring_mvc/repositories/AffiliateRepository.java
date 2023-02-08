package fr.dawan.projet_spring_mvc.repositories;

import fr.dawan.projet_spring_mvc.entities.Affiliate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {
}
