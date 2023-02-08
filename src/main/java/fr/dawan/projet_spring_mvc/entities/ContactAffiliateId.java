package fr.dawan.projet_spring_mvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class ContactAffiliateId implements Serializable {

    @Column(name = "contact_id")
    private Long contactId;
    @Column(name = "affiliate_id")
    private Long affiliateId;

    public ContactAffiliateId(Long contactId, Long affiliateId) {
        this.contactId = contactId;
        this.affiliateId = affiliateId;
    }
}
