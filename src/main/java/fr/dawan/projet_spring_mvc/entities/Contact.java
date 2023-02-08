package fr.dawan.projet_spring_mvc.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String pictures;
    private String phone;
    private LocalDate birthday;
    @ManyToMany(mappedBy = "contacts")
    private List<Affiliate> affiliates;
}
