package fr.dawan.projet_spring_mvc.services;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactAffiliateService contactAffiliateService;

    public List<ContactDTO> getAllByUserId(Long id) {
        return convertListFromEntities(contactRepository.findAllByUserId(id));
    }

    public void delete(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
        contactAffiliateService.deleteByContactId(contact.getId());
        contactRepository.delete(contact);
    }

    public List<ContactDTO> getSearch(String search) {
        return convertListFromEntities(contactRepository.findByFirstnameContainingOrLastnameContainingOrEmailContainingOrPhoneContaining(search,search,search,search));
    }

    // GET A SINGLE CONTACT BY ITS ID
    public ContactDTO getById(Long id)
    {

        Contact contact = contactRepository.getById(id);

        return ContactDTO.convertFromEntity(contact);
    }


    public ContactDTO save(ContactDTO contactDTO){
        return ContactDTO.convertFromEntity(contactRepository.save(Contact.convertFromDTO(contactDTO)));
    }


    private List<ContactDTO> convertListFromEntities(List<Contact> contactList) {
        return contactList
                .stream()
                .map(contact -> ContactDTO.convertFromEntity(contact))
                .collect(Collectors.toList());
    }
}