package sn.esmt.emploi.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "appCV")
public class AppCVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String nom;
    @Column(length = 200, nullable = false)
    private String prenom;
    @Column(length = 6, nullable = false)
    private int age;
    @Column(length = 200, nullable = false)
    private String adresse;
    @Column(length = 200, nullable = false, unique = true)
    private String email;
    @Column(length = 200, nullable = false)
    private String telephone;
    @Column(length = 200, nullable = false)
    private String specialite;
    @Column(length = 200, nullable = false)
    private String niveauEtude;
    @Column(length = 2000, nullable = false)
    private String experienceProfessionnelle;

}
