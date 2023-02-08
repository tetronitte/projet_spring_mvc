package fr.dawan.projet_spring_mvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String phone;
    private LocalDate birthday;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<ContactAffiliate> contactAffiliates = new ArrayList<>();

    public Contact(User user, String firstname, String lastname, String email, String picture, String phone, LocalDate birthday) {
        this.user = user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.picture = picture;
        this.phone = phone;
        this.birthday = birthday;
    }

    public void addContactAffiliate(ContactAffiliate contactAffiliate) {
        contactAffiliates.add(contactAffiliate);
    }
}
