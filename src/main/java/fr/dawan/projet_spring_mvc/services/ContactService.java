package fr.dawan.projet_spring_mvc.services;

import fr.dawan.projet_spring_mvc.dto.ContactDTO;
import fr.dawan.projet_spring_mvc.entities.Contact;
import fr.dawan.projet_spring_mvc.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> getAll() {
        return convertListFromEntities(contactRepository.findAll());
    }

    public void delete(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
        contactRepository.delete(contact);
    }

    public ContactDTO save(ContactDTO contactDTO){
        return ContactDTO.convertFromEntity(contactRepository.save(Contact.ConvertFromDTO(contactDTO)));
    }


    private List<ContactDTO> convertListFromEntities(List<Contact> contactList) {
        return contactList
                .stream()
                .map(contact -> ContactDTO.convertFromEntity(contact))
                .collect(Collectors.toList());
    }
}