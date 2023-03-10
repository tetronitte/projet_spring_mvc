package fr.dawan.projet_spring_mvc.tools;

import fr.dawan.projet_spring_mvc.entities.*;
import com.github.javafaker.Faker;
import fr.dawan.projet_spring_mvc.repositories.AffiliateRepository;
import fr.dawan.projet_spring_mvc.repositories.ContactAffilateRepository;
import fr.dawan.projet_spring_mvc.repositories.ContactRepository;
import fr.dawan.projet_spring_mvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AffiliateRepository affiliateRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactAffilateRepository contactAffilateRepository;
    @Autowired
    private PasswordHasher passwordHasher;
    private final Faker faker = new Faker(new Locale("fr"), new Random(2));
    private List<Affiliate> affiliates = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();
    private List<ContactAffiliate> contactAffiliates = new ArrayList<>();
    private User user = new User();
    private Random rd = new Random();

    @Override
    public void run(String... args) throws Exception {
        insertOwner();
        insertAffiliate();
        insertContact();
        insertContactAffiliate();
    }

    private void insertContactAffiliate() {
        contactAffiliates.add(new ContactAffiliate(contacts.get(0),contacts.get(1),affiliates.get(3)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(0),contacts.get(2),affiliates.get(1)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(0),contacts.get(3),affiliates.get(0)));

        contactAffiliates.add(new ContactAffiliate(contacts.get(1),contacts.get(0),affiliates.get(2)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(1),contacts.get(2),affiliates.get(1)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(1),contacts.get(3),affiliates.get(0)));

        contactAffiliates.add(new ContactAffiliate(contacts.get(2),contacts.get(3),affiliates.get(13)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(2),contacts.get(0),affiliates.get(4)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(2),contacts.get(1),affiliates.get(5)));

        contactAffiliates.add(new ContactAffiliate(contacts.get(3),contacts.get(2),affiliates.get(12)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(3),contacts.get(0),affiliates.get(4)));
        contactAffiliates.add(new ContactAffiliate(contacts.get(3),contacts.get(1),affiliates.get(5)));

        contactAffilateRepository.saveAll(contactAffiliates);
    }

    private void insertContact() {
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"99-998739_dale-engen-person-placeholder-hd-png-download.png",faker.phoneNumber().cellPhone(),LocalDate.of(1995, 5, 20)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"99-998739_dale-engen-person-placeholder-hd-png-download.png",faker.phoneNumber().cellPhone(),LocalDate.of(1993, 2, 12)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"99-998739_dale-engen-person-placeholder-hd-png-download.png",faker.phoneNumber().cellPhone(),LocalDate.of(1970, 12, 29)));
        contacts.add(new Contact(user,faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"99-998739_dale-engen-person-placeholder-hd-png-download.png",faker.phoneNumber().cellPhone(),LocalDate.of(1972, 10, 30)));
        contactRepository.saveAll(contacts);
    }

    private void insertAffiliate() {
        affiliates.add(new Affiliate("M??re"));
        affiliates.add(new Affiliate("P??re"));
        affiliates.add(new Affiliate("Fr??re"));
        affiliates.add(new Affiliate("Soeur"));
        affiliates.add(new Affiliate("Fils"));
        affiliates.add(new Affiliate("Fille"));
        affiliates.add(new Affiliate("Oncle"));
        affiliates.add(new Affiliate("Tante"));
        affiliates.add(new Affiliate("Grand-M??re"));
        affiliates.add(new Affiliate("Grand-P??re"));
        affiliates.add(new Affiliate("Cousin"));
        affiliates.add(new Affiliate("Cousine"));
        affiliates.add(new Affiliate("Conjoint"));
        affiliates.add(new Affiliate("Conjointe"));
        affiliateRepository.saveAll(affiliates);
    }

    private void insertOwner() {
        user.setFirstname("Hubert");
        user.setLastname("Bonisseur De La Bath");
        user.setEmail("hubert.bdlb@example.com");
        user.setPassword(passwordHasher.encode("aaAA11@@"));
        user.setPicture("99-998739_dale-engen-person-placeholder-hd-png-download.png");
        userRepository.save(user);
    }
}
