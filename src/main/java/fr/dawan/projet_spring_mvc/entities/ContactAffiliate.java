package fr.dawan.projet_spring_mvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_affiliate")
@Data
@NoArgsConstructor
public class ContactAffiliate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
    @OneToOne
    @JoinColumn(name = "contact_affiliated_id")
    private Contact contactAffiliated;
    @ManyToOne
    @JoinColumn(name = "affiliate_id")
    private Affiliate affiliate;

    public ContactAffiliate(Contact contact, Contact contactAffiliated, Affiliate affiliate) {
        this.contact = contact;
        this.contactAffiliated = contactAffiliated;
        this.affiliate = affiliate;
    }
}
