package fr.dawan.projet_spring_mvc.services;

import fr.dawan.projet_spring_mvc.entities.ContactAffiliate;
import fr.dawan.projet_spring_mvc.repositories.ContactAffilateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactAffiliateService {

    @Autowired
    private ContactAffilateRepository contactAffilateRepository;

    public void deleteByContactId(Long id) {
        List<ContactAffiliate> contact = contactAffilateRepository.findAllByContactId(id);
        List<ContactAffiliate> contactAffiliated = contactAffilateRepository.findAllByContactAffiliatedId(id);
        contactAffilateRepository.deleteAll(contact);
        contactAffilateRepository.deleteAll(contactAffiliated);
    }
}
