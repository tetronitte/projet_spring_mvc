package fr.dawan.projet_spring_mvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Affiliate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String affiliate;

    public Affiliate (String affiliate) {
        this.affiliate = affiliate;
    }
}
