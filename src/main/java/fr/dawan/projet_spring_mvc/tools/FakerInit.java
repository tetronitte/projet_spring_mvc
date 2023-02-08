package fr.dawan.projet_spring_mvc.tools;

import fr.dawan.projet_spring_mvc.entities.*;
import com.github.javafaker.Faker;
import fr.dawan.projet_spring_mvc.repositories.AffiliateRepository;
import fr.dawan.projet_spring_mvc.repositories.ContactRepository;
import fr.dawan.projet_spring_mvc.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class FakerInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AffiliateRepository affiliateRepository;
    private final ContactRepository contactRepository;
    private final Faker faker = new Faker(new Locale("fr"), new Random(2));
    private List<Affiliate> affiliates = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();
    private User user = new User();

    public FakerInit(UserRepository userRepository, AffiliateRepository affiliateRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.affiliateRepository = affiliateRepository;
        this.contactRepository = contactRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        insertOwner();
        insertAffiliate();
        insertContact();
        insertContactAffiliate();
    }

    private void insertContactAffiliate() {
        contacts.get(0).addContactAffiliate(createContactAffiliate(contacts.get(0),contacts.get(1),affiliates.get(3)));
        contacts.get(0).addContactAffiliate(createContactAffiliate(contacts.get(0),contacts.get(2),affiliates.get(1)));
        contacts.get(0).addContactAffiliate(createContactAffiliate(contacts.get(0),contacts.get(3),affiliates.get(0)));

        contacts.get(1).addContactAffiliate(createContactAffiliate(contacts.get(1),contacts.get(0),affiliates.get(2)));
        contacts.get(1).addContactAffiliate(createContactAffiliate(contacts.get(1),contacts.get(2),affiliates.get(1)));
        contacts.get(1).addContactAffiliate(createContactAffiliate(contacts.get(1),contacts.get(3),affiliates.get(0)));

        contacts.get(2).addContactAffiliate(createContactAffiliate(contacts.get(2),contacts.get(3),affiliates.get(13)));
        contacts.get(2).addContactAffiliate(createContactAffiliate(contacts.get(2),contacts.get(0),affiliates.get(4)));
        contacts.get(2).addContactAffiliate(createContactAffiliate(contacts.get(2),contacts.get(1),affiliates.get(5)));

        contacts.get(3).addContactAffiliate(createContactAffiliate(contacts.get(3),contacts.get(2),affiliates.get(12)));
        contacts.get(3).addContactAffiliate(createContactAffiliate(contacts.get(3),contacts.get(0),affiliates.get(4)));
        contacts.get(3).addContactAffiliate(createContactAffiliate(contacts.get(3),contacts.get(1),affiliates.get(5)));

        contactRepository.saveAll(contacts);
    }

    private void insertContact() {
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"osef.png",faker.phoneNumber().cellPhone(),LocalDate.of(1995, 5, 20)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"osef.png",faker.phoneNumber().cellPhone(),LocalDate.of(1993, 2, 12)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"osef.png",faker.phoneNumber().cellPhone(),LocalDate.of(1970, 12, 29)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"osef.png",faker.phoneNumber().cellPhone(),LocalDate.of(1972, 10, 30)));
        contactRepository.saveAll(contacts);
    }

    private ContactAffiliate createContactAffiliate(Contact contact, Contact contactAffiliated, Affiliate affiliate) {
        return new ContactAffiliate(contact,contactAffiliated,affiliate);
    }

    private void insertAffiliate() {
        affiliates.add(new Affiliate("Mère"));
        affiliates.add(new Affiliate("Père"));
        affiliates.add(new Affiliate("Frère"));
        affiliates.add(new Affiliate("Soeur"));
        affiliates.add(new Affiliate("Fils"));
        affiliates.add(new Affiliate("Fille"));
        affiliates.add(new Affiliate("Oncle"));
        affiliates.add(new Affiliate("Tante"));
        affiliates.add(new Affiliate("Grand-Mère"));
        affiliates.add(new Affiliate("Grand-Père"));
        affiliates.add(new Affiliate("Cousin"));
        affiliates.add(new Affiliate("Cousine"));
        affiliates.add(new Affiliate("Conjoint"));
        affiliates.add(new Affiliate("Conjointe"));
        affiliateRepository.saveAll(affiliates);
    }

    private void insertOwner() {
        user.setFirstname(faker.name().firstName());
        user.setLastname(faker.name().lastName());
        user.setEmail(user.getFirstname()+user.getLastname()+"@example.com");
        user.setPassword("aaAA11@@");
        user.setPictures("./pictures/pp.png");
        userRepository.save(user);
    }
}
